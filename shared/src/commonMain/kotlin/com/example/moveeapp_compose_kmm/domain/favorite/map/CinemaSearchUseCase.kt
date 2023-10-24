package com.example.moveeapp_compose_kmm.domain.usecase.accountusecase.map

import com.example.moveeapp_compose_kmm.data.repository.MapRepository
import com.example.moveeapp_compose_kmm.domain.location.OSMObject

class CinemaSearchUseCase(
    private val repository: MapRepository
) {
    suspend fun execute(
        latitude: Double,
        longitude: Double
    ): Result<List<OSMObject>> {
        return repository.getCinemas(latitude, longitude)
    }
}