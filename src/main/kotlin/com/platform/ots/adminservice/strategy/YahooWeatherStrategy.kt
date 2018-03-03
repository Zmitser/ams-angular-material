package com.platform.ots.adminservice.strategy

import com.github.fedy2.weather.YahooWeatherService
import com.github.fedy2.weather.YahooWeatherService.LimitDeclaration
import com.github.fedy2.weather.data.Item
import com.github.fedy2.weather.data.unit.DegreeUnit.CELSIUS
import com.platform.ots.adminservice.web.dto.WeatherDto
import org.springframework.stereotype.Component

@Component
class YahooWeatherStrategy(val yahooWeatherService: YahooWeatherService) : WeatherStrategy {

    override fun serviceName(): String = "Yahoo Weather"

    override fun forecast(): List<WeatherDto> {
        val forecastForLocation: LimitDeclaration = yahooWeatherService.getForecastForLocation("Minsk", CELSIUS)
        val item: Item = forecastForLocation.first(1).first().item
        return item.forecasts.map {
            WeatherDto(it.high, it.low, it.text, it.date.toInstant(), serviceName())
        }
    }
}