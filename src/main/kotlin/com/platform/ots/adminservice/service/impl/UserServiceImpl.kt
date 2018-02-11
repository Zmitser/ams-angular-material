package com.platform.ots.adminservice.service.impl

import com.platform.ots.adminservice.repository.UserRepository
import com.platform.ots.adminservice.service.UserService
import com.platform.ots.adminservice.service.dto.UserDto
import com.platform.ots.adminservice.service.mapper.UserMapper
import org.springframework.stereotype.Service
import reactor.core.publisher.Flux

@Service
class UserServiceImpl(val userRepository: UserRepository, val userMapper: UserMapper) : UserService {

    override fun findAll(): Flux<UserDto> = userRepository.findAll().map { userMapper.userToUserDto(it) }
}