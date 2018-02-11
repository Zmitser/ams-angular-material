package com.platform.ots.adminservice.service.dto

data class UserDto(var id: Int?,
                   var firstName: String?,
                   var lastName: String?,
                   var userName: String?,
                   var password: String?,
                   var email: String?) {
    constructor() : this(null, null, null, null, null, null)
}