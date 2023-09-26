package com.example.moveeapp_compose_kmm.data.remote.model.account.favorite

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class AddFavoriteResponseModel(
    @SerialName("status_code") val statusCode: Int,
    @SerialName("status_message") val statusMessage: String
)