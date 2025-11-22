package com.golfpvcc.peep.domain.exception

import com.golfpvcc.peep.domain.type.UserId

class ChatParticipantNotFoundException(
    private val id: UserId
): RuntimeException(
    "The chat participant with the ID $id was not found."
)