package com.platform.ots.adminservice.config

import com.platform.ots.adminservice.filter.CorsFilter
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity
import org.springframework.security.config.web.server.ServerHttpSecurity
import org.springframework.security.core.userdetails.MapReactiveUserDetailsService
import org.springframework.security.core.userdetails.User.withUsername
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.web.server.SecurityWebFilterChain


@Configuration
@EnableWebFluxSecurity
class SecurityModuleConfiguration(val corsFilter: CorsFilter) {


    @Bean
    fun passwordEncoder(): PasswordEncoder = BCryptPasswordEncoder()


    @Bean
    fun springSecurityFilterChain(http: ServerHttpSecurity): SecurityWebFilterChain {
        return http.authorizeExchange()
                .pathMatchers("/api/v1/users")
                .permitAll()
                .anyExchange()
                .authenticated()
                .and()
                .build()
    }

    @Bean
    fun userDetailsRepository(): MapReactiveUserDetailsService {
        val user = withUsername("user")
                .password("\$2a\$10\$pwxohHsbtd8tvGC1QGx/pul6OSS0lVeiTWXhtjefHHhLy75KKF8Gu")
                .roles("USER")
                .build()
        val admin = withUsername("admin")
                .password("\$2a\$10\$pwxohHsbtd8tvGC1QGx/pul6OSS0lVeiTWXhtjefHHhLy75KKF8Gu")
                .roles("ADMIN", "USER")
                .build()
        return MapReactiveUserDetailsService(user, admin)
    }


}