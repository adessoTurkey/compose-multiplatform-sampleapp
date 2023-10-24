package com.example.moveeapp_compose_kmm.data.remote

import com.example.moveeapp_compose_kmm.data.remote.model.account.AccountDetailModel
import com.example.moveeapp_compose_kmm.domain.location.OSMObject
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get

class NominatimImpl(private val client: HttpClient) : NominatimInterface {
    override suspend fun getCinemas(query: String, latitude: Double, longitude: Double): List<OSMObject> {
        val parameterss = mapOf(
            "q" to "$latitude,$longitude cinema",
            "format" to "json",
            "limit" to "20"
        )
        return client.get("https://nominatim.openstreetmap.org/search") {
            url {
                parameterss.forEach { (key, value) ->
                    parameters.append(key, value)
                }
            }
        }.body()
    }
}