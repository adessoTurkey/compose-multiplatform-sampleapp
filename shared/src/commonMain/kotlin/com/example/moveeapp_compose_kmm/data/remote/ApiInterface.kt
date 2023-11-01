package com.example.moveeapp_compose_kmm.data.remote

import com.example.moveeapp_compose_kmm.data.remote.model.SearchModel
import com.example.moveeapp_compose_kmm.data.remote.model.account.favorite.AccountStateResponseModel
import com.example.moveeapp_compose_kmm.data.remote.model.account.favorite.AddFavoriteRequestModel
import com.example.moveeapp_compose_kmm.data.remote.model.account.favorite.AddFavoriteResponseModel
import com.example.moveeapp_compose_kmm.data.remote.model.account.favorite.FavoriteMovieModel
import com.example.moveeapp_compose_kmm.data.remote.model.account.favorite.FavoriteTvModel
import com.example.moveeapp_compose_kmm.data.remote.model.login.LoginRequestModel
import com.example.moveeapp_compose_kmm.data.remote.model.login.LoginResponseModel
import com.example.moveeapp_compose_kmm.data.remote.model.login.RequestTokenResponseModel
import com.example.moveeapp_compose_kmm.data.remote.model.login.SessionRequestModel
import com.example.moveeapp_compose_kmm.data.remote.model.login.SessionResponseModel
import com.example.moveeapp_compose_kmm.data.remote.model.person.PersonCreditsModel
import com.example.moveeapp_compose_kmm.data.remote.model.person.PersonDetailModel

interface ApiInterface {

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
    suspend fun addFavorite(
        accountId: Int,
        addFavoriteRequestModel: AddFavoriteRequestModel,
        sessionId: String
    ): AddFavoriteResponseModel

    //Favorite
    suspend fun getMovieState(sessionId: String, movieId: Int): AccountStateResponseModel

    suspend fun getTvState(sessionId: String, tvId: Int): AccountStateResponseModel

    suspend fun getFavoriteMovie(accountId: Int, sessionId: String): FavoriteMovieModel
    suspend fun getFavoriteTv(accountId: Int, sessionId: String): FavoriteTvModel

    //Logout
}
