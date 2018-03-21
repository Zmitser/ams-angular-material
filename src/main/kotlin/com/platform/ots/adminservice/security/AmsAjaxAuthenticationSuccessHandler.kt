package com.platform.ots.adminservice.security

import org.springframework.http.HttpStatus
import org.springframework.security.core.Authentication
import org.springframework.security.web.server.WebFilterExchange
import org.springframework.security.web.server.authentication.ServerAuthenticationSuccessHandler
import reactor.core.publisher.Mono
import reactor.core.publisher.Mono.empty


class AmsAjaxAuthenticationSuccessHandler : ServerAuthenticationSuccessHandler {

    override fun onAuthenticationSuccess(webFilterExchange: WebFilterExchange?,
                                         authentication: Authentication?): Mono<Void> {
        webFilterExchange?.exchange?.response?.statusCode = HttpStatus.OK
        return empty()
    }
}