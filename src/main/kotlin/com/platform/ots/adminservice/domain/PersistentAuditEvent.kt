package com.platform.ots.adminservice.domain

import java.io.Serializable
import java.time.LocalDateTime
import javax.persistence.*
import javax.persistence.GenerationType.IDENTITY
import javax.validation.constraints.NotNull


@Entity
@Table(name = "persistent_audit_event")
data class PersistentAuditEvent(@Id
                                @GeneratedValue(strategy = IDENTITY)
                                var id: Long?,
                                @NotNull
                                @Column(name = "principal", nullable = false)
                                var principal: String?,
                                @Column(name = "audit_event_date")
                                var auditEventDate: LocalDateTime,
                                @Column(name = "audit_event_type")
                                var auditEventType: String?,
                                @ElementCollection
                                @MapKeyColumn(name = "name")
                                @CollectionTable(
                                        name = "persistent_audit_event_data",
                                        joinColumns = (arrayOf(JoinColumn(name = "event_id")))
                                )
                                var data: Map<String, String> = mapOf()) : Serializable