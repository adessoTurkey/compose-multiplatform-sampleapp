package com.example.moveeapp_compose_kmm.domain.usecase.accountusecase.rating

import com.example.moveeapp_compose_kmm.domain.rating.RatingRepository

class RemoveMovieRatingUseCase(private val repository: RatingRepository) {
    suspend fun execute(movieId: Int): Result<Unit> {
        return repository.removeMovieRating(movieId = movieId)
    }
}
