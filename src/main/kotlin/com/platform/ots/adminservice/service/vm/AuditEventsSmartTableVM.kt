package com.platform.ots.adminservice.service.vm

import org.springframework.boot.actuate.audit.AuditEvent

class AuditEventsSmartTableVM(data: MutableList<AuditEvent?>, total: Long) : SmartTableVM<AuditEvent>(data, total)