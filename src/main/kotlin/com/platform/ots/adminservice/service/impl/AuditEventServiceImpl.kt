package com.platform.ots.adminservice.service.impl

import com.platform.ots.adminservice.repository.PersistentAuditEventRepository
import com.platform.ots.adminservice.service.AuditEventService
import com.platform.ots.adminservice.service.mapper.AuditEventConverter
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Sort
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import reactor.core.publisher.toMono
import java.time.LocalDateTime

@Service
@Transactional
class AuditEventServiceImpl(val persistentAuditEventRepository: PersistentAuditEventRepository,
                            val auditEventConverter: AuditEventConverter) : AuditEventService {
    override fun find(sort: String, order: Sort.Direction, page: Int, limit: Int, fromDate: LocalDateTime, toDate: LocalDateTime) {
        PageRequest.of(page, limit, Sort.by(order, sort)).toMono().map { }
    }

    override fun find(id: Long) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun find(sort: String, order: Sort.Direction, page: Int, limit: Int) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}