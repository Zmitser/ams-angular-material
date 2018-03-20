package com.platform.ots.adminservice.security

import com.platform.ots.adminservice.repository.UserRepository
import mu.KLogger
import mu.KotlinLogging
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.ReactiveUserDetailsService
import org.springframework.security.core.userdetails.User
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UsernameNotFoundException
import reactor.core.publisher.Mono
import java.util.Locale.ENGLISH

//@Service("domainUserDetailsService")
class DomainUserDetailsService(val userRepository: UserRepository) : ReactiveUserDetailsService {


    override fun findByUsername(username: String?): Mono<UserDetails> {
        log.debug { "Authenticating $username" }
        val lowerCaseLogin: String? = username?.toLowerCase(ENGLISH)

        return userRepository.findOneByEmail(lowerCaseLogin).map {
            checkNotNull(it) {
                throw UsernameNotFoundException("User $lowerCaseLogin was not found in the database")
            }
            createSecurityUser(lowerCaseLogin, it)
        }
    }

    val log: KLogger = KotlinLogging.logger { }




    private fun createSecurityUser(lowerCaseLogin: String?, user: com.platform.ots.adminservice.domain.User): User {
        return when {
            user.isActivated -> User(user.userName, user.password, user.authorities.map { SimpleGrantedAuthority(it.name) })
            else -> throw UserNotActivatedException("User $lowerCaseLogin was not activated")
        }
    }
}