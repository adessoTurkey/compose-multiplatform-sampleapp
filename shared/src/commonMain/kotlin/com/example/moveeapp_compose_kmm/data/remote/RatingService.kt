package com.example.moveeapp_compose_kmm.data.remote

import com.example.moveeapp_compose_kmm.data.remote.model.account.rate.RateDto
import com.example.moveeapp_compose_kmm.data.remote.model.account.rate.RateResponse

interface RatingService {
    suspend fun rateMovie(rating: RateDto, movieId: Int, sessionId: String): RateResponse
    suspend fun rateTvShow(rating: RateDto, tvShowId: Int, sessionId: String): RateResponse
    suspend fun removeMovieRating(movieId: Int, sessionId: String)
    suspend fun removeTvShowRating(tvShowId: Int, sessionId: String)
}
