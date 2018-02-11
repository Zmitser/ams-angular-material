package com.platform.ots.adminservice.bootstrap

import com.platform.ots.adminservice.domain.User
import com.platform.ots.adminservice.repository.UserRepository
import io.github.benas.randombeans.api.EnhancedRandom.random
import mu.KotlinLogging
import org.springframework.boot.CommandLineRunner
import org.springframework.stereotype.Component
import reactor.core.publisher.toFlux

@Component
class BootstrapCLR(val userRepository: UserRepository) : CommandLineRunner {

    val log = KotlinLogging.logger { }

    override fun run(vararg args: String?) {
        userRepository.deleteAll()
                .thenMany(arrayOf(
                        random(User::class.java),
                        random(User::class.java),
                        random(User::class.java))
                        .toFlux()
                        .flatMap { userRepository.save(it) }
                ).subscribe { log.debug { it } }
    }
}