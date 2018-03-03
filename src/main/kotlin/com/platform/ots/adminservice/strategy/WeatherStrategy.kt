package com.platform.ots.adminservice.strategy

import com.platform.ots.adminservice.web.dto.WeatherDto

interface WeatherStrategy {

    fun forecast(): List<WeatherDto>

    fun serviceName(): String
}