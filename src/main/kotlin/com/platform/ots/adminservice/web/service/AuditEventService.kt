package com.platform.ots.adminservice.web.service

import com.platform.ots.adminservice.web.vm.AuditEventsSmartTableVM
import org.springframework.boot.actuate.audit.AuditEvent
import org.springframework.data.domain.Sort
import reactor.core.publisher.Mono
import java.time.Instant

interface AuditEventService {

    fun find(sort: String, order: Sort.Direction, page: Int, limit: Int): Mono<AuditEventsSmartTableVM>

    fun find(
            sort: String,
            order: Sort.Direction,
            page: Int,
            limit: Int,
            fromDate: Instant,
            toDate: Instant
    ): Mono<AuditEventsSmartTableVM>

    fun find(id: Long): Mono<AuditEvent>
}