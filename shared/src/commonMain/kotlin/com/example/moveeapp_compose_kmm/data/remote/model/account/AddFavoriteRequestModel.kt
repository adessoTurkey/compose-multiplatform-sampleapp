package com.example.moveeapp_compose_kmm.data.remote.model.account

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class AddFavoriteRequestModel(
    @SerialName("favorite") val favorite: Boolean,
    @SerialName("media_id") val mediaId: Int,
    @SerialName("media_type") val mediaType: String
)