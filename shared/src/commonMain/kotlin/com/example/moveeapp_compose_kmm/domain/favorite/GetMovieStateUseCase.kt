package com.example.moveeapp_compose_kmm.domain.favorite

import com.example.moveeapp_compose_kmm.domain.movie.MovieRepository

class GetMovieStateUseCase(private val repository: MovieRepository) {

    suspend fun execute(mediaId: Int): Result<MovieAccountState> {
        return repository.getMovieAccountState(mediaId)
    }
}
