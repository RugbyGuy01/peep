package com.golfpvcc.peep.api.mappers

import com.golfpvcc.peep.api.dto.AuthenticatedUserDto
import com.golfpvcc.peep.api.dto.UserDto
import com.golfpvcc.peep.domain.model.AuthenticatedUser
import com.golfpvcc.peep.domain.model.User

fun AuthenticatedUser.toAuthenticatedUserDto(): AuthenticatedUserDto {
    return AuthenticatedUserDto(
        user = user.toUserDto(),
        accessToken = accessToken,
        refreshToken = refreshToken
    )
}

fun User.toUserDto(): UserDto {
    return UserDto(
        id = id,
        email = email,
        username = username,
        hasVerifiedEmail = hasEmailVerified
    )
}