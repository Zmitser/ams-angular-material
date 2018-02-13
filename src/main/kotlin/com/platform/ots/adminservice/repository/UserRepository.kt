package com.platform.ots.adminservice.repository

import com.platform.ots.adminservice.domain.User
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

interface UserRepository {

    fun findAll(): Flux<User>

    fun deleteAll(): Mono<Unit>

    fun save(user: User): Mono<User>

    fun delete(id: Long): Mono<Long>
}