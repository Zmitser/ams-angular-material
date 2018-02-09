package com.platform.ots.adminservice.handler

import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.server.ServerRequest
import org.springframework.web.reactive.function.server.ServerResponse
import org.springframework.web.reactive.function.server.body
import reactor.core.publisher.Mono
import reactor.core.publisher.toMono

@Component
class HelloHandler {

    fun displayHelloWorld(req: ServerRequest): Mono<ServerResponse> = ServerResponse.ok().body("Hello World".toMono())
}