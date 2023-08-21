package com.example.moveeapp_compose_kmm.data.remote.model.login

import kotlinx.serialization.Serializable

@Serializable
data class SessionRequestModel(
    val request_token: String
)