package com.platform.ots.adminservice.web.vm

import com.platform.ots.adminservice.web.dto.UserDto

class UsersSmartTableVM(data: List<UserDto?>, total: Long) : SmartTableVM<UserDto>(data, total)