package com.platform.ots.adminservice.service.vm

import java.io.Serializable

abstract class SmartTableResponse<out T>(val data: List<T>?, val total: Long?) : Serializable