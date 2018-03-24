package com.platform.ots.adminservice.security


import org.apache.commons.collections4.MapUtils.isNotEmpty
import org.apache.commons.lang3.StringUtils
import org.apache.commons.lang3.StringUtils.isEmpty
import org.apache.commons.lang3.StringUtils.startsWith
import org.springframework.beans.factory.annotation.Value
import org.springframework.security.authentication.BadCredentialsException
import org.springframework.security.core.Authentication
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Component
import org.springframework.util.MultiValueMap
import org.springframework.web.server.ServerWebExchange
import reactor.core.publisher.Mono
import reactor.core.scheduler.Schedulers

@Component
class AmsAuthenticationConverter(private val amsTokenProvider: AmsTokenProvider,
                                 private val userDetailsService: DomainUserDetailsService,
                                 @Value("\${jwt.token.header}") var header: String,
                                 @Value("\${jwt.token.param}") var param: String,
                                 @Value("\${jwt.token.prefix}") var prefix: String)
    : (ServerWebExchange) -> Mono<Authentication> {

    override fun invoke(serverWebExchange: ServerWebExchange): Mono<Authentication> {
        val request = serverWebExchange.request
        val token: String? = request.headers.getFirst(header)
        var authToken: String? = null
        if (startsWith(token, "$prefix ")) {
            authToken = token?.substring(7)
        }
        val queryParams: MultiValueMap<String, String> = request.queryParams
        if (isEmpty(authToken) && isNotEmpty(queryParams)) {
            val authTokenParam: String? = queryParams.getFirst(param)
            if (StringUtils.isNotEmpty(authTokenParam)) {
                authToken = authTokenParam
            }
        }
        var username: String? = null
        if (StringUtils.isNotEmpty(authToken)) {
            username = amsTokenProvider.getUsernameFromToken(authToken)
        }
        if (StringUtils.isNotEmpty(username) && SecurityContextHolder.getContext().authentication == null) {
            if (amsTokenProvider.validateToken(authToken)) {
                return this.userDetailsService.findByUsername(username)
                        .publishOn(Schedulers.parallel())
                        .switchIfEmpty(Mono.error(BadCredentialsException("Invalid Credentials")))
                        .map { AmsWebAuthenticationToken("fd", it.username, it.authorities.toList()) }
            }
        }
        return Mono.empty()
    }
}