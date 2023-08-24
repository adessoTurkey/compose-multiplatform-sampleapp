package com.example.moveeapp_compose_kmm.data.remote

import com.example.moveeapp_compose_kmm.data.remote.model.movie.PopularMovieModel
import com.example.moveeapp_compose_kmm.data.remote.model.login.LoginRequestModel
import com.example.moveeapp_compose_kmm.data.remote.model.login.LoginResponseModel
import com.example.moveeapp_compose_kmm.data.remote.model.login.RequestTokenResponseModel
import com.example.moveeapp_compose_kmm.data.remote.model.login.SessionRequestModel
import com.example.moveeapp_compose_kmm.data.remote.model.login.SessionResponseModel
import com.example.moveeapp_compose_kmm.data.remote.model.movie.NowPlayingMovieModel
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.http.ContentType
import io.ktor.http.contentType

class ApiImpl(private val client: HttpClient) : ApiInterface {

    override suspend fun popularMovie(): PopularMovieModel {
        return client.get(POPULAR_MOVIE).body()
    }

    override suspend fun nowPlayingMovie(): NowPlayingMovieModel {
        return client.get(NOW_PLAYING_MOVIE).body()
    }

    override suspend fun createRequestToken(): RequestTokenResponseModel {
        return client.get(REQUEST_TOKEN).body()
    }

    override suspend fun createRequestTokenWithLogin(requestModel: LoginRequestModel): LoginResponseModel {
        return client.post(LOGIN) {
            contentType(ContentType.Application.Json)
            setBody(requestModel)
        }.body()
    }

    override suspend fun createSession(requestModel: SessionRequestModel): SessionResponseModel {
        return client.post(SESSION) {
            contentType(ContentType.Application.Json)
            setBody(requestModel)
        }.body()
    }

    companion object {

        //Movie
        const val POPULAR_MOVIE = "movie/popular"
        const val NOW_PLAYING_MOVIE = "movie/now_playing"

        //Login
        const val REQUEST_TOKEN = "authentication/token/new"
        const val LOGIN = "authentication/token/validate_with_login"
        const val SESSION = "authentication/session/new"
    }
}


