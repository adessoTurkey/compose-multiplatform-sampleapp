package com.example.moveeapp_compose_kmm.domain.movie

interface MovieRepository {

    suspend fun getPopularMovie(): Result<List<PopularMovie>>

    suspend fun getNowPlayingMovie(): Result<List<NowPlayingMovie>>

    suspend fun getMovieDetail(movieId: Int): Result<MovieDetail>

    suspend fun getMovieCredits(movieId: Int): Result<List<Credits>>
}
