package com.example.moveeapp_compose_kmm.domain.usecase.accountusecase.rating

import com.example.moveeapp_compose_kmm.domain.rating.RatingRepository

class RemoveTvShowRatingUseCase(private val repository: RatingRepository) {
    suspend fun execute(tvShowId: Int): Result<Unit> {
        return repository.removeTvShowRating(tvShowId = tvShowId)
    }
}
