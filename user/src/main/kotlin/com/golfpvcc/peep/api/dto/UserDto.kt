package com.golfpvcc.peep.api.dto

import com.golfpvcc.peep.domain.type.UserId

data class UserDto(
    val id: UserId,
    val email: String,
    val username: String,
    val hasVerifiedEmail: Boolean,
)