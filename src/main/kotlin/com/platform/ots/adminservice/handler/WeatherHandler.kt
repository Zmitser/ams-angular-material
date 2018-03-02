package com.platform.ots.adminservice.handler

import com.github.fedy2.weather.YahooWeatherService
import com.github.fedy2.weather.YahooWeatherService.LimitDeclaration
import com.github.fedy2.weather.data.unit.DegreeUnit
import org.openweathermap.api.UrlConnectionWeatherClient
import org.openweathermap.api.model.forecast.ForecastInformation
import org.openweathermap.api.model.forecast.daily.DailyForecast
import org.openweathermap.api.query.Language
import org.openweathermap.api.query.QueryBuilderPicker
import org.openweathermap.api.query.UnitFormat.METRIC
import org.openweathermap.api.query.forecast.daily.ByCityName
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.server.ServerRequest
import org.springframework.web.reactive.function.server.ServerResponse
import org.springframework.web.reactive.function.server.ServerResponse.ok
import org.springframework.web.reactive.function.server.body
import reactor.core.publisher.Mono
import reactor.core.publisher.toMono
import tk.plogitech.darksky.api.jackson.DarkSkyJacksonClient
import tk.plogitech.darksky.forecast.APIKey
import tk.plogitech.darksky.forecast.ForecastRequest
import tk.plogitech.darksky.forecast.ForecastRequestBuilder
import tk.plogitech.darksky.forecast.ForecastRequestBuilder.Block.hourly
import tk.plogitech.darksky.forecast.ForecastRequestBuilder.Block.minutely
import tk.plogitech.darksky.forecast.ForecastRequestBuilder.Language.ru
import tk.plogitech.darksky.forecast.ForecastRequestBuilder.Units.si
import tk.plogitech.darksky.forecast.GeoCoordinates
import tk.plogitech.darksky.forecast.model.Latitude
import tk.plogitech.darksky.forecast.model.Longitude


@Component
class WeatherHandler(val yahooWeatherService: YahooWeatherService,
                     val urlConnectionWeatherClient: UrlConnectionWeatherClient,
                     val darkSkyClient: DarkSkyJacksonClient) {

    fun getYahooWeather(serverRequest: ServerRequest): Mono<ServerResponse> {
        val limitDeclaration: LimitDeclaration = yahooWeatherService.getForecastForLocation("Minsk", DegreeUnit.CELSIUS)
        return ok().body(limitDeclaration.first(1)[0].toMono())
    }

    fun getOpenMapWeather(serverRequest: ServerRequest): Mono<ServerResponse> {
        val build: ByCityName = QueryBuilderPicker.pick()
                .forecast()
                .daily()
                .byCityName("Minsk")
                .countryCode("BY")
                .unitFormat(METRIC)
                .language(Language.RUSSIAN)
                .build()
        val forecastInformation: ForecastInformation<DailyForecast> = urlConnectionWeatherClient.getForecastInformation(build)
        return ok().body(forecastInformation.toMono())
    }

    fun getForecastIoWeather(serverRequest: ServerRequest): Mono<ServerResponse> {
        val request: ForecastRequest = ForecastRequestBuilder()
                .key(APIKey("a78c9e6952fbb80168611297fed8bc3c"))
                .language(ru)
                .units(si)
                .exclude(minutely, hourly)
                .location(GeoCoordinates(Longitude(27.5666667), Latitude(53.9)))
                .build()
        return ok().body(darkSkyClient.forecast(request).toMono())
    }
}