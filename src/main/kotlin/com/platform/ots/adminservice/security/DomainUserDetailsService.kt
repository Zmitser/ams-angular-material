package com.platform.ots.adminservice.security

import com.platform.ots.adminservice.repository.UserRepository
import mu.KLogger
import mu.KotlinLogging
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.ReactiveUserDetailsService
import org.springframework.security.core.userdetails.User
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service
import reactor.core.publisher.Mono
import java.util.Locale.ENGLISH

@Service("domainUserDetailsService")
class DomainUserDetailsService(val userRepository: UserRepository) : ReactiveUserDetailsService {

    val log: KLogger = KotlinLogging.logger { }

    override fun findByUsername(username: String?): Mono<UserDetails> {
        log.debug { "Authenticating $username" }
        val lowerCaseLogin: String? = username?.toLowerCase(ENGLISH)
        return userRepository.findOneByUsernameOrEmail(lowerCaseLogin)
                .switchIfEmpty(Mono.error(UsernameNotFoundException("User $lowerCaseLogin was not found in the database")))
                .map { createSecurityUser(lowerCaseLogin, it) }
    }


    private fun createSecurityUser(lowerCaseLogin: String?, user: com.platform.ots.adminservice.domain.User): User {
        return when {
            user.isActivated -> User(user.userName, user.password, user.authorities.map { SimpleGrantedAuthority(it.name) })
            else -> throw UserNotActivatedException("User $lowerCaseLogin was not activated")
        }
    }
}