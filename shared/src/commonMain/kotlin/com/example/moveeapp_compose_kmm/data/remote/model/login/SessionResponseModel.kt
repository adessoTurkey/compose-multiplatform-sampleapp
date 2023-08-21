package com.example.moveeapp_compose_kmm.data.remote.model.login

import kotlinx.serialization.Serializable

@Serializable
data class SessionResponseModel(
    val success: Boolean,
    val session_id: String
)