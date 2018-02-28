package com.platform.ots.adminservice.repository

import com.platform.ots.adminservice.domain.PersistentAuditEvent
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono
import java.time.Instant

interface PersistentAuditEventRepository {

    fun find(principal: String): Flux<PersistentAuditEvent>

    fun find(id: Long): Mono<PersistentAuditEvent>

    fun find(pageable: Pageable): Mono<Page<PersistentAuditEvent>>

    fun find(after: Instant): Flux<PersistentAuditEvent>

    fun find(principal: String, after: Instant): Flux<PersistentAuditEvent>

    fun find(principal: String, after: Instant, type: String): Flux<PersistentAuditEvent>

    fun find(fromDate: Instant, toDate: Instant, pageable: Pageable): Mono<Page<PersistentAuditEvent>>
}