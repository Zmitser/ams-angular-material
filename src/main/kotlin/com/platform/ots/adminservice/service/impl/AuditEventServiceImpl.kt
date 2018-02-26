package com.platform.ots.adminservice.service.impl

import com.platform.ots.adminservice.repository.PersistentAuditEventRepository
import com.platform.ots.adminservice.service.AuditEventService
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional
class AuditEventServiceImpl(val persistentAuditEventRepository: PersistentAuditEventRepository) : AuditEventService