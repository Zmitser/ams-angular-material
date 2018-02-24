package com.platform.ots.adminservice.service.vm

import ch.qos.logback.classic.Logger
import java.io.Serializable

data class LoggerVM(val name: String, val level: String) : Serializable {
    constructor(logger: Logger) : this(logger.name, logger.effectiveLevel.levelStr)
}