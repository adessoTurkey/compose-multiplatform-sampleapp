package com.example.moveeapp_compose_kmm.domain.map

import com.example.moveeapp_compose_kmm.ui.scene.map.Cinema

interface MapRepository {
    suspend fun getCinemas(
        latitude: Double,
        longitude: Double
    ): Result<List<Cinema>>
}