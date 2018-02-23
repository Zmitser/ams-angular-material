package com.platform.ots.adminservice.repository.impl

import com.platform.ots.adminservice.domain.User
import org.springframework.data.jpa.repository.JpaRepository


interface ProxyUserRepository : JpaRepository<User, Long>