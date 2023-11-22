package com.example.moveeapp_compose_kmm.domain.location

interface LocationRepository {
    suspend fun getCurrentLocation(): DeviceLocation
}
