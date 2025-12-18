package com.golfpvcc.peep.api.dto

import com.golfpvcc.peep.domain.type.UserId
import java.time.Instant

data class DeviceTokenDto(
    val userId: UserId,
    val token: String,
    val createdAt: Instant
)