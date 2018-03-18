package com.platform.ots.adminservice.config

import com.platform.ots.adminservice.filter.CorsFilter
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity
import org.springframework.security.config.web.server.SecurityWebFiltersOrder.FIRST
import org.springframework.security.config.web.server.ServerHttpSecurity
import org.springframework.security.core.userdetails.MapReactiveUserDetailsService
import org.springframework.security.core.userdetails.User
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.web.server.SecurityWebFilterChain
import org.springframework.security.web.server.csrf.WebSessionServerCsrfTokenRepository


@Configuration
@EnableWebFluxSecurity
class SecurityModuleConfiguration(val corsFilter: CorsFilter) {


    @Bean
    fun passwordEncoder(): PasswordEncoder = BCryptPasswordEncoder()


    @Bean
    fun springWebFilterChain(http: ServerHttpSecurity): SecurityWebFilterChain {
        http
                .csrf()
                .csrfTokenRepository(WebSessionServerCsrfTokenRepository())

        http
                .addFilterAt(corsFilter, FIRST)
                .exceptionHandling()

        http
                .httpBasic()
                .disable()

        http
                .formLogin()
                .disable()

        http
                .logout()
                .logoutUrl("/api/v1/logout")

        http
                .headers()
                .frameOptions()
                .disable()

        return http
                .authorizeExchange()
                .pathMatchers("/api/v1/register").permitAll()
                .pathMatchers("/api/v1/activate").permitAll()
                .pathMatchers("/api/v1/authenticate").permitAll()
                .pathMatchers("/api/v1/account/password-reset/init").permitAll()
                .pathMatchers("/api/v1/account/password-reset/finish").permitAll()
                .and()
                .build()

    }

    @Bean
    fun userDetailsRepository(): MapReactiveUserDetailsService {
        val admin = User.withUsername("admin").password("123").roles("ADMIN").build()
        val ciazhar = User.withUsername("ciazhar").password("123").roles("USER").build()
        return MapReactiveUserDetailsService(admin, ciazhar)
    }


}