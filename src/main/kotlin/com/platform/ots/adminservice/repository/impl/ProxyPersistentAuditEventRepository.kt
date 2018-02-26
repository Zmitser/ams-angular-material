package com.platform.ots.adminservice.repository.impl

import com.platform.ots.adminservice.domain.PersistentAuditEvent
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import java.time.LocalDateTime

interface ProxyPersistentAuditEventRepository : JpaRepository<PersistentAuditEvent, Long> {

    @Query("SELECT pae FROM PersistentAuditEvent pae WHERE pae.principal=:principal")
    fun find(@Param("principal") principal: String): List<PersistentAuditEvent>

    @Query("SELECT pae FROM PersistentAuditEvent pae WHERE pae.auditEventDate >:after")
    fun find(@Param("after") after: LocalDateTime): List<PersistentAuditEvent>

    @Query("SELECT pae FROM PersistentAuditEvent pae WHERE pae.principal =:principal AND pae.auditEventDate >:after")
    fun find(@Param("principal") principal: String, @Param("after") after: LocalDateTime): List<PersistentAuditEvent>

    @Query("SELECT pae FROM PersistentAuditEvent pae WHERE pae.principal =:principal AND pae.auditEventType=:type AND pae.auditEventDate >:after")
    fun find(@Param("principal") principal: String, @Param("after") after: LocalDateTime, @Param("type") type: String): List<PersistentAuditEvent>

    @Query("SELECT pae FROM PersistentAuditEvent pae WHERE pae.auditEventDate BETWEEN :fromDate AND :toDate")
    fun find(@Param("fromDate") fromDate: LocalDateTime, @Param("toDate") toDate: LocalDateTime, pageable: Pageable): Page<PersistentAuditEvent>


}