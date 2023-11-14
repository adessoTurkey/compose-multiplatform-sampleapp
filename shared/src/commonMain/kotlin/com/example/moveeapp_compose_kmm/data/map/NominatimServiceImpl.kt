package com.example.moveeapp_compose_kmm.data.map

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get

class NominatimServiceImpl(private val client: HttpClient) : NominatimService {
    override suspend fun getCinemas(latitude: Double, longitude: Double): List<OSMObject> {
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