package com.platform.ots.adminservice.service.vm

import com.platform.ots.adminservice.service.dto.UserDto

class UsersSmartTableResponse(data: List<UserDto>, total: Long) : SmartTableResponse<UserDto>(data, total)