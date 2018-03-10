package com.platform.ots.adminservice.domain

import org.hibernate.annotations.Cache
import org.hibernate.annotations.CacheConcurrencyStrategy.NONSTRICT_READ_WRITE
import java.time.LocalDateTime
import javax.persistence.*
import javax.persistence.GenerationType.IDENTITY
import javax.validation.constraints.Email
import javax.validation.constraints.NotNull
import javax.validation.constraints.Size

@Entity
@Table(name = "user")
@Cache(usage = NONSTRICT_READ_WRITE)
data class User(
        @Id
        @GeneratedValue(strategy = IDENTITY)
        var id: Long?,
        @NotNull
        @Size(max = 60, min = 2)
        @Column(name = "first_name", length = 60)
        var firstName: String?,
        @NotNull
        @Size(min = 2, max = 60)
        @Column(name = "last_name", length = 60)
        var lastName: String?,
        @NotNull
        @Size(min = 5, max = 80)
        @Column(name = "username", length = 80)
        var userName: String?,
        @NotNull
        @Email
        @Size(min = 5, max = 80)
        @Column(name = "email", unique = true, length = 80)
        var email: String?,
        @NotNull
        @Size(min = 10, max = 60)
        @Column(name = "password", length = 60)
        var password: String?,
        @Column(name = "create_date")
        var createDate: LocalDateTime?) : AbstractAuditingEntity() {
    constructor() : this(null, null, null, null, null, null, null)
}