package com.platform.ots.adminservice.repository.impl

import com.platform.ots.adminservice.domain.Authority
import org.springframework.data.jpa.repository.JpaRepository

interface ProxyAuthorityRepository : JpaRepository<Authority, String>