package com.platform.ots.adminservice.service

import org.springframework.data.domain.Sort
import java.time.LocalDateTime

interface AuditEventService {

    fun find(sort: String, order: Sort.Direction, page: Int, limit: Int)

    fun find(
            sort: String,
            order: Sort.Direction,
            page: Int,
            limit: Int,
            fromDate: LocalDateTime,
            toDate: LocalDateTime
    )

    fun find(id: Long)
}