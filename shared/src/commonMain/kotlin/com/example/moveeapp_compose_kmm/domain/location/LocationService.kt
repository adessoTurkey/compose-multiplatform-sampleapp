package com.example.moveeapp_compose_kmm.domain.location

interface LocationService {
    suspend fun getCurrentLocation(): DeviceLocation
}
