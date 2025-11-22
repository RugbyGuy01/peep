package com.golfpvcc.peep.domain.models

import com.golfpvcc.peep.domain.type.ChatId
import com.golfpvcc.peep.domain.type.ChatMessageId
import java.time.Instant

data class ChatMessage(
    val id: ChatMessageId,
    val chatId: ChatId,
    val sender: ChatParticipant,
    val content: String,
    val createdAt: Instant
)