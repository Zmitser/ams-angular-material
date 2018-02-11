package com.platform.ots.adminservice.service

import com.platform.ots.adminservice.service.dto.UserDto
import reactor.core.publisher.Flux

interface UserService {

    fun findAll(): Flux<UserDto>
}