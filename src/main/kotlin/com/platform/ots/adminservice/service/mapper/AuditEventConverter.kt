package com.platform.ots.adminservice.service.mapper

import com.platform.ots.adminservice.domain.PersistentAuditEvent
import org.springframework.boot.actuate.audit.AuditEvent
import org.springframework.security.web.authentication.WebAuthenticationDetails
import org.springframework.stereotype.Component

@Component
class AuditEventConverter {

    fun convertToAuditEvents(persistentAuditEvents: List<PersistentAuditEvent>): List<AuditEvent> {
        checkNotNull(persistentAuditEvents) {
            return emptyList()
        }
        val auditEvents: MutableList<AuditEvent> = mutableListOf()
        persistentAuditEvents.forEach { convertToAuditEvent(it)?.let { it1 -> auditEvents.add(it1) } }
        return auditEvents
    }

    private fun convertToAuditEvent(it: PersistentAuditEvent): AuditEvent? {
        checkNotNull(it) {
            return null
        }
        return AuditEvent(it.auditEventDate, it.principal, it.auditEventType, convertDataObjects(it.data))
    }

    private fun convertDataObjects(data: Map<String, String>): MutableMap<String, Any> {
        val results: MutableMap<String, Any> = mutableMapOf()
        checkNotNull(data) {
            return results
        }
        data.forEach { key, value -> results[key] = value }
        return results
    }

    fun convertDataToStrings(data: Map<String, Any>): MutableMap<String, String> {
        val results: MutableMap<String, String> = mutableMapOf()
        checkNotNull(data) {
            return results
        }
        data.forEach { key, value ->
            when {
                value is WebAuthenticationDetails -> {
                    results["remoteAddress"] = value.remoteAddress
                    results["sessionId"] = value.sessionId
                }
                value != null ->
                    results[key] = value.toString()
                else ->
                    results[key] = "null"
            }
        }
        return results
    }
}