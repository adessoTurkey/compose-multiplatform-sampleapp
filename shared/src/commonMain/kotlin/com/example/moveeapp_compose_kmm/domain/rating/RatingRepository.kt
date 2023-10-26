package com.example.moveeapp_compose_kmm.domain.rating

interface RatingRepository {
    suspend fun rateMovie(rating: Double, movieId: Int): Result<Boolean>
    suspend fun rateTvShow(rating: Double, movieId: Int): Result<Boolean>
    suspend fun removeMovieRating(movieId: Int): Result<Unit>
    suspend fun removeTvShowRating(tvShowId: Int): Result<Unit>
}
