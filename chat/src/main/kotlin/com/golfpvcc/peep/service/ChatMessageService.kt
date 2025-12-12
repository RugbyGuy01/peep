package com.golfpvcc.peep.service


import com.golfpvcc.peep.domain.event.MessageDeletedEvent
import com.golfpvcc.peep.domain.events.chat.ChatEvent
import com.golfpvcc.peep.domain.exception.ChatNotFoundException
import com.golfpvcc.peep.domain.exception.ChatParticipantNotFoundException
import com.golfpvcc.peep.domain.exception.ForbiddenException
import com.golfpvcc.peep.domain.exception.MessageNotFoundException
import com.golfpvcc.peep.domain.models.ChatMessage
import com.golfpvcc.peep.domain.type.ChatId
import com.golfpvcc.peep.domain.type.ChatMessageId
import com.golfpvcc.peep.domain.type.UserId
import com.golfpvcc.peep.infra.database.entities.ChatMessageEntity
import com.golfpvcc.peep.infra.database.repositories.ChatMessageRepository
import com.golfpvcc.peep.infra.database.repositories.ChatParticipantRepository
import com.golfpvcc.peep.infra.database.repositories.ChatRepository
import com.golfpvcc.peep.infra.database.mappers.toChatMessage
import com.golfpvcc.peep.infra.message_queue.EventPublisher
import org.springframework.cache.annotation.CacheEvict
import org.springframework.context.ApplicationEventPublisher
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class ChatMessageService(
    private val chatRepository: ChatRepository,
    private val chatMessageRepository: ChatMessageRepository,
    private val chatParticipantRepository: ChatParticipantRepository,
    private val applicationEventPublisher: ApplicationEventPublisher,
    private val eventPublisher: EventPublisher
) {

    @Transactional
    @CacheEvict(
        value = ["messages"],
        key = "#chatId",
    )
    fun sendMessage(
        chatId: ChatId,
        senderId: UserId,
        content: String,
        messageId: ChatMessageId? = null
    ): ChatMessage {
        val chat = chatRepository.findChatById(chatId, senderId)
            ?: throw ChatNotFoundException()
        val sender = chatParticipantRepository.findByIdOrNull(senderId)
            ?: throw ChatParticipantNotFoundException(senderId)

        val savedMessage = chatMessageRepository.saveAndFlush(
            ChatMessageEntity(
                id = messageId,
                content = content.trim(),
                chatId = chatId,
                chat = chat,
                sender = sender
            )
        )

        eventPublisher.publish(
            event = ChatEvent.NewMessage(
                senderId = sender.userId,
                senderUsername = sender.username,
                recipientIds = chat.participants.map { it.userId }.toSet(),
                chatId = chatId,
                message = savedMessage.content
            )
        )

        return savedMessage.toChatMessage()
    }

    @Transactional
    fun deleteMessage(
        messageId: ChatMessageId,
        requestUserId: UserId
    ) {
        val message = chatMessageRepository.findByIdOrNull(messageId)
            ?: throw MessageNotFoundException(messageId)

        if(message.sender.userId != requestUserId) {
            throw ForbiddenException()
        }

        chatMessageRepository.delete(message)

        applicationEventPublisher.publishEvent(
            MessageDeletedEvent(
                chatId = message.chatId,
                messageId = messageId
            )
        )

        evictMessagesCache(message.chatId)
    }

    @CacheEvict(
        value = ["messages"],
        key = "#chatId",
    )
    fun evictMessagesCache(chatId: ChatId) {
        // NO-OP: Let Spring handle the cache evict
    }
}