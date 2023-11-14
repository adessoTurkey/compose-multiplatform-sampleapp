package com.example.moveeapp_compose_kmm.data.map

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class OSMObject (
    @SerialName ("place_id") val placeId: Long,
    @SerialName ("name") val name: String,
    @SerialName ("display_name") val displayName: String,
    @SerialName ("lat") val latitude: String,
    @SerialName ("lon") val longitude: String,
    @SerialName ("type") val type: String
){
    val lat get() = latitude.toDouble()
    val lon get() = longitude.toDouble()
}