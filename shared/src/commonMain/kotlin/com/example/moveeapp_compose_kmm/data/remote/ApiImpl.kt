package com.example.moveeapp_compose_kmm.data.remote

import com.example.moveeapp_compose_kmm.data.remote.model.CreditsModel
import com.example.moveeapp_compose_kmm.data.remote.model.movie.PopularMovieModel
import com.example.moveeapp_compose_kmm.data.remote.model.login.LoginRequestModel
import com.example.moveeapp_compose_kmm.data.remote.model.login.LoginResponseModel
import com.example.moveeapp_compose_kmm.data.remote.model.login.RequestTokenResponseModel
import com.example.moveeapp_compose_kmm.data.remote.model.login.SessionRequestModel
import com.example.moveeapp_compose_kmm.data.remote.model.login.SessionResponseModel
import com.example.moveeapp_compose_kmm.data.remote.model.movie.MovieDetailModel
import com.example.moveeapp_compose_kmm.data.remote.model.movie.NowPlayingMovieModel
import com.example.moveeapp_compose_kmm.data.remote.model.tv.PopularTvModel
import com.example.moveeapp_compose_kmm.data.remote.model.tv.TopRatedTvModel
import com.example.moveeapp_compose_kmm.data.remote.model.tv.TvDetailModel
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.http.ContentType
import io.ktor.http.contentType

class ApiImpl(private val client: HttpClient) : ApiInterface {

    //Movie
    override suspend fun popularMovie(): PopularMovieModel {
        return client.get(POPULAR_MOVIE).body()
    }

    override suspend fun nowPlayingMovie(): NowPlayingMovieModel {
        return client.get(NOW_PLAYING_MOVIE).body()
    }

    override suspend fun movieDetail(movieId: Int): MovieDetailModel {
        return client.get("movie/$movieId").body()
    }

    override suspend fun movieCredit(movieId: Int): CreditsModel {
        return client.get("movie/$movieId/credits").body()
    }

    //Tv
    override suspend fun popularTv(): PopularTvModel {
        return client.get(POPULAR_TV).body()
    }

    override suspend fun topRatedTv(): TopRatedTvModel {
        return client.get(TOP_RATED_TV).body()
    }

    override suspend fun tvDetail(tvId: Int): TvDetailModel {
        return client.get("tv/$tvId").body()
    }

    override suspend fun tvCredit(tvId: Int): CreditsModel {
        return client.get("tv/$tvId/credits").body()
    }

    //Login
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

        //Tv
        const val POPULAR_TV = "tv/popular"
        const val TOP_RATED_TV = "tv/top_rated"

        //Login
        const val REQUEST_TOKEN = "authentication/token/new"
        const val LOGIN = "authentication/token/validate_with_login"
        const val SESSION = "authentication/session/new"
    }
}


