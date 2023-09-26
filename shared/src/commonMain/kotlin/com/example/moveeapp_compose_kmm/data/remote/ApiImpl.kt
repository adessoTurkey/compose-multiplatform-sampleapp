package com.example.moveeapp_compose_kmm.data.remote

import com.example.moveeapp_compose_kmm.data.remote.model.CreditsModel
import com.example.moveeapp_compose_kmm.data.remote.model.SearchModel
import com.example.moveeapp_compose_kmm.data.remote.model.account.AccountDetailModel
import com.example.moveeapp_compose_kmm.data.remote.model.account.favorite.AccountStateResponseModel
import com.example.moveeapp_compose_kmm.data.remote.model.account.favorite.AddFavoriteResponseModel
import com.example.moveeapp_compose_kmm.data.remote.model.account.favorite.AddFavoriteRequestModel
import com.example.moveeapp_compose_kmm.data.remote.model.account.favorite.FavoriteMovieModel
import com.example.moveeapp_compose_kmm.data.remote.model.account.favorite.FavoriteTvModel
import com.example.moveeapp_compose_kmm.data.remote.model.login.LoginRequestModel
import com.example.moveeapp_compose_kmm.data.remote.model.login.LoginResponseModel
import com.example.moveeapp_compose_kmm.data.remote.model.login.LogoutRequestModel
import com.example.moveeapp_compose_kmm.data.remote.model.login.LogoutResponseModel
import com.example.moveeapp_compose_kmm.data.remote.model.login.RequestTokenResponseModel
import com.example.moveeapp_compose_kmm.data.remote.model.login.SessionRequestModel
import com.example.moveeapp_compose_kmm.data.remote.model.login.SessionResponseModel
import com.example.moveeapp_compose_kmm.data.remote.model.movie.MovieDetailModel
import com.example.moveeapp_compose_kmm.data.remote.model.movie.NowPlayingMovieModel
import com.example.moveeapp_compose_kmm.data.remote.model.movie.PopularMovieModel
import com.example.moveeapp_compose_kmm.data.remote.model.person.PersonCreditsModel
import com.example.moveeapp_compose_kmm.data.remote.model.person.PersonDetailModel
import com.example.moveeapp_compose_kmm.data.remote.model.tv.PopularTvModel
import com.example.moveeapp_compose_kmm.data.remote.model.tv.TopRatedTvModel
import com.example.moveeapp_compose_kmm.data.remote.model.tv.TvDetailModel
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.delete
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

    //Search
    override suspend fun search(query: String): SearchModel {
        return client.get(SEARCH) {
            url {
                parameters.append("query", query)
            }
        }.body()
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

    //Person
    override suspend fun personDetail(personId: Int): PersonDetailModel {
        return client.get("person/$personId").body()
    }

    override suspend fun personCredit(personId: Int): PersonCreditsModel {
        return client.get("person/$personId/combined_credits").body()
    }

    //Account
    override suspend fun addFavorite(
        accountId: Int,
        addFavoriteRequestModel: AddFavoriteRequestModel,
        sessionId: String
    ): AddFavoriteResponseModel {
        return client.post("account/$accountId/favorite") {
            contentType(ContentType.Application.Json)
            url {
                parameters.append(SESSION_ID, sessionId)
            }
            setBody(addFavoriteRequestModel)
        }.body()
    }

    override suspend fun accountDetails(sessionId: String): AccountDetailModel {
        return client.get("account") {
            url {
                parameters.append(SESSION_ID, sessionId)
            }
        }.body()
    }

    override suspend fun getMovieState(sessionId: String, movieId: Int): AccountStateResponseModel {
        return client.get("movie/${movieId}/account_states"){
            contentType(ContentType.Application.Json)
            url {
                parameters.append(SESSION_ID, sessionId)
            }
        }.body()
    }

    override suspend fun getTvState(sessionId: String, tvId: Int): AccountStateResponseModel {
        return client.get("tv/${tvId}/account_states") {
            contentType(ContentType.Application.Json)
            url {
                parameters.append(SESSION_ID, sessionId)
            }
        }.body()
    }

    override suspend fun getFavoriteMovie(accountId: Int, sessionId: String): FavoriteMovieModel {
        return client.get("account/{$accountId}/favorite/movies"){
            url{
                parameters.append(SESSION_ID, sessionId)
            }
        }.body()
    }

    override suspend fun getFavoriteTv(accountId: Int, sessionId: String): FavoriteTvModel {
        return client.get("account/{$accountId}/favorite/tv"){
            url{
                parameters.append(SESSION_ID, sessionId)
            }
        }.body()
    }

    override suspend fun logout(logoutRequestModel: LogoutRequestModel): LogoutResponseModel {
        return client.delete(LOGOUT){
            setBody(logoutRequestModel)
            contentType(ContentType.Application.Json)
        }.body()
    }


    companion object {

        //Movie
        const val POPULAR_MOVIE = "movie/popular"
        const val NOW_PLAYING_MOVIE = "movie/now_playing"

        //Tv
        const val POPULAR_TV = "tv/popular"
        const val TOP_RATED_TV = "tv/top_rated"

        //Search
        const val SEARCH = "search/multi"

        //Login
        const val REQUEST_TOKEN = "authentication/token/new"
        const val LOGIN = "authentication/token/validate_with_login"
        const val SESSION = "authentication/session/new"

        //Logout
        const val LOGOUT = "authentication/session"

        const val SESSION_ID = "session_id"
    }
}


