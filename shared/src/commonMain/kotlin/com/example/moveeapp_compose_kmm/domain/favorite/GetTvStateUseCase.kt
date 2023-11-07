package com.example.moveeapp_compose_kmm.domain.favorite

import com.example.moveeapp_compose_kmm.domain.tv.TvRepository

class GetTvStateUseCase(private val repository: TvRepository) {

    suspend fun execute(mediaId: Int): Result<TvAccountState> {
        return repository.getTvAccountState(mediaId)
    }
}
