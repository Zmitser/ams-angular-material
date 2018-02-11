package com.platform.ots.adminservice.service.dto

import com.fasterxml.jackson.annotation.JsonFormat
import java.time.LocalDateTime

data class UserDto(var id: Int?,
                   var firstName: String?,
                   var lastName: String?,
                   var userName: String?,
                   var password: String?,
                   var email: String?,
                   @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss")
                   var createDate: LocalDateTime?) {
    constructor() : this(null, null, null, null, null, null, null)
}