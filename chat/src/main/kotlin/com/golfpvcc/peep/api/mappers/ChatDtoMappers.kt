package com.golfpvcc.peep.api.mappers

import com.golfpvcc.peep.api.dto.ChatDto
import com.golfpvcc.peep.api.dto.ChatMessageDto
import com.golfpvcc.peep.api.dto.ChatParticipantDto
import com.golfpvcc.peep.domain.models.Chat
import com.golfpvcc.peep.domain.models.ChatMessage
import com.golfpvcc.peep.domain.models.ChatParticipant


fun Chat.toChatDto(): ChatDto {
    return ChatDto(
        id = id,
        participants = participants.map {
            it.toChatParticipantDto()
        },
        lastActivityAt = lastActivityAt,
        lastMessage = lastMessage?.toChatMessageDto(),
        creator = creator.toChatParticipantDto()
    )
}

fun ChatMessage.toChatMessageDto(): ChatMessageDto {
    return ChatMessageDto(
        id = id,
        chatId = chatId,
        content = content,
        createdAt = createdAt,
        senderId = sender.userId
    )
}

fun ChatParticipant.toChatParticipantDto(): ChatParticipantDto {
    return ChatParticipantDto(
        userId = userId,
        username = username,
        email = email,
        profilePictureUrl = profilePictureUrl
    )
}