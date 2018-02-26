package com.platform.ots.adminservice.repository

import com.platform.ots.adminservice.domain.PersistentAuditEvent
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono
import java.time.LocalDateTime

interface PersistentAuditEventRepository {

    fun find(principal: String): Flux<PersistentAuditEvent>

    fun find(id: Long): Mono<PersistentAuditEvent>

    fun find(pageable: Pageable): Mono<Page<PersistentAuditEvent>>

    fun find(after: LocalDateTime): Flux<PersistentAuditEvent>

    fun find(principal: String, after: LocalDateTime): Flux<PersistentAuditEvent>

    fun find(principal: String, after: LocalDateTime, type: String): Flux<PersistentAuditEvent>

    fun find(fromDate: LocalDateTime, toDate: LocalDateTime, pageable: Pageable): Mono<Page<PersistentAuditEvent>>
}