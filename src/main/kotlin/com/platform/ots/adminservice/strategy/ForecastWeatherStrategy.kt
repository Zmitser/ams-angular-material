package com.platform.ots.adminservice.strategy

import com.platform.ots.adminservice.web.dto.WeatherDto
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component
import tk.plogitech.darksky.api.jackson.DarkSkyJacksonClient
import tk.plogitech.darksky.forecast.APIKey
import tk.plogitech.darksky.forecast.ForecastRequest
import tk.plogitech.darksky.forecast.ForecastRequestBuilder
import tk.plogitech.darksky.forecast.ForecastRequestBuilder.Block.hourly
import tk.plogitech.darksky.forecast.ForecastRequestBuilder.Block.minutely
import tk.plogitech.darksky.forecast.ForecastRequestBuilder.Language.en
import tk.plogitech.darksky.forecast.ForecastRequestBuilder.Units.si
import tk.plogitech.darksky.forecast.GeoCoordinates
import tk.plogitech.darksky.forecast.model.Forecast
import tk.plogitech.darksky.forecast.model.Latitude
import tk.plogitech.darksky.forecast.model.Longitude
import java.time.LocalDateTime.ofInstant
import java.time.ZoneOffset.UTC


@Component
class ForecastWeatherStrategy(val darkSkyClient: DarkSkyJacksonClient,
                              @Value("\${forecast-io.api.key}") val apiKey: String) : WeatherStrategy {
    override fun forecast(): List<WeatherDto> {
        val request: ForecastRequest = ForecastRequestBuilder()
                .key(APIKey(apiKey))
                .language(en)
                .units(si)
                .exclude(minutely, hourly)
                .location(GeoCoordinates(Longitude(27.5666667), Latitude(53.9)))
                .build()
        val forecast: Forecast = darkSkyClient.forecast(request)
        return forecast.daily.data.map {
            WeatherDto(
                    it.temperatureMax.toInt(),
                    it.temperatureMin.toInt(),
                    it.summary,
                    ofInstant(it.time, UTC),
                    serviceName()
            )
        }

    }

    override fun serviceName(): String = "Forecast.io"
}