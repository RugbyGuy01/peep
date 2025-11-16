package com.golfpvcc.peep.domain.model

import com.golfpvcc.peep.domain.type.UserId



data class User(
    val id: UserId,
    val username: String,
    val email: String,
    val hasEmailVerified: Boolean
)