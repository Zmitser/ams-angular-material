package com.platform.ots.adminservice.web.response

import java.io.Serializable

data class AmsWebAuthenticationTokenRequest(val username: String?, val password: String?) : Serializable {
    constructor() : this(null, null)
}