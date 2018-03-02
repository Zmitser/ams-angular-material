package com.platform.ots.adminservice.web.vm

import java.io.Serializable

abstract class SmartTableVM<out T>(val data: List<T?>, val total: Long?) : Serializable