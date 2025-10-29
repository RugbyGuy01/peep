package com.golfpvcc.peep.service.auth

import com.golfpvcc.peep.domain.exception.UserAlreadyExistsException
import com.golfpvcc.peep.domain.model.User
import com.golfpvcc.peep.infra.database.entities.UserEntity
import com.golfpvcc.peep.infra.database.mappers.toUser
import com.golfpvcc.peep.infra.database.repositories.UserRepository
import com.golfpvcc.peep.infra.security.PasswordEncoder
import org.springframework.stereotype.Service


@Service
class AuthService(
    private val userRepository: UserRepository,
    private val passwordEncoder: PasswordEncoder
) {

    fun register(email: String, username: String, password: String): User {
        val user = userRepository.findByEmailOrUsername(
            email = email.trim(),
            username = username.trim()
        )
        if(user != null) {
            throw UserAlreadyExistsException()
        }

        val savedUser = userRepository.save(
            UserEntity(
                email = email.trim(),
                username = username.trim(),
                hashedPassword = passwordEncoder.encode(password)
            )
        ).toUser()

        return savedUser
    }
}