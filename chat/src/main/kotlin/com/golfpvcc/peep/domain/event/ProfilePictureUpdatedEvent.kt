package com.golfpvcc.peep.domain.event

import com.golfpvcc.peep.domain.type.UserId

data class ProfilePictureUpdatedEvent(
    val userId: UserId,
    val newUrl: String?
)