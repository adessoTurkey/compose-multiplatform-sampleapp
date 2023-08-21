package com.example.moveeapp_compose_kmm.data.remote.model.login

import kotlinx.serialization.Serializable

@Serializable
data class LoginRequestModel(
    val username: String,
    val password: String,
    val request_token: String
)