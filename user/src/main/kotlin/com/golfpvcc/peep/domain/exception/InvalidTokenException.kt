package com.golfpvcc.peep.domain.exception

class InvalidTokenException(
    override val message: String?
): RuntimeException(
    message ?: "Invalid token"
)