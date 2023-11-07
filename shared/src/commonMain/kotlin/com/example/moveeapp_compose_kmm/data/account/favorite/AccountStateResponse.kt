package com.example.moveeapp_compose_kmm.data.account.favorite

import kotlinx.serialization.DeserializationStrategy
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.JsonContentPolymorphicSerializer
import kotlinx.serialization.json.JsonElement
import kotlinx.serialization.json.jsonObject
import kotlinx.serialization.json.jsonPrimitive

@Serializable(with = AccountStateResponseDeserializer::class)
sealed class AccountStateResponse {
    @Serializable
    data class RatedAccountStateResponse(
        @SerialName("favorite") val favorite: Boolean?,
        @SerialName("id") val id: Int?,
        @SerialName("rated") val rated: Rated?,
        @SerialName("watchlist") val watchList: Boolean?
    ) : AccountStateResponse()

    @Serializable
    data class NotRatedAccountStateResponse(
        @SerialName("favorite") val favorite: Boolean?,
        @SerialName("id") val id: Int?,
        @SerialName("rated") val rated: Boolean?,
        @SerialName("watchlist") val watchList: Boolean?
    ) : AccountStateResponse()
}

@Serializable
data class Rated(
    @SerialName("value") val value: Double? = null
)

object AccountStateResponseDeserializer :
    JsonContentPolymorphicSerializer<AccountStateResponse>(AccountStateResponse::class) {
    override fun selectDeserializer(element: JsonElement): DeserializationStrategy<AccountStateResponse> {
        val jsonPrimitive = try {
            element.jsonObject["rated"]?.jsonPrimitive
        } catch (e: Throwable) {
            element.jsonObject["rated"]?.jsonObject?.get("value")?.jsonPrimitive
        }

        val rated = jsonPrimitive?.content?.lowercase()

        return if (rated?.toBooleanStrictOrNull() != null) {
            AccountStateResponse.NotRatedAccountStateResponse.serializer()
        } else {
            AccountStateResponse.RatedAccountStateResponse.serializer()
        }
    }
}