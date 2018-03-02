package com.platform.ots.adminservice.web.service

import com.platform.ots.adminservice.web.dto.UserDto
import com.platform.ots.adminservice.web.vm.UsersSmartTableVM
import org.springframework.data.domain.Sort
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

interface UserService {

    fun findAll(): Flux<UserDto>

    fun delete(id: Long): Mono<Long>

    fun save(userDto: UserDto): Mono<UserDto>

    fun findOne(id: Long): Mono<UserDto>

    fun findAll(sort: String, order: Sort.Direction, page: Int, limit: Int): Mono<UsersSmartTableVM>
}