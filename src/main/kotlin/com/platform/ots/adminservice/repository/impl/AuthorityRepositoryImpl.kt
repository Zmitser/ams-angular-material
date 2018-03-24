package com.platform.ots.adminservice.repository.impl

import com.platform.ots.adminservice.domain.Authority
import com.platform.ots.adminservice.repository.AuthorityRepository
import org.springframework.stereotype.Repository
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono
import reactor.core.publisher.toFlux
import reactor.core.publisher.toMono

@Repository
class AuthorityRepositoryImpl(private val proxyAuthorityRepository: ProxyAuthorityRepository) : AuthorityRepository {

    override fun deleteAll(): Mono<Unit> = proxyAuthorityRepository.deleteAll().toMono()

    override fun saveAll(authorities: List<Authority>): Flux<Authority> = proxyAuthorityRepository.saveAll(authorities).toFlux()

    override fun save(authority: Authority): Mono<Authority> = proxyAuthorityRepository.save(authority).toMono()

}