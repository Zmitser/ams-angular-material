package com.platform.ots.adminservice.web.dto

import java.time.Instant

data class WeatherDto(
        val high: Int,
        val low: Int,
        val text: String?,
        val date: Instant,
        val weatherServiceName: String
)