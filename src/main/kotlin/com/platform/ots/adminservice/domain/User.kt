package com.platform.ots.adminservice.domain

data class User(val id: Int?,
                val firstName: String?,
                val lastName: String?,
                val userName: String?,
                val email: String?,
                val password: String?) {
    constructor() : this(null, null, null, null, null, null)
}