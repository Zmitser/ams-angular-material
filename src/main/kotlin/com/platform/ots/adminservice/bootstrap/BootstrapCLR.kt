package com.platform.ots.adminservice.bootstrap

import com.platform.ots.adminservice.domain.Authority
import com.platform.ots.adminservice.domain.User
import com.platform.ots.adminservice.repository.AuthorityRepository
import com.platform.ots.adminservice.repository.UserRepository
import io.github.benas.randombeans.api.EnhancedRandom.random
import mu.KotlinLogging
import org.springframework.boot.CommandLineRunner
import org.springframework.stereotype.Component
import java.time.LocalDateTime.now

@Component
class BootstrapCLR(val userRepository: UserRepository,
                   val authorityRepository: AuthorityRepository) : CommandLineRunner {

    val log = KotlinLogging.logger { }

    override fun run(vararg args: String?) {
        authorityRepository.deleteAll()
                .thenMany(
                        authorityRepository.saveAll(
                                mutableListOf(
                                        Authority("ROLE_USER"),
                                        Authority("ROLE_ADMIN")
                                ))
                )
                .thenMany(
                        userRepository.deleteAll()
                                .thenMany(userRepository.saveAll(mutableListOf(
                                        random(User::class.java),
                                        random(User::class.java),
                                        random(User::class.java),
                                        random(User::class.java),
                                        random(User::class.java),
                                        random(User::class.java),
                                        random(User::class.java),
                                        random(User::class.java),
                                        random(User::class.java),
                                        random(User::class.java),
                                        random(User::class.java),
                                        User(
                                                1000,
                                                "Zmitser",
                                                "Koskinen",
                                                "zmitser",
                                                "gagat@tut.by",
                                                "\$2a\$10\$dB0EOlaVyTF8Gd7nc4NOmOU3.mhIK7M09JZoOXzBWwZMXhQ5Ep.Jm",
                                                now(),
                                                true,
                                                mutableSetOf(Authority("ROLE_USER"))
                                        ),
                                        User(
                                                1001,
                                                "Zmitser",
                                                "Lisitsin",
                                                "admin",
                                                "drwoland@tut.by",
                                                "\$2a\$10\$dB0EOlaVyTF8Gd7nc4NOmOU3.mhIK7M09JZoOXzBWwZMXhQ5Ep.Jm",
                                                now(),
                                                true,
                                                mutableSetOf(Authority("ROLE_USER"), Authority("ROLE_ADMIN"))
                                        )))
                                ))
                .subscribe { log.debug { it } }
    }
}