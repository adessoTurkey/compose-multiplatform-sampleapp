package com.example.moveeapp_compose_kmm.domain.usecase.accountusecase

import com.example.moveeapp_compose_kmm.data.repository.AccountRepository
import com.example.moveeapp_compose_kmm.domain.model.MovieAccountState

class GetMovieStateUseCase(private val repository: AccountRepository) {

    suspend fun execute(mediaId: Int): Result<MovieAccountState> {
        return repository.getMovieAccountState(mediaId)
    }
}