package com.platform.ots.adminservice.repository

import com.platform.ots.adminservice.domain.User
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

interface UserRepository {

    fun findAll(): Flux<User>

    fun findAll(pageable: Pageable): Mono<Page<User>>

    fun deleteAll(): Mono<Unit>

    fun save(user: User): Mono<User>

    fun saveAll(users: List<User>): Flux<User>

    fun delete(id: Long): Mono<Long>

    fun findOne(id: Long): Mono<User>

    fun findOneByEmail(email: String?): Mono<User>

    fun findOneByUsername(username: String?): Mono<User>

    fun findOneByUsernameOrEmail(usernameOrEmail: String?): Mono<User>
}