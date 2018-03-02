package com.platform.ots.adminservice.web.dto

import com.fasterxml.jackson.annotation.JsonFormat
import com.fasterxml.jackson.annotation.JsonProperty
import java.io.Serializable
import java.time.LocalDateTime
import java.time.LocalDateTime.now

data class UserDto(@JsonProperty("id") var id: Long?,
                   var firstName: String?,
                   var lastName: String?,
                   var userName: String?,
                   var email: String?,
                   @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss")
                   var createDate: LocalDateTime = now()) : Serializable {
    constructor() : this(null, null, null, null, null)
}
