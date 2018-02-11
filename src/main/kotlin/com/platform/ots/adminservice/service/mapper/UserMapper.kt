package com.platform.ots.adminservice.service.mapper

import com.platform.ots.adminservice.domain.User
import com.platform.ots.adminservice.service.dto.UserDto
import org.springframework.stereotype.Component

@Component
class UserMapper {

    fun userToUserDtoConverter(user: User): UserDto {
        val userDto = UserDto()
        userDto.id = user.id
        userDto.firstName = user.firstName
        userDto.lastName = user.lastName
        userDto.userName = user.userName
        userDto.password = user.password
        userDto.email = user.email
        return userDto
    }

}