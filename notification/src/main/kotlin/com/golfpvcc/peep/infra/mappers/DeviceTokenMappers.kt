package com.golfpvcc.peep.infra.mappers

import com.golfpvcc.peep.domain.model.DeviceToken
import com.golfpvcc.peep.infra.database.DeviceTokenEntity

fun DeviceTokenEntity.toDeviceToken(): DeviceToken {
    return DeviceToken(
        userId = userId,
        token = token,
        platform = platform.toPlatform(),
        createdAt = createdAt,
        id = id
    )
}