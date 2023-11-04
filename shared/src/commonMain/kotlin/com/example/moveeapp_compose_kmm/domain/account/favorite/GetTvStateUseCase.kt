package com.example.moveeapp_compose_kmm.domain.account.favorite

import com.example.moveeapp_compose_kmm.domain.account.AccountRepository

class GetTvStateUseCase(private val repository: AccountRepository) {

    suspend fun execute(mediaId: Int): Result<TvAccountState> {
        return repository.getTvAccountState(mediaId)
    }
}
