package com.example.moveeapp_compose_kmm.domain.account

import com.example.moveeapp_compose_kmm.data.remote.model.account.favorite.AddFavoriteRequestModel
import com.example.moveeapp_compose_kmm.data.remote.model.account.favorite.AddFavoriteResponseModel
import com.example.moveeapp_compose_kmm.data.remote.model.account.favorite.FavoriteMovieModel
import com.example.moveeapp_compose_kmm.data.remote.model.account.favorite.FavoriteTvModel
import com.example.moveeapp_compose_kmm.domain.model.MovieAccountState
import com.example.moveeapp_compose_kmm.domain.model.TvAccountState

interface AccountRepository {
    suspend fun getAccountDetail(): Result<AccountDetail>

    suspend fun addFavorite(
        accountId: Int,
        addFavoriteRequestModel: AddFavoriteRequestModel
    ): Result<AddFavoriteResponseModel>

    suspend fun getMovieAccountState(movieId: Int): Result<MovieAccountState>

    suspend fun getTvAccountState(tvId: Int): Result<TvAccountState>

    suspend fun getFavoriteMovie(accountId: Int, sessionId: String): Result<FavoriteMovieModel>

    suspend fun getFavoriteTv(accountId: Int, sessionId: String): Result<FavoriteTvModel>

    suspend fun logout(sessionId: String?): Result<Boolean>
}
