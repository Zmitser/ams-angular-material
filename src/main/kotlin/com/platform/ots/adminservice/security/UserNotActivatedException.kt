package com.platform.ots.adminservice.security

import org.springframework.security.core.AuthenticationException

/**
 * This exception is thrown in case of a not activated user trying to authenticate.
 */
class UserNotActivatedException(message: String, throwable: Throwable?) : AuthenticationException(message, throwable)