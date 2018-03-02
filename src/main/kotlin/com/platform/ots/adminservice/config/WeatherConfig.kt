package com.platform.ots.adminservice.config

import com.github.fedy2.weather.YahooWeatherService
import org.openweathermap.api.UrlConnectionWeatherClient
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import tk.plogitech.darksky.api.jackson.DarkSkyJacksonClient

@Configuration
class WeatherConfig {

    @Bean
    fun yahooWeatherService(): YahooWeatherService = YahooWeatherService()

    @Bean
    fun openWeatherMapService(): UrlConnectionWeatherClient = UrlConnectionWeatherClient("1d82742ee19d794f79341fab6dac7b50")

    @Bean
    fun forecastIoService(): DarkSkyJacksonClient = DarkSkyJacksonClient()
}