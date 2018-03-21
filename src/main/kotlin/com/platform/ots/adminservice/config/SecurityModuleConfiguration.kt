package com.platform.ots.adminservice.config

import com.platform.ots.adminservice.filter.CorsFilter
import com.platform.ots.adminservice.security.AmsAjaxAuthenticationFailureHandler
import com.platform.ots.adminservice.security.AmsAjaxAuthenticationSuccessHandler
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity
import org.springframework.security.config.web.server.SecurityWebFiltersOrder.FIRST
import org.springframework.security.config.web.server.ServerHttpSecurity
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.web.server.SecurityWebFilterChain
import org.springframework.security.web.server.authentication.ServerAuthenticationFailureHandler
import org.springframework.security.web.server.authentication.ServerAuthenticationSuccessHandler
import org.springframework.security.web.server.csrf.WebSessionServerCsrfTokenRepository


@Configuration
@EnableWebFluxSecurity
class SecurityModuleConfiguration(val corsFilter: CorsFilter) {


    @Bean
    fun passwordEncoder(): PasswordEncoder = BCryptPasswordEncoder()


    @Bean
    fun amsAjaxAuthenticationSuccessHandler(): ServerAuthenticationSuccessHandler = AmsAjaxAuthenticationSuccessHandler()

    @Bean
    fun amsAjaxAuthenticationFailureHandler(): ServerAuthenticationFailureHandler = AmsAjaxAuthenticationFailureHandler()

    @Bean
    fun springSecurityFilterChain(http: ServerHttpSecurity): SecurityWebFilterChain {
        return http.csrf()
                .csrfTokenRepository(WebSessionServerCsrfTokenRepository())
                .and()
                .addFilterAt(corsFilter, FIRST)
                .formLogin()
                .authenticationSuccessHandler(amsAjaxAuthenticationSuccessHandler())
                .authenticationFailureHandler(amsAjaxAuthenticationFailureHandler())
                .and()
                .authorizeExchange()
                .pathMatchers("/api/v1/register").permitAll()
                .pathMatchers("/api/v1/activate").permitAll()
                .pathMatchers("/api/v1/authenticate").permitAll()
                .pathMatchers("/api/v1/account/reset-password/init").permitAll()
                .pathMatchers("/api/v1/account/reset-password/finish").permitAll()
                .pathMatchers("/api/v1/profile-info").permitAll()
                .pathMatchers("/api/v1/users").permitAll()
                .and()
                .build()
    }

}