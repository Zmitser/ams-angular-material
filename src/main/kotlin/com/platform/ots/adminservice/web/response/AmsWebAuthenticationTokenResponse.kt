package com.platform.ots.adminservice.web.response

import java.io.Serializable

data class AmsWebAuthenticationTokenResponse(val username: String?, val token: String?) : Serializable {
    constructor() : this(null, null)
}