package com.example.moveeapp_compose_kmm.data.account

import com.example.moveeapp_compose_kmm.data.account.favorite.AccountStateResponse
import com.example.moveeapp_compose_kmm.data.account.favorite.AddFavoriteRequestModel
import com.example.moveeapp_compose_kmm.data.account.favorite.AddFavoriteResponseModel
import com.example.moveeapp_compose_kmm.data.account.favorite.FavoriteMovieModel
import com.example.moveeapp_compose_kmm.data.account.favorite.FavoriteTvModel

interface AccountService {

    suspend fun accountDetails(sessionId: String): AccountDetailModel

    suspend fun logout(logoutRequestModel: LogoutRequestModel): LogoutResponseModel

    suspend fun addFavorite(
        accountId: Int,
        addFavoriteRequestModel: AddFavoriteRequestModel,
        sessionId: String
    ): AddFavoriteResponseModel

    suspend fun getMovieState(sessionId: String, movieId: Int): AccountStateResponse

    suspend fun getTvState(sessionId: String, tvId: Int): AccountStateResponse

    suspend fun getFavoriteMovie(accountId: Int, sessionId: String): FavoriteMovieModel
    suspend fun getFavoriteTv(accountId: Int, sessionId: String): FavoriteTvModel
}
