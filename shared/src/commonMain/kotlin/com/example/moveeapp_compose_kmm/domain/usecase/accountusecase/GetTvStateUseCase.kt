package com.example.moveeapp_compose_kmm.domain.usecase.accountusecase

import com.example.moveeapp_compose_kmm.data.repository.AccountRepository
import com.example.moveeapp_compose_kmm.domain.model.TvAccountState

class GetTvStateUseCase(private val repository: AccountRepository) {

    suspend fun execute(mediaId: Int): Result<TvAccountState> {
        return repository.getTvAccountState(mediaId)
    }
}