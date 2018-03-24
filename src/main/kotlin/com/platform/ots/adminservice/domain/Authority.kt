package com.platform.ots.adminservice.domain

import org.hibernate.annotations.Cache
import org.hibernate.annotations.CacheConcurrencyStrategy.NONSTRICT_READ_WRITE
import java.io.Serializable
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.Table
import javax.validation.constraints.NotNull
import javax.validation.constraints.Size

/**
 * An authority (a security role) used by Spring Security.
 */
@Entity
@Table(name = "authority")
@Cache(usage = NONSTRICT_READ_WRITE)
data class Authority(
        @NotNull
        @Size(max = 50)
        @Id
        @Column(name = "name", length = 50)
        var name: String?
) : Serializable {
    constructor() : this(null)
}