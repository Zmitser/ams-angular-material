package com.platform.ots.adminservice.config

import com.github.fedy2.weather.YahooWeatherService
import org.openweathermap.api.UrlConnectionWeatherClient
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import tk.plogitech.darksky.api.jackson.DarkSkyJacksonClient

@Configuration
class WeatherConfig(@Value("\${open-weather.api.key}") val apiKey: String) {

    @Bean
    fun yahooWeatherService(): YahooWeatherService = YahooWeatherService()

    @Bean
    fun openWeatherMapService(): UrlConnectionWeatherClient = UrlConnectionWeatherClient(apiKey)

    @Bean
    fun forecastIoService(): DarkSkyJacksonClient = DarkSkyJacksonClient()
}