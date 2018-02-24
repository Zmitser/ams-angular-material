package com.platform.ots.adminservice

import com.platform.ots.adminservice.constant.Constant
import com.platform.ots.adminservice.util.DefaultProfileUtil
import mu.KotlinLogging
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.core.env.ConfigurableEnvironment
import org.springframework.core.env.Environment
import org.springframework.core.env.get
import java.net.InetAddress
import javax.annotation.PostConstruct


private val log = KotlinLogging.logger { }


@SpringBootApplication
class AdminServiceApplication(val environment: Environment) {


    @PostConstruct
    fun initApplication() {
        val activeProfiles: Array<String> = environment.activeProfiles
        if (activeProfiles.contains(Constant.SPRING_PROFILE_DEVELOPMENT)
                && activeProfiles.contains(Constant.SPRING_PROFILE_PRODUCTION)) {
            log.error {
                """
                    You have misconfigured your application!
                    It should not run with both the '${Constant.SPRING_PROFILE_DEVELOPMENT}'
                    and ${Constant.SPRING_PROFILE_PRODUCTION} profiles at the same time
                """
            }
        }
        if (activeProfiles.contains(Constant.SPRING_PROFILE_DEVELOPMENT)
                && activeProfiles.contains(Constant.SPRING_PROFILE_CLOUD)) {
            log.error {
                """
                    You have misconfigured your application!
                    It should not run with both the '${Constant.SPRING_PROFILE_DEVELOPMENT}'
                    and ${Constant.SPRING_PROFILE_CLOUD} profiles at the same time
                """
            }
        }
    }
}

fun main(args: Array<String>) {
    val initialApp = SpringApplication(AdminServiceApplication::class.java)
    val app: SpringApplication = DefaultProfileUtil.addDefaultProfile(initialApp)
    val environment: ConfigurableEnvironment = app.run(*args).environment
    val hasServerSslKeystore: Boolean = !environment.getProperty(Constant.SERVER_SSL_KEYSTORE).isNullOrEmpty()
    val protocol: String = if (hasServerSslKeystore) Constant.HTTPS else Constant.HTTP
    log.debug {
        """
            -------------------------------------------------------------------------
            1)Application: '${environment[Constant.SPRING_APPLICATION_NAME]}' is running!
            2)Access URLs:
            2.1)Local: $protocol://localhost:${environment[Constant.SERVER_PORT]}
            2.2)External: $protocol://${InetAddress.getLocalHost().hostAddress}:${environment[Constant.SERVER_PORT]}
            3)Profiles: ${environment.activeProfiles.contentToString()}
            -------------------------------------------------------------------------
        """
    }
}
