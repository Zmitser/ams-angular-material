package com.platform.ots.adminservice.security

import org.springframework.http.HttpStatus
import org.springframework.security.core.AuthenticationException
import org.springframework.security.web.server.WebFilterExchange
import org.springframework.security.web.server.authentication.ServerAuthenticationFailureHandler
import reactor.core.publisher.Mono

class AmsAjaxAuthenticationFailureHandler : ServerAuthenticationFailureHandler {

    override fun onAuthenticationFailure(webFilterExchange: WebFilterExchange?,
                                         exception: AuthenticationException?): Mono<Void> {
        webFilterExchange?.exchange?.response?.statusCode = HttpStatus.UNAUTHORIZED
        return Mono.empty()
    }
}