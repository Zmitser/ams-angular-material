package com.platform.ots.adminservice.util

import com.platform.ots.adminservice.constant.Constants
import org.springframework.boot.SpringApplication
import org.springframework.core.env.Environment

/**
 * Utility class to load a Soring Profile to be used as default
 */
object DefaultProfileUtil {

    fun addDefaultProfile(app: SpringApplication): SpringApplication {
        app.setDefaultProperties(mapOf(Constants.SPRING_DEFAULT_PROFILE to Constants.SPRING_PROFILE_DEVELOPMENT))
        return app
    }

    fun getActiveProfile(environment: Environment): Array<String> {
        val activeProfiles: Array<String> = environment.activeProfiles
        return if (activeProfiles.isEmpty()) activeProfiles else environment.defaultProfiles
    }
}