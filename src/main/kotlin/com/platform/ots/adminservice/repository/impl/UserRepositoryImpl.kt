package com.platform.ots.adminservice.repository.impl

import com.platform.ots.adminservice.domain.User
import com.platform.ots.adminservice.repository.UserRepository
import mu.KotlinLogging
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Repository
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono
import reactor.core.publisher.toFlux
import reactor.core.publisher.toMono


@Repository
class UserRepositoryImpl(val proxyUserRepository: ProxyUserRepository) : UserRepository {
    override fun findAll(pageable: Pageable): Mono<Page<User>> {
        return proxyUserRepository.findAll(pageable).toMono()
    }

    private val log = KotlinLogging.logger {}

    override fun findOne(id: Long): Mono<User> = proxyUserRepository.findById(id).orElseGet { null }.toMono()

    override fun delete(id: Long): Mono<Long> = id.toMono().doOnSuccess { proxyUserRepository.deleteById(id) }

    override fun save(user: User): Mono<User> {
        log.debug { "Try to save user: $user" }
        return proxyUserRepository.save(user).toMono()
    }

    override fun deleteAll(): Mono<Unit> = proxyUserRepository.deleteAll().toMono()

    override fun findAll(): Flux<User> = proxyUserRepository.findAll().toFlux()

}