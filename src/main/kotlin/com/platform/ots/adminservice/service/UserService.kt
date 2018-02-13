package com.platform.ots.adminservice.service

import com.platform.ots.adminservice.service.dto.UserDto
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

interface UserService {

    fun findAll(): Flux<UserDto>

    fun delete(id: Long): Mono<Long>
}