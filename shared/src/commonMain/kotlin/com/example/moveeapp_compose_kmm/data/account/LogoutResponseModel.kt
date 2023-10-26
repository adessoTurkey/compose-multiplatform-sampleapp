package com.example.moveeapp_compose_kmm.data.account

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class LogoutResponseModel(
    @SerialName("success") val success: Boolean
)
