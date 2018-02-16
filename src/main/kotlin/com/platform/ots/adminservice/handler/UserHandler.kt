package com.platform.ots.adminservice.handler

import com.platform.ots.adminservice.service.UserService
import com.platform.ots.adminservice.service.dto.UserDto
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.server.ServerRequest
import org.springframework.web.reactive.function.server.ServerResponse
import org.springframework.web.reactive.function.server.ServerResponse.ok
import org.springframework.web.reactive.function.server.body
import reactor.core.publisher.Mono

@Component
class UserHandler(val userService: UserService) {

    fun findAll(request: ServerRequest): Mono<ServerResponse> = ok().body(userService.findAll())

    fun delete(request: ServerRequest): Mono<ServerResponse> = ok().body(userService.delete(request.pathVariable("id").toLong()))

    fun save(request: ServerRequest): Mono<ServerResponse> {
        return request.bodyToMono(UserDto::class.java)
                .map { userService.save(it) }
                .flatMap { ServerResponse.status(HttpStatus.CREATED).body(it) }
    }

    fun findOne(request: ServerRequest): Mono<ServerResponse> = ok().body(userService.findOne(request.pathVariable("id").toLong()))

}