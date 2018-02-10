package com.platform.ots.adminservice.handler

import com.platform.ots.adminservice.repository.UserRepository
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.server.ServerRequest
import org.springframework.web.reactive.function.server.ServerResponse
import org.springframework.web.reactive.function.server.ServerResponse.ok
import org.springframework.web.reactive.function.server.body
import reactor.core.publisher.Mono

@Component
class UserHandler(val userRepository: UserRepository) {

    fun findAll(request: ServerRequest): Mono<ServerResponse> = ok().body(userRepository.findAll())
}