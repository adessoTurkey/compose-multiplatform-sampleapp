package com.example.moveeapp_compose_kmm.data.account.favorite

import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.KSerializer
import kotlinx.serialization.Serializable
import kotlinx.serialization.descriptors.PrimitiveKind
import kotlinx.serialization.descriptors.PrimitiveSerialDescriptor
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder

@Serializable
data class AccountStateResponseModel(
    val favorite: Boolean?,
    val id: Int?,
    @Serializable(RatingDeserializer::class) val rated: Float?,
    val watchlist: Boolean?,
)

@Serializable
data class Rated(
    val value: Float?,
)

private object RatingDeserializer : KSerializer<Float?> {
    @OptIn(ExperimentalSerializationApi::class)
    override fun deserialize(decoder: Decoder): Float? {
        return try {
            decoder.decodeBoolean()
            null
        } catch (e: Exception) {
            val rated = decoder.decodeNullableSerializableValue(Rated.serializer())
            rated?.value
        }
    }

    override val descriptor: SerialDescriptor
        get() = PrimitiveSerialDescriptor("rating", PrimitiveKind.FLOAT)

    override fun serialize(encoder: Encoder, value: Float?) {
    }
}