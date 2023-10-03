package com.example.moveeapp_compose_kmm.domain.usecase.accountusecase.rating

import com.example.moveeapp_compose_kmm.data.repository.AccountRepository
import com.example.moveeapp_compose_kmm.domain.model.IsRateSuccess

class RateTvShowUseCase(private val accountRepository: AccountRepository) {
    suspend fun execute(rating: Int, tvShowId: Int): IsRateSuccess {
        val result = accountRepository.rateTvShow(rating.toDouble(), tvShowId)

        result.onSuccess {
            return IsRateSuccess(it)
        }

        return IsRateSuccess(false)
    }
}