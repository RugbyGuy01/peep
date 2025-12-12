package com.golfpvcc.peep.service

import com.golfpvcc.peep.domain.models.ChatParticipant
import com.golfpvcc.peep.domain.type.UserId
import com.golfpvcc.peep.infra.database.repositories.ChatParticipantRepository
import com.golfpvcc.peep.infra.database.mappers.toChatParticipant
import com.golfpvcc.peep.infra.database.mappers.toChatParticipantEntity
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service

@Service
class ChatParticipantService(
    private val chatParticipantRepository: ChatParticipantRepository,
) {

    fun createChatParticipant(
        chatParticipant: ChatParticipant
    ) {
        chatParticipantRepository.save(
            chatParticipant.toChatParticipantEntity()
        )
    }

    fun findChatParticipantById(userId: UserId): ChatParticipant? {
        return chatParticipantRepository.findByIdOrNull(userId)?.toChatParticipant()
    }

    fun findChatParticipantByEmailOrUsername(
        query: String
    ): ChatParticipant? {
        val normalizedQuery = query.lowercase().trim()
        return chatParticipantRepository.findByEmailOrUsername(
            query = normalizedQuery
        )?.toChatParticipant()
    }
}