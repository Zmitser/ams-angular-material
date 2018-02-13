package com.platform.ots.adminservice.domain

import java.time.LocalDateTime
import javax.persistence.*
import javax.persistence.GenerationType.IDENTITY
import javax.validation.constraints.Email
import javax.validation.constraints.NotNull
import javax.validation.constraints.Size

@Entity
@Table(name = "user")
data class User(
        @Id
        @GeneratedValue(strategy = IDENTITY)
        val id: Long?,
        @NotNull
        @Size(max = 60, min = 2)
        @Column(name = "first_name", length = 60)
        val firstName: String?,
        @NotNull
        @Size(min = 2, max = 60)
        @Column(name = "last_name", length = 60)
        val lastName: String?,
        @NotNull
        @Size(min = 5, max = 80)
        @Column(name = "username", length = 80)
        val userName: String?,
        @NotNull
        @Email
        @Size(min = 5, max = 80)
        @Column(name = "email", unique = true, length = 80)
        val email: String?,
        @NotNull
        @Size(min = 10, max = 60)
        @Column(name = "password", length = 60)
        val password: String?,
        @Column(name = "create_date")
        val createDate: LocalDateTime?) {
    constructor() : this(null, null, null, null, null, null, null)
}