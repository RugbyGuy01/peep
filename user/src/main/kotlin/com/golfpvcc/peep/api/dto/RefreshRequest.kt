package com.golfpvcc.peep.api.dto

import com.fasterxml.jackson.annotation.JsonProperty


// vpg 11/02/2025
//data class RefreshRequest(
//    @JsonProperty("refreshToken")
//    val refreshToken: String
//)

data class RefreshRequest(
    val refreshToken: String
)
