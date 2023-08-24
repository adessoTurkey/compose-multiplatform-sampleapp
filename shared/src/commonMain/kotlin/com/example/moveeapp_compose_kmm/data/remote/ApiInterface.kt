package com.example.moveeapp_compose_kmm.data.remote

import com.example.moveeapp_compose_kmm.data.remote.model.movie.PopularMovieModel
import com.example.moveeapp_compose_kmm.data.remote.model.login.LoginRequestModel
import com.example.moveeapp_compose_kmm.data.remote.model.login.LoginResponseModel
import com.example.moveeapp_compose_kmm.data.remote.model.login.RequestTokenResponseModel
import com.example.moveeapp_compose_kmm.data.remote.model.login.SessionRequestModel
import com.example.moveeapp_compose_kmm.data.remote.model.login.SessionResponseModel
import com.example.moveeapp_compose_kmm.data.remote.model.movie.NowPlayingMovieModel

interface ApiInterface {

    suspend fun popularMovie(): PopularMovieModel

    suspend fun nowPlayingMovie(): NowPlayingMovieModel

    suspend fun createRequestToken(): RequestTokenResponseModel

    suspend fun createRequestTokenWithLogin(requestModel: LoginRequestModel): LoginResponseModel

    suspend fun createSession(requestModel: SessionRequestModel): SessionResponseModel
}