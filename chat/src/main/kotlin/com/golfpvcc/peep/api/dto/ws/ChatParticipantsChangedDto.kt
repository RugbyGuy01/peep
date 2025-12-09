package com.golfpvcc.peep.api.dto.ws

import com.golfpvcc.peep.domain.type.ChatId

data class ChatParticipantsChangedDto(
    val chatId: ChatId
)