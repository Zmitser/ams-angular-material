package com.platform.ots.adminservice.strategy

import com.platform.ots.adminservice.web.dto.WeatherDto
import org.springframework.stereotype.Service
import reactor.core.publisher.Mono
import reactor.core.publisher.toMono

@Service
class WeatherService(val weatherStrategies: List<WeatherStrategy>) {

    fun getWeather(): Mono<Map<String, List<WeatherDto>>> {

        val groupBy: Map<String, List<WeatherDto>> = weatherStrategies.map {
            it.forecast()
        }.flatMap {
            it
        }.groupBy {
            it.weatherServiceName
        }
        return groupBy.toMono()
    }
}