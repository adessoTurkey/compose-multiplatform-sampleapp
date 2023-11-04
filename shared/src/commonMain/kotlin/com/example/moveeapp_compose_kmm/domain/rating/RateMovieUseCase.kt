package com.example.moveeapp_compose_kmm.domain.rating

import com.example.moveeapp_compose_kmm.domain.account.favorite.IsRateSuccess

class RateMovieUseCase(private val repository: RatingRepository) {

    suspend fun execute(rating: Int, movieId: Int): IsRateSuccess {
        val result = repository.rateMovie(rating.toDouble(), movieId)

        result.onSuccess {
            return IsRateSuccess(it)
        }

        return IsRateSuccess(false)
    }
}
