package com.example.moveeapp_compose_kmm.data.account

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class AccountDetailModel(
    @SerialName("avatar") val avatar: Avatar,
    @SerialName("id") val id: Int,
    @SerialName("include_adult") val includeAdult: Boolean,
    @SerialName("iso_3166_1") val country: String,
    @SerialName("iso_639_1") val language: String,
    @SerialName("name") val name: String,
    @SerialName("username") val username: String
) {
    @Serializable
    data class Avatar(
        @SerialName("gravatar")
        val gravatar: Gravatar
    ) {
        @Serializable
        data class Gravatar(
            @SerialName("hash")
            val hash: String
        )
    }
}
