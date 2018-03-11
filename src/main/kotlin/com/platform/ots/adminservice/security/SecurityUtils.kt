package com.platform.ots.adminservice.security

import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.context.SecurityContextHolder.getContext
import org.springframework.security.core.userdetails.UserDetails


/**
 * Utility class for Spring Security.
 */
object SecurityUtils {

    /**
     * Get the login of the current user.
     *
     * @return the login of the current user
     */
    fun getCurrentUserLogin(): String? {
        val principal: Any? = getContext().authentication.principal
        return when (principal) {
            is UserDetails -> principal.username
            is String -> principal
            else -> null
        }
    }

    /**
     * Check if a user is authenticated.
     *
     * @return true if the user is authenticated, false otherwise
     */

    fun isAuthenticated(): Boolean {
        val authorities: MutableCollection<out GrantedAuthority> = getContext().authentication.authorities
        return authorities.none {
            it.authority == AuthorizationConstants.ANONYMOUS
        }
    }

    /**
     * If the current user has a specific authority (security role).
     * <p>
     * The name of this method comes from the isUserInRole() method in the Servlet API
     *
     * @param authority the authority to check
     * @return true if the current user has the authority, false otherwise
     */
    fun isCurrentUserRole(authority: String): Boolean {
        val authorities: MutableCollection<out GrantedAuthority> = getContext().authentication.authorities
        return authorities.any {
            it.authority == authority
        }
    }
}