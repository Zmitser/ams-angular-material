package com.platform.ots.adminservice.service.impl

import com.platform.ots.adminservice.repository.UserRepository
import com.platform.ots.adminservice.service.UserService
import com.platform.ots.adminservice.service.dto.UserDto
import com.platform.ots.adminservice.service.mapper.UserMapper
import org.springframework.stereotype.Service
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

@Service
class UserServiceImpl(val userRepository: UserRepository, val userMapper: UserMapper) : UserService {
    override fun findOne(id: Long): Mono<UserDto> = userRepository.findOne(id)
            .map { userMapper.userToUserDto(it) }


    override fun save(userDto: UserDto): Mono<UserDto> = userRepository.save(userMapper.userDtoToUser(userDto))
            .map { userMapper.userToUserDto(it) }


    override fun delete(id: Long): Mono<Long> = userRepository.delete(id)

    override fun findAll(): Flux<UserDto> = userRepository.findAll().map { userMapper.userToUserDto(it) }
}