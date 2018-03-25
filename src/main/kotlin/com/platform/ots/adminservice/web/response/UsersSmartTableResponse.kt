package com.platform.ots.adminservice.web.response

import com.platform.ots.adminservice.web.dto.UserDto

class UsersSmartTableResponse(data: List<UserDto?>, total: Long) : SmartTableResponse<UserDto>(data, total)