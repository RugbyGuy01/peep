package com.golfpvcc.peep.api.dto.ws

import com.golfpvcc.peep.domain.type.UserId

data class ProfilePictureUpdateDto(
    val userId: UserId,
    val newUrl: String?
)