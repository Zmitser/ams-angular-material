package com.platform.ots.adminservice.security

import com.platform.ots.adminservice.repository.UserRepository
import mu.KLogger
import mu.KotlinLogging
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.stereotype.Service
import java.util.Locale.ENGLISH

@Service
class DomainUserDetailsService(val userRepository: UserRepository) : UserDetailsService {

    val log: KLogger = KotlinLogging.logger { }

    override fun loadUserByUsername(username: String?): UserDetails? {
        log.debug { "Authenticating $username" }
        val lowerCaseLogin: String? = username?.toLowerCase(ENGLISH)
        return null
    }
}