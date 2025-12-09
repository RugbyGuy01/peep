package com.golfpvcc.peep.api.dto.ws

import com.golfpvcc.peep.domain.type.ChatId
import com.golfpvcc.peep.domain.type.ChatMessageId

data class SendMessageDto(
    val chatId: ChatId,
    val content: String,
    val messageId: ChatMessageId? = null
)