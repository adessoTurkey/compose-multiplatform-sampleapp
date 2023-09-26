package com.example.moveeapp_compose_kmm.domain.usecase.accountusecase

import com.example.moveeapp_compose_kmm.data.repository.AccountRepository
import com.example.moveeapp_compose_kmm.domain.model.IsFavorite

class GetTvStateUseCase(private val repository: AccountRepository) {

    suspend fun execute(mediaId: Int): Result<IsFavorite> {
        return repository.getTvIsFavorite(mediaId)
    }
}