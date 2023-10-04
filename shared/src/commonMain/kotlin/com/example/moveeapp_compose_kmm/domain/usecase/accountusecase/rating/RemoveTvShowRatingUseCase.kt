package com.example.moveeapp_compose_kmm.domain.usecase.accountusecase.rating

import com.example.moveeapp_compose_kmm.data.repository.AccountRepository

class RemoveTvShowRatingUseCase(private val accountRepository: AccountRepository) {
    suspend fun execute(tvShowId: Int): Result<Unit> {
        return accountRepository.removeTvShowRating(tvShowId = tvShowId)
    }
}