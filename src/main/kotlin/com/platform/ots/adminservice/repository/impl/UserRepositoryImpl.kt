package com.platform.ots.adminservice.repository.impl

import com.platform.ots.adminservice.domain.User
import com.platform.ots.adminservice.repository.UserRepository
import org.springframework.stereotype.Repository
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono
import reactor.core.publisher.toFlux
import reactor.core.publisher.toMono

@Repository
class UserRepositoryImpl(val proxyUserRepository: ProxyUserRepository) : UserRepository {

    override fun delete(id: Long): Mono<Long> = id.toMono().doOnSuccess { proxyUserRepository.deleteById(id) }

    override fun save(user: User): Mono<User> = proxyUserRepository.save(user).toMono()

    override fun deleteAll(): Mono<Unit> = proxyUserRepository.deleteAll().toMono()

    override fun findAll(): Flux<User> = proxyUserRepository.findAll().toFlux()

}