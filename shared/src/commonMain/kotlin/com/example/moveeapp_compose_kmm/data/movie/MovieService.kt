package com.example.moveeapp_compose_kmm.data.movie

import com.example.moveeapp_compose_kmm.data.favorite.AccountStateResponseModel
import com.example.moveeapp_compose_kmm.data.artist.CreditsModel

interface MovieService {

    suspend fun popularMovie(): PopularMovieModel

    suspend fun nowPlayingMovie(): NowPlayingMovieModel

    suspend fun movieDetail(movieId: Int): MovieDetailModel

    suspend fun movieCredit(movieId: Int): CreditsModel

    suspend fun getMovieState(sessionId: String, movieId: Int): AccountStateResponseModel
}
