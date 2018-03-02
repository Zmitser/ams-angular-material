package com.platform.ots.adminservice.web.vm

import org.springframework.boot.actuate.audit.AuditEvent

class AuditEventsSmartTableVM(data: MutableList<AuditEvent?>, total: Long) : SmartTableVM<AuditEvent>(data, total)