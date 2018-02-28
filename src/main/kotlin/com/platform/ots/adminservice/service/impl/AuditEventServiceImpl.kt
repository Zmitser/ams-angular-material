package com.platform.ots.adminservice.service.impl

import com.platform.ots.adminservice.repository.PersistentAuditEventRepository
import com.platform.ots.adminservice.service.AuditEventService
import com.platform.ots.adminservice.service.mapper.AuditEventConverter
import com.platform.ots.adminservice.service.vm.AuditEventsSmartTableVM
import org.springframework.boot.actuate.audit.AuditEvent
import org.springframework.data.domain.PageRequest.of
import org.springframework.data.domain.Sort
import org.springframework.data.domain.Sort.Direction
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import reactor.core.publisher.Mono
import java.time.Instant

@Service
@Transactional
class AuditEventServiceImpl(val persistentAuditEventRepository: PersistentAuditEventRepository,
                            val auditEventConverter: AuditEventConverter) : AuditEventService {
    override fun find(
            sort: String,
            order: Direction,
            page: Int,
            limit: Int,
            fromDate: Instant,
            toDate: Instant
    ): Mono<AuditEventsSmartTableVM> {
        return persistentAuditEventRepository.find(fromDate, toDate, of(page, limit, Sort.by(order, sort)))
                .map {
                    it.map {
                        auditEventConverter.convertToAuditEvent(it)
                    }
                }
                .map {
                    AuditEventsSmartTableVM(it.content, it.totalElements)
                }


    }

    override fun find(id: Long): Mono<AuditEvent> {
        return persistentAuditEventRepository.find(id)
                .map { auditEventConverter.convertToAuditEvent(it) }
    }

    override fun find(
            sort: String,
            order: Direction,
            page: Int,
            limit: Int
    ): Mono<AuditEventsSmartTableVM> {
        return persistentAuditEventRepository.find(of(page, limit, Sort.by(order, sort)))
                .map {
                    it.map {
                        auditEventConverter.convertToAuditEvent(it)
                    }
                }
                .map {
                    AuditEventsSmartTableVM(it.content, it.totalElements)
                }

    }
}