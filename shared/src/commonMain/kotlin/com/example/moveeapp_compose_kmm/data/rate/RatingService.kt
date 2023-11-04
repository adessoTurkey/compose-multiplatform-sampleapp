package com.example.moveeapp_compose_kmm.data.rate

interface RatingService {
    suspend fun rateMovie(rating: RateDto, movieId: Int, sessionId: String): RateResponse
    suspend fun rateTvShow(rating: RateDto, tvShowId: Int, sessionId: String): RateResponse
    suspend fun removeMovieRating(movieId: Int, sessionId: String)
    suspend fun removeTvShowRating(tvShowId: Int, sessionId: String)
}
