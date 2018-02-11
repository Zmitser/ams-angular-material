package com.platform.ots.adminservice.config

import com.platform.ots.adminservice.aop.logging.LoggingAspect
import com.platform.ots.adminservice.constant.Constant
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.EnableAspectJAutoProxy
import org.springframework.context.annotation.Profile
import org.springframework.core.env.Environment

@Configuration
@EnableAspectJAutoProxy
class AspectLoggingConfiguration {

    @Bean
    @Profile(Constant.SPRING_PROFILE_DEVELOPMENT)
    fun loggingAspect(environment: Environment): LoggingAspect = LoggingAspect(environment)

}