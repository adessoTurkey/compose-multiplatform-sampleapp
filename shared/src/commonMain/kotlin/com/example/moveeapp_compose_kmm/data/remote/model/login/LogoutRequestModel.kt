package com.example.moveeapp_compose_kmm.data.remote.model.login

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class LogoutRequestModel(
    @SerialName("session_id") val sessionId: String
)