package com.platform.ots.adminservice.repository.impl

import com.platform.ots.adminservice.domain.PersistentAuditEvent
import com.platform.ots.adminservice.repository.PersistentAuditEventRepository
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Repository
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono
import reactor.core.publisher.toFlux
import reactor.core.publisher.toMono
import java.time.Instant

@Repository
class PersistentAuditEventRepositoryImpl(val proxyPersistentAuditEventRepository: ProxyPersistentAuditEventRepository) : PersistentAuditEventRepository {

    override fun find(pageable: Pageable): Mono<Page<PersistentAuditEvent>> {
        return proxyPersistentAuditEventRepository.findAll(pageable).toMono()
    }

    override fun find(id: Long): Mono<PersistentAuditEvent> {
        return proxyPersistentAuditEventRepository.findById(id).orElse(null).toMono()
    }

    override fun find(after: Instant): Flux<PersistentAuditEvent> {
        return proxyPersistentAuditEventRepository.find(after).toFlux()
    }

    override fun find(principal: String, after: Instant): Flux<PersistentAuditEvent> {
        return proxyPersistentAuditEventRepository.find(principal, after).toFlux()
    }

    override fun find(principal: String, after: Instant, type: String): Flux<PersistentAuditEvent> {
        return proxyPersistentAuditEventRepository.find(principal, after, type).toFlux()
    }

    override fun find(fromDate: Instant, toDate: Instant, pageable: Pageable): Mono<Page<PersistentAuditEvent>> {
        return proxyPersistentAuditEventRepository.find(fromDate, toDate, pageable).toMono()
    }

    override fun find(principal: String): Flux<PersistentAuditEvent> {
        return proxyPersistentAuditEventRepository.find(principal).toFlux()
    }
}