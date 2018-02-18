package com.platform.ots.adminservice.service.dto

import com.fasterxml.jackson.annotation.JsonFormat
import java.time.LocalDateTime

data class UserDto(var id: Long?,
                   var firstName: String?,
                   var lastName: String?,
                   var userName: String?,
                   var email: String?,
                   @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss")
                   var createDate: LocalDateTime?) {
    constructor() : this(null, null, null, null, null, null)
}