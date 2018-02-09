package com.platform.ots.adminservice.router

import com.platform.ots.adminservice.handler.HelloHandler
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.MediaType
import org.springframework.web.reactive.function.server.router

@Configuration
class HelloRouter(val helloHandler: HelloHandler) {

    @Bean
    fun apis() = router {
        (accept(MediaType.APPLICATION_JSON) and "/api/v1/greetings").nest {
            GET("", helloHandler::displayHelloWorld)
        }
    }
}