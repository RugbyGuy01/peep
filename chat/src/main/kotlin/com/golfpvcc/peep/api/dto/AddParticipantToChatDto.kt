package com.golfpvcc.peep.api.dto


import com.golfpvcc.peep.domain.type.UserId
import jakarta.validation.constraints.Size

data class AddParticipantToChatDto(
    @field:Size(min = 1)
    val userIds: List<UserId>
)