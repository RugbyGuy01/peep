package com.golfpvcc.peep.api.mappers

import com.golfpvcc.peep.api.dto.DeviceTokenDto
import com.golfpvcc.peep.api.dto.PlatformDto
import com.golfpvcc.peep.domain.model.DeviceToken

fun DeviceToken.toDeviceTokenDto(): DeviceTokenDto {
    return DeviceTokenDto(
        userId = userId,
        token = token,
        createdAt = createdAt
    )
}

fun PlatformDto.toPlatformDto(): DeviceToken.Platform {
    return when(this) {
        PlatformDto.ANDROID -> DeviceToken.Platform.ANDROID
        PlatformDto.IOS -> DeviceToken.Platform.IOS
    }
}