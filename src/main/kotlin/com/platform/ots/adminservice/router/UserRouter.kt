package com.platform.ots.adminservice.router

import com.platform.ots.adminservice.handler.UserHandler
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.MediaType.APPLICATION_JSON
import org.springframework.web.reactive.function.server.router

@Configuration
class UserRouter(val userHandler: UserHandler) {

    @Bean
    fun apis() = router {
        (accept(APPLICATION_JSON) and "/api/v1/users").nest {
            GET("", userHandler::findAll)
            DELETE("/{id}", userHandler::delete)
        }
    }
}