package com.golfpvcc.peep.api.mappers

import com.golfpvcc.peep.api.dto.PictureUploadResponse
import com.golfpvcc.peep.domain.models.ProfilePictureUploadCredentials

fun ProfilePictureUploadCredentials.toResponse(): PictureUploadResponse {
    return PictureUploadResponse(
        uploadUrl = uploadUrl,
        publicUrl = publicUrl,
        headers = headers,
        expiresAt = expiresAt
    )
}