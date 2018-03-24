package com.platform.ots.adminservice.repository

import com.platform.ots.adminservice.domain.Authority
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono


interface AuthorityRepository {

    fun save(authority: Authority): Mono<Authority>

    fun saveAll(authorities: List<Authority>): Flux<Authority>

    fun deleteAll(): Mono<Unit>
}