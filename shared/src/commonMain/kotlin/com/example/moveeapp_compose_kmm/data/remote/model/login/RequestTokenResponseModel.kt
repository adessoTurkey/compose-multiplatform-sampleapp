package com.example.moveeapp_compose_kmm.data.remote.model.login

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class RequestTokenResponseModel(
    @SerialName("success") val success: Boolean,
    @SerialName("request_token") val request_token: String
)