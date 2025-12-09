package com.golfpvcc.peep.domain.exception

import com.golfpvcc.peep.domain.type.ChatMessageId

class MessageNotFoundException(
    private val id: ChatMessageId
): RuntimeException(
    "Message with ID $id not found"
)