package com.golfpvcc.peep.domain.event

import com.golfpvcc.peep.domain.type.ChatId
import com.golfpvcc.peep.domain.type.ChatMessageId

data class MessageDeletedEvent(
    val chatId: ChatId,
    val messageId: ChatMessageId,
)