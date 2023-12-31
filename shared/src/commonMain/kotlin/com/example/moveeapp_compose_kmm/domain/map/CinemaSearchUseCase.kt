package com.example.moveeapp_compose_kmm.domain.map

import com.example.moveeapp_compose_kmm.ui.scene.map.Cinema

class CinemaSearchUseCase(
    private val repository: MapRepository
) {
    suspend fun execute(
        latitude: Double,
        longitude: Double
    ): Result<List<Cinema>> {
        return repository.getCinemas(latitude, longitude)
    }
}