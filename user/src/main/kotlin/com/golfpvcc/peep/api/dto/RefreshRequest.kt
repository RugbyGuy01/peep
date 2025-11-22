package com.golfpvcc.peep.api.dto

import com.fasterxml.jackson.annotation.JsonProperty


data class RefreshRequest(
    val refreshToken: String
)
