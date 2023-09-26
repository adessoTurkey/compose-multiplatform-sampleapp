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

    //Search
    suspend fun search(query: String): SearchModel

    //Person
    suspend fun personDetail(personId: Int): PersonDetailModel

    suspend fun personCredit(personId: Int): PersonCreditsModel

    //Login
    suspend fun createRequestToken(): RequestTokenResponseModel

    suspend fun createRequestTokenWithLogin(requestModel: LoginRequestModel): LoginResponseModel

    suspend fun createSession(requestModel: SessionRequestModel): SessionResponseModel

    //Account
    suspend fun addFavorite(accountId: Int, addFavoriteRequestModel: AddFavoriteRequestModel, sessionId: String) : AddFavoriteResponseModel

    suspend fun accountDetails(sessionId: String) : AccountDetailModel

    //Favorite
    suspend fun getMovieState(sessionId: String, movieId: Int) : AccountStateResponseModel

    suspend fun getTvState(sessionId: String, tvId: Int) : AccountStateResponseModel

    suspend fun getFavoriteMovie(accountId: Int, sessionId: String) : FavoriteMovieModel
    suspend fun getFavoriteTv(accountId: Int, sessionId: String) : FavoriteTvModel

    //Logout
    suspend fun logout(logoutRequestModel: LogoutRequestModel) : LogoutResponseModel
}