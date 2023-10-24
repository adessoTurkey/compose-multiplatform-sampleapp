package com.example.moveeapp_compose_kmm.data.repository

import com.example.moveeapp_compose_kmm.data.remote.NominatimInterface
import com.example.moveeapp_compose_kmm.domain.location.OSMObject
import com.example.moveeapp_compose_kmm.utils.resultOf

class MapRepository(private val api: NominatimInterface) {
    suspend fun getCinemas(
        latitude: Double,
        longitude: Double
    ): Result<List<OSMObject>> {
        return resultOf {
            api.getCinemas(latitude, longitude)
        }
    }

}