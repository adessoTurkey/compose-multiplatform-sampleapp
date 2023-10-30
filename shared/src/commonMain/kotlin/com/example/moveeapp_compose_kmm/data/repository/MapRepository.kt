package com.example.moveeapp_compose_kmm.data.repository

import com.example.moveeapp_compose_kmm.data.remote.NominatimInterface
import com.example.moveeapp_compose_kmm.domain.location.DeviceLocation
import com.example.moveeapp_compose_kmm.ui.scene.map.Cinema
import com.example.moveeapp_compose_kmm.utils.resultOf

class MapRepository(private val api: NominatimInterface) {
    suspend fun getCinemas(
        latitude: Double,
        longitude: Double
    ): Result<List<Cinema>> {
        return resultOf {
            api.getCinemas(latitude, longitude).map {
                Cinema(it.name, it.displayName, DeviceLocation(it.lat, it.lon))
            }
        }
    }

}