package com.example.moveeapp_compose_kmm.data.remote.model.account.favorite

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class AccountStateResponseModel(
    @SerialName("favorite") val favorite: Boolean? = null,
    @SerialName("id") val id: Int? = null,
    @SerialName("rated") val rated: Rated? = null,
    @SerialName("watch_list") val watchList: Boolean? = null
) {
    @Serializable
    data class Rated(
        @SerialName("value") val value: Double? = null
    )
}
