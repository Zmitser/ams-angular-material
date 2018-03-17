package com.platform.ots.adminservice.config

import com.platform.ots.adminservice.filter.CorsFilter
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity
import org.springframework.security.config.web.server.SecurityWebFiltersOrder.FIRST
import org.springframework.security.config.web.server.ServerHttpSecurity
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.web.server.SecurityWebFilterChain


@Configuration
@EnableWebFluxSecurity
class SecurityModuleConfiguration(val corsFilter: CorsFilter) {


    @Bean
    fun passwordEncoder(): PasswordEncoder = BCryptPasswordEncoder()


    @Bean
    fun springWebFilterChain(http: ServerHttpSecurity): SecurityWebFilterChain? {
        return http.addFilterAt(corsFilter, FIRST).build()
    }


}