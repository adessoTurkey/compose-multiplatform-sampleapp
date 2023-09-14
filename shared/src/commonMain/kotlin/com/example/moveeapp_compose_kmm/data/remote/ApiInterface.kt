package com.example.moveeapp_compose_kmm.data.remote

import com.example.moveeapp_compose_kmm.data.remote.model.CreditsModel
import com.example.moveeapp_compose_kmm.data.remote.model.login.LoginRequestModel
import com.example.moveeapp_compose_kmm.data.remote.model.login.LoginResponseModel
import com.example.moveeapp_compose_kmm.data.remote.model.login.RequestTokenResponseModel
import com.example.moveeapp_compose_kmm.data.remote.model.login.SessionRequestModel
import com.example.moveeapp_compose_kmm.data.remote.model.login.SessionResponseModel
import com.example.moveeapp_compose_kmm.data.remote.model.movie.MovieDetailModel
import com.example.moveeapp_compose_kmm.data.remote.model.movie.NowPlayingMovieModel
import com.example.moveeapp_compose_kmm.data.remote.model.movie.PopularMovieModel
import com.example.moveeapp_compose_kmm.data.remote.model.person.PersonCreditsResponse
import com.example.moveeapp_compose_kmm.data.remote.model.person.PersonDetailResponse
import com.example.moveeapp_compose_kmm.data.remote.model.tv.PopularTvModel
import com.example.moveeapp_compose_kmm.data.remote.model.tv.TopRatedTvModel
import com.example.moveeapp_compose_kmm.data.remote.model.tv.TvDetailModel

interface ApiInterface {

    //Movie
    suspend fun popularMovie(): PopularMovieModel

    suspend fun nowPlayingMovie(): NowPlayingMovieModel

    suspend fun movieDetail(movieId: Int): MovieDetailModel

    suspend fun movieCredit(movieId: Int): CreditsModel

    //Tv
    suspend fun popularTv(): PopularTvModel

    suspend fun topRatedTv(): TopRatedTvModel

    suspend fun tvDetail(tvId: Int): TvDetailModel

    suspend fun tvCredit(tvId: Int): CreditsModel

    //Login
    suspend fun createRequestToken(): RequestTokenResponseModel

    suspend fun createRequestTokenWithLogin(requestModel: LoginRequestModel): LoginResponseModel

    suspend fun createSession(requestModel: SessionRequestModel): SessionResponseModel

    suspend fun personDetail(personId: Int): PersonDetailResponse

    suspend fun personCredit(personId: Int): PersonCreditsResponse

}