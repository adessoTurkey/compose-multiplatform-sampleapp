package com.example.moveeapp_compose_kmm.domain.movie

import com.example.moveeapp_compose_kmm.domain.favorite.MovieAccountState
import com.example.moveeapp_compose_kmm.domain.artist.Credits

interface MovieRepository {

    suspend fun getPopularMovie(): Result<List<PopularMovie>>

    suspend fun getNowPlayingMovie(): Result<List<NowPlayingMovie>>

    suspend fun getMovieDetail(movieId: Int): Result<MovieDetail>

    suspend fun getMovieCredits(movieId: Int): Result<List<Credits>>

    suspend fun getMovieAccountState(movieId: Int): Result<MovieAccountState>
}
