package com.platform.ots.adminservice.web.response

import org.springframework.boot.actuate.audit.AuditEvent

class AuditEventsSmartTableResponse(data: MutableList<AuditEvent?>, total: Long) : SmartTableResponse<AuditEvent>(data, total)