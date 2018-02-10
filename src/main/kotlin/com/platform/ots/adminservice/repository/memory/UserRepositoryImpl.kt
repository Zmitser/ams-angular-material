package com.platform.ots.adminservice.repository.memory

import com.platform.ots.adminservice.domain.User
import com.platform.ots.adminservice.repository.UserRepository
import org.springframework.stereotype.Repository
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono
import reactor.core.publisher.toFlux
import reactor.core.publisher.toMono

@Repository
class UserRepositoryImpl : UserRepository {

    val storage: MutableList<User> = mutableListOf()
    override fun save(user: User): Mono<User> {
        storage.add(user)
        return user.toMono()
    }

    override fun deleteAll(): Mono<Unit> = storage.toMono().map { it.clear() }

    override fun findAll(): Flux<User> = storage.toList().toFlux()

}