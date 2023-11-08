package com.example.moveeapp_compose_kmm.data.account.favorite

import kotlinx.serialization.DeserializationStrategy
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.JsonContentPolymorphicSerializer
import kotlinx.serialization.json.JsonElement
import kotlinx.serialization.json.jsonObject
import kotlinx.serialization.json.jsonPrimitive

@Serializable(with = AccountStateResponseDeserializer::class)
sealed class AccountStateResponse {
    @Serializable
    data class RatedAccountStateResponse(
        val favorite: Boolean?,
        val id: Int?,
        val rated: Rated?,
        val watchlist: Boolean?
    ) : AccountStateResponse()

    @Serializable
    data class NotRatedAccountStateResponse(
        val favorite: Boolean?,
        val id: Int?,
        val rated: Boolean?,
        val watchlist: Boolean?
    ) : AccountStateResponse()
}

@Serializable
data class Rated(
    val value: Double?
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