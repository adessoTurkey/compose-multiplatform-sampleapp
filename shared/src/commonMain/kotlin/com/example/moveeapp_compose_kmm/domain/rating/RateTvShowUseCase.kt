package com.example.moveeapp_compose_kmm.domain.rating

import com.example.moveeapp_compose_kmm.domain.account.favorite.IsRateSuccess

class RateTvShowUseCase(private val repository: RatingRepository) {
    suspend fun execute(rating: Int, tvShowId: Int): IsRateSuccess {
        val result = repository.rateTvShow(rating.toDouble(), tvShowId)

        result.onSuccess {
            return IsRateSuccess(it)
        }

        return IsRateSuccess(false)
    }
}
