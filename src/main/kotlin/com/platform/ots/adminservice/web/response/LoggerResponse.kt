package com.platform.ots.adminservice.web.response

import ch.qos.logback.classic.Logger
import java.io.Serializable

data class LoggerResponse(val name: String, val level: String) : Serializable {
    constructor(logger: Logger) : this(logger.name, logger.effectiveLevel.levelStr)
}