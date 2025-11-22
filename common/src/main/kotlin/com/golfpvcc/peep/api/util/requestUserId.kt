package com.golfpvcc.peep.api.util

import com.golfpvcc.peep.domain.exception.UnauthorizedException
import com.golfpvcc.peep.domain.type.UserId
import org.springframework.security.core.context.SecurityContextHolder

val requestUserId: UserId
    get() = SecurityContextHolder.getContext().authentication?.principal as? UserId
        ?: throw UnauthorizedException()