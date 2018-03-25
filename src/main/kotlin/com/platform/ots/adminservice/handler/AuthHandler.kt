package com.platform.ots.adminservice.handler

import com.platform.ots.adminservice.security.AmsTokenProvider
import com.platform.ots.adminservice.security.DomainUserDetailsService
import com.platform.ots.adminservice.web.response.AmsWebAuthenticationTokenRequest
import com.platform.ots.adminservice.web.response.AmsWebAuthenticationTokenResponse
import org.springframework.http.HttpStatus.CREATED
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.server.ServerRequest
import org.springframework.web.reactive.function.server.ServerResponse
import org.springframework.web.reactive.function.server.ServerResponse.status
import org.springframework.web.reactive.function.server.body
import reactor.core.publisher.Mono
import reactor.core.publisher.toMono

@Component
class AuthHandler(val tokenProvider: AmsTokenProvider,
                  val userDetailsService: DomainUserDetailsService) {


    fun createToken(serverRequest: ServerRequest): Mono<ServerResponse> {
        return serverRequest.bodyToMono(AmsWebAuthenticationTokenRequest::class.java)
                .flatMap {
                    userDetailsService.findByUsername(it.username)
                }
                .flatMap {
                    status(CREATED).body(AmsWebAuthenticationTokenResponse(it.username, createToken(it)).toMono())
                }

    }

    private fun createToken(it: UserDetails) =
            tokenProvider.createToken(UsernamePasswordAuthenticationToken(it.username, it.password), true)
}