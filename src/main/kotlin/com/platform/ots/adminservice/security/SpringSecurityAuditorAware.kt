package com.platform.ots.adminservice.security

import com.platform.ots.adminservice.constant.Constants
import org.springframework.data.domain.AuditorAware
import org.springframework.stereotype.Component
import java.util.*

/**
 * Implementation of AuditorAware based on Spring Security.
 */

@Component
class SpringSecurityAuditorAware : AuditorAware<String> {

    override fun getCurrentAuditor(): Optional<String> {
        return Optional.of(SecurityUtils.getCurrentUserLogin() ?: Constants.SYSTEM_ACCOUNT)
    }
}