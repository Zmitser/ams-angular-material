package com.platform.ots.adminservice.security

import org.springframework.security.authentication.BadCredentialsException
import org.springframework.security.authentication.ReactiveAuthenticationManager
import org.springframework.security.core.Authentication
import org.springframework.security.core.userdetails.ReactiveUserDetailsService
import org.springframework.security.crypto.password.PasswordEncoder
import reactor.core.publisher.Mono
import reactor.core.publisher.Mono.error
import reactor.core.publisher.toMono


class AmsAuthenticationManager(private var passwordEncoder: PasswordEncoder,
                               private var userDetailsService: ReactiveUserDetailsService) : ReactiveAuthenticationManager {

    override fun authenticate(authentication: Authentication): Mono<Authentication>? {
        return authentication.toMono()
                .filter { it is AmsWebAuthenticationToken }
                .switchIfEmpty(error(BadCredentialsException("Bad credentials")))
                .map {
                    it.isAuthenticated = true
                    it
                }
    }
}