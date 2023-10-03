package com.example.moveeapp_compose_kmm.domain.usecase.accountusecase.rating

import com.example.moveeapp_compose_kmm.data.repository.AccountRepository
import com.example.moveeapp_compose_kmm.domain.model.IsRateSuccess

class RateMovieUseCase(private val accountRepository: AccountRepository) {
    suspend fun execute(rating: Int, movieId: Int): IsRateSuccess {
        val result = accountRepository.rateMovie(rating.toDouble(), movieId)

        result.onSuccess {
            return IsRateSuccess(it)
        }

        return IsRateSuccess(false)
    }
}