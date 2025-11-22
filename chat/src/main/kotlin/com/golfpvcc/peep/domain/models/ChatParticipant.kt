package com.golfpvcc.peep.domain.models

import com.golfpvcc.peep.domain.type.UserId

data class ChatParticipant(
    val userId: UserId,
    val username: String,
    val email: String,
    val profilePictureUrl: String?
)