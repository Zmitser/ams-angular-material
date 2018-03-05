package com.platform.ots.adminservice.web.dto

import com.fasterxml.jackson.annotation.JsonFormat
import java.io.Serializable
import java.time.LocalDateTime

data class WeatherDto(
        val high: Int,
        val low: Int,
        val text: String?,
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss")
        val date: LocalDateTime,
        val weatherServiceName: String
) : Serializable