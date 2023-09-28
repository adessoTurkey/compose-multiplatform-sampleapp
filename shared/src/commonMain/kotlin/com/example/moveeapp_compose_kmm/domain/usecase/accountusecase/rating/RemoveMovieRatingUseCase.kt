package com.example.moveeapp_compose_kmm.domain.usecase.accountusecase.rating

import com.example.moveeapp_compose_kmm.data.repository.AccountRepository

class RemoveMovieRatingUseCase(private val accountRepository: AccountRepository) {
    suspend fun execute(movieId: Int): Result<Unit> {
        return accountRepository.removeMovieRating(movieId = movieId)
    }
}