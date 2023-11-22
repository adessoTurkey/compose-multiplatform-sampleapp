package com.example.moveeapp_compose_kmm.data.map

import com.example.moveeapp_compose_kmm.domain.location.DeviceLocation
import com.example.moveeapp_compose_kmm.domain.map.MapRepository
import com.example.moveeapp_compose_kmm.ui.scene.map.Cinema
import com.example.moveeapp_compose_kmm.utils.resultOf

class MapRepositoryImpl(private val service: NominatimService) : MapRepository {
    override suspend fun getCinemas(
        latitude: Double,
        longitude: Double
    ): Result<List<Cinema>> {
        return resultOf {
            service.getCinemas(latitude, longitude).map {
                Cinema(it.name, it.displayName, DeviceLocation(it.lat, it.lon))
            }
        }
    }
}