package com.example.moveeapp_compose_kmm.domain.location

import kotlinx.coroutines.flow.Flow

interface LocationRepository {
    suspend fun getCurrentLocation(): DeviceLocation
}
