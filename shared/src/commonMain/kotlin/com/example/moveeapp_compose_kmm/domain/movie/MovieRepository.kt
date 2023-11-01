package com.example.moveeapp_compose_kmm.domain.movie

import kotlinx.coroutines.flow.Flow

interface MovieRepository {

    fun getPopularMovie(): Flow<Result<List<PopularMovie>>>
    fun getNowPlayingMovie(): Flow<Result<List<NowPlayingMovie>>>
    fun getMovieDetail(movieId: Int): Flow<Result<MovieDetail>>
    fun getMovieCredits(movieId: Int): Flow<Result<List<Credits>>>
}
