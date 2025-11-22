package com.golfpvcc.peep.service

import com.golfpvcc.peep.domain.exception.ChatParticipantNotFoundException
import com.golfpvcc.peep.domain.exception.InvalidChatSizeException
import com.golfpvcc.peep.domain.models.Chat
import com.golfpvcc.peep.domain.type.UserId
import com.golfpvcc.peep.infra.database.entities.ChatEntity
import com.golfpvcc.peep.infra.database.repositories.ChatParticipantRepository
import com.golfpvcc.peep.infra.database.repositories.ChatRepository
import com.golfpvcc.peep.infra.mappers.toChat
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class ChatService(
    private val chatRepository: ChatRepository,
    private val chatParticipantRepository: ChatParticipantRepository,
) {

    @Transactional
    fun createChat(
        creatorId: UserId,
        otherUserIds: Set<UserId>
    ): Chat {
        val otherParticipants = chatParticipantRepository.findByUserIdIn(
            userIds = otherUserIds
        )

        val allParticipants = (otherParticipants + creatorId)
        if(allParticipants.size < 2) {
            throw InvalidChatSizeException()
        }

        val creator = chatParticipantRepository.findByIdOrNull(creatorId)
            ?: throw ChatParticipantNotFoundException(creatorId)

        return chatRepository.save(
            ChatEntity(
                creator = creator,
                participants = setOf(creator) + otherParticipants
            )
        ).toChat(lastMessage = null)
    }
}