package com.golfpvcc.peep.api.dto

import com.golfpvcc.peep.domain.type.ChatId
import com.golfpvcc.peep.domain.type.ChatMessageId
import com.golfpvcc.peep.domain.type.UserId
import java.time.Instant

data class ChatMessageDto(
    val id: ChatMessageId,
    val chatId: ChatId,
    val content: String,
    val createdAt: Instant,
    val senderId: UserId
)