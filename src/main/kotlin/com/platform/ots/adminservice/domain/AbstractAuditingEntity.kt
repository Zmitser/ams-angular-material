package com.platform.ots.adminservice.domain

import org.hibernate.envers.Audited
import org.springframework.data.annotation.CreatedBy
import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.LastModifiedBy
import org.springframework.data.annotation.LastModifiedDate
import org.springframework.data.jpa.domain.support.AuditingEntityListener
import java.io.Serializable
import java.time.Instant
import java.time.Instant.now
import javax.persistence.Column
import javax.persistence.EntityListeners
import javax.persistence.MappedSuperclass

/**
 * Base abstract class for entities which will hold definitions for created, last modified by and created,
 * last modified by date.
 */
@MappedSuperclass
@Audited
@EntityListeners(AuditingEntityListener::class)
abstract class AbstractAuditingEntity(
        @CreatedBy
        @Column(name = "created_by", nullable = false, updatable = false, length = 50)
        var createdBy: String?,
        @CreatedDate
        @Column(name = "created_date", nullable = false)
        var createdDate: Instant?,
        @LastModifiedBy
        @Column(name = "last_modified_by", nullable = false, updatable = true, length = 50)
        var lastModifiedBy: String?,
        @LastModifiedDate
        @Column(name = "last_modified_date", nullable = false)
        var lastModifiedDate: Instant?
) : Serializable {
    constructor() : this(String.toString(), now(), String.toString(), now())
}