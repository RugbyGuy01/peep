package com.golfpvcc.peep.infra.database.mappers

import com.golfpvcc.peep.domain.model.EmailVerificationToken
import com.golfpvcc.peep.infra.database.entities.EmailVerificationTokenEntity

fun EmailVerificationTokenEntity.toEmailVerificationToken(): EmailVerificationToken {
    return EmailVerificationToken(
        id = id,
        token = token,
        user = user.toUser()
    )
}