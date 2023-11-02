package com.example.moveeapp_compose_kmm.data.movie

import com.example.moveeapp_compose_kmm.data.remote.model.CreditsModel

interface MovieService {

    suspend fun popularMovie(): PopularMovieModel

    suspend fun nowPlayingMovie(): NowPlayingMovieModel

    suspend fun movieDetail(movieId: Int): MovieDetailModel

    suspend fun movieCredit(movieId: Int): CreditsModel
}
