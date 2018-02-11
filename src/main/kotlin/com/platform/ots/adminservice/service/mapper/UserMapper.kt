package com.platform.ots.adminservice.service.mapper

import com.platform.ots.adminservice.domain.User
import com.platform.ots.adminservice.service.dto.UserDto
import org.mapstruct.Mapper

@Mapper(componentModel = "spring")
interface UserMapper {

    fun userToUserDto(user: User): UserDto

    fun userDtoToUser(userDto: UserDto): User
}