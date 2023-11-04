package com.example.moveeapp_compose_kmm.domain.account

import com.example.moveeapp_compose_kmm.data.account.favorite.AddFavoriteRequestModel
import com.example.moveeapp_compose_kmm.data.account.favorite.AddFavoriteResponseModel
import com.example.moveeapp_compose_kmm.domain.account.favorite.FavoriteMovie
import com.example.moveeapp_compose_kmm.domain.account.favorite.FavoriteTv
import com.example.moveeapp_compose_kmm.domain.account.favorite.MovieAccountState
import com.example.moveeapp_compose_kmm.domain.account.favorite.TvAccountState

interface AccountRepository {
    suspend fun getAccountDetail(): Result<AccountDetail>

    //favorite
    suspend fun addFavorite(
        accountId: Int,
        addFavoriteRequestModel: AddFavoriteRequestModel
    ): Result<AddFavoriteResponseModel>

    suspend fun getMovieAccountState(movieId: Int): Result<MovieAccountState>

    suspend fun getTvAccountState(tvId: Int): Result<TvAccountState>

    suspend fun getFavoriteMovie(accountId: Int, sessionId: String): Result<List<FavoriteMovie>>

    suspend fun getFavoriteTv(accountId: Int, sessionId: String): Result<List<FavoriteTv>>

    //logout
    suspend fun logout(sessionId: String?): Result<Boolean>
}
