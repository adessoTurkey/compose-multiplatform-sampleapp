package com.example.moveeapp_compose_kmm.domain.rating

class RemoveTvShowRatingUseCase(private val repository: RatingRepository) {
    suspend fun execute(tvShowId: Int): Result<Unit> {
        return repository.removeTvShowRating(tvShowId = tvShowId)
    }
}
