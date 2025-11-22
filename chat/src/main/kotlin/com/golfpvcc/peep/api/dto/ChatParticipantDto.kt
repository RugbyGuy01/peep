package com.golfpvcc.peep.api.dto

import com.golfpvcc.peep.domain.type.UserId

data class ChatParticipantDto(
    val userId: UserId,
    val username: String,
    val email: String,
    val profilePictureUrl: String?
)