package com.example.moveeapp_compose_kmm.data.remote.model.account

import com.example.moveeapp_compose_kmm.data.uimodel.AccountUiModel
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class AccountResponse(
    @SerialName("avatar") val avatar: Avatar,
    @SerialName ("id") val id: Int,
    @SerialName ("include_adult")val includeAdult: Boolean,
    @SerialName ("iso_3166_1") val iso31661: String,
    @SerialName ("iso_639_1") val iso6391: String,
    @SerialName ("name") val name: String,
    @SerialName ("username") val username: String
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

    fun toUiModel() = AccountUiModel(
        username = username,
        accountId = id,
        country = iso31661
    )
}