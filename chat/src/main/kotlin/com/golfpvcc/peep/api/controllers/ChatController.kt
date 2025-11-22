package com.golfpvcc.peep.api.controllers

import com.golfpvcc.peep.api.dto.ChatDto
import com.golfpvcc.peep.api.dto.CreateChatRequest
import com.golfpvcc.peep.api.mappers.toChatDto
import com.golfpvcc.peep.api.util.requestUserId
import com.golfpvcc.peep.service.ChatService
import jakarta.validation.Valid
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/chat")
class ChatController(
    private val chatService: ChatService
) {

    @PostMapping
    fun createChat(
        @Valid @RequestBody body: CreateChatRequest
    ): ChatDto {
        return chatService.createChat(
            creatorId = requestUserId,
            otherUserIds = body.otherUserIds.toSet()
        ).toChatDto()
    }
}