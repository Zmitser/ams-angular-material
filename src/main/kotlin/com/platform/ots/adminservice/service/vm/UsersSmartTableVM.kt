package com.platform.ots.adminservice.service.vm

import com.platform.ots.adminservice.service.dto.UserDto

class UsersSmartTableVM(data: List<UserDto>, total: Long) : SmartTableVM<UserDto>(data, total)