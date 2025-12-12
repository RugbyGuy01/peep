package com.golfpvcc.peep.api.exception_handling

import com.golfpvcc.peep.domain.exception.ChatNotFoundException
import com.golfpvcc.peep.domain.exception.ChatParticipantNotFoundException
import com.golfpvcc.peep.domain.exception.InvalidChatSizeException
import com.golfpvcc.peep.domain.exception.InvalidProfilePictureException
import com.golfpvcc.peep.domain.exception.MessageNotFoundException
import com.golfpvcc.peep.domain.exception.StorageException
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseStatus

class ChatExceptionHandler {

    @ExceptionHandler(
        ChatNotFoundException::class,
        MessageNotFoundException::class,
        ChatParticipantNotFoundException::class,
    )
    @ResponseStatus(HttpStatus.NOT_FOUND)
    fun onForbidden(e: Exception) = mapOf(
        "code" to "NOT_FOUND",
        "message" to e.message
    )

    @ExceptionHandler(InvalidChatSizeException::class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    fun onForbidden(e: InvalidChatSizeException) = mapOf(
        "code" to "INVALID_CHAT_SIZE",
        "message" to e.message
    )

    @ExceptionHandler(InvalidProfilePictureException::class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    fun onInvalidProfilePicture(e: InvalidProfilePictureException) = mapOf(
        "code" to "INVALID_PROFILE_PICTURE",
        "message" to e.message
    )

    @ExceptionHandler(StorageException::class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    fun onInvalidProfilePicture(e: StorageException) = mapOf(
        "code" to "STORAGE_ERROR",
        "message" to e.message
    )
}