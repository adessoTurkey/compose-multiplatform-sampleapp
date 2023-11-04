package com.example.moveeapp_compose_kmm.domain.account.favorite

import com.example.moveeapp_compose_kmm.domain.account.AccountRepository

class GetMovieStateUseCase(private val repository: AccountRepository) {

    suspend fun execute(mediaId: Int): Result<MovieAccountState> {
        return repository.getMovieAccountState(mediaId)
    }
}
