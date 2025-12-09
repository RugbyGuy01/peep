package com.golfpvcc.peep.domain.event

import com.golfpvcc.peep.domain.type.ChatId
import com.golfpvcc.peep.domain.type.UserId

data class ChatParticipantLeftEvent(
    val chatId: ChatId,
    val userId: UserId
)