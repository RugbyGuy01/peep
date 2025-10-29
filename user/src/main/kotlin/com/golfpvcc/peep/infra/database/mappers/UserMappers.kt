package com.golfpvcc.peep.infra.database.mappers

import com.golfpvcc.peep.domain.model.User
import com.golfpvcc.peep.infra.database.entities.UserEntity

fun UserEntity.toUser(): User {
    return User(
        id = id!!,
        username = username,
        email = email,
        hasEmailVerified = hasVerifiedEmail
    )
}