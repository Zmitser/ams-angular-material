package com.platform.ots.adminservice.strategy

import com.platform.ots.adminservice.web.dto.WeatherDto
import org.openweathermap.api.UrlConnectionWeatherClient
import org.openweathermap.api.model.forecast.ForecastInformation
import org.openweathermap.api.model.forecast.daily.DailyForecast
import org.openweathermap.api.query.Language.ENGLISH
import org.openweathermap.api.query.QueryBuilderPicker
import org.openweathermap.api.query.UnitFormat.METRIC
import org.openweathermap.api.query.forecast.daily.ByCityName
import org.springframework.stereotype.Component

@Component
class OpenWeatherStrategy(val urlConnectionWeatherClient: UrlConnectionWeatherClient) : WeatherStrategy {
    override fun forecast(): List<WeatherDto> {
        val build: ByCityName = QueryBuilderPicker.pick()
                .forecast()
                .daily()
                .byCityName("Minsk")
                .unitFormat(METRIC)
                .language(ENGLISH)
                .build()
        val forecastInformation: ForecastInformation<DailyForecast> =
                urlConnectionWeatherClient.getForecastInformation(build)
        return forecastInformation.forecasts.map {
            WeatherDto(it.temperature.max.toInt(), it.temperature.min.toInt(), it.weather.first().description, it.dateTime.toInstant(), serviceName())
        }
    }

    override fun serviceName(): String = "Open Weather"
}