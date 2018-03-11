package com.platform.ots.adminservice.repository.impl

import com.platform.ots.adminservice.domain.User
import org.springframework.cache.annotation.Cacheable
import org.springframework.data.jpa.repository.EntityGraph
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param


interface ProxyUserRepository : JpaRepository<User, Long> {

    @Query("SELECT u FROM User u where u.email=:email")
    @EntityGraph(attributePaths = ["authorities"])
    @Cacheable(cacheNames = ["usersByEmailCache"])
    fun findOneByEmail(@Param("email") email: String): User
}