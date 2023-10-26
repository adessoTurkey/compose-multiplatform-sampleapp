package com.example.moveeapp_compose_kmm.data.repository

import com.example.moveeapp_compose_kmm.data.remote.model.account.favorite.AddFavoriteRequestModel
import com.example.moveeapp_compose_kmm.data.remote.model.account.favorite.AddFavoriteResponseModel
import com.example.moveeapp_compose_kmm.data.remote.model.account.favorite.FavoriteMovieModel
import com.example.moveeapp_compose_kmm.data.remote.model.account.favorite.FavoriteTvModel
import com.example.moveeapp_compose_kmm.data.account.LogoutResponseModel
import com.example.moveeapp_compose_kmm.domain.account.AccountDetail
import com.example.moveeapp_compose_kmm.domain.account.AccountRepository
import com.example.moveeapp_compose_kmm.domain.model.MovieAccountState
import com.example.moveeapp_compose_kmm.domain.model.TvAccountState

class FakeAccountRepository : AccountRepository {

    var accountDetailModel: AccountDetail? = null
    var addFavoriteResponseModel: AddFavoriteResponseModel? = null
    var movieAccountState: MovieAccountState? = null
    var tvAccountState: TvAccountState? = null
    var favoriteMovieModel: FavoriteMovieModel? = null
    var favoriteTvModel: FavoriteTvModel? = null
    var logoutResponseModel: Boolean? = null

    override suspend fun getAccountDetail(): Result<AccountDetail> =
        accountDetailModel.runCatching { this!! }

    override suspend fun addFavorite(
        accountId: Int,
        addFavoriteRequestModel: AddFavoriteRequestModel
    ): Result<AddFavoriteResponseModel> = addFavoriteResponseModel.runCatching { this!! }

    override suspend fun getMovieAccountState(movieId: Int): Result<MovieAccountState> =
        movieAccountState.runCatching { this!! }

    override suspend fun getTvAccountState(tvId: Int): Result<TvAccountState> =
        tvAccountState.runCatching { this!! }

    override suspend fun getFavoriteMovie(
        accountId: Int,
        sessionId: String
    ): Result<FavoriteMovieModel> = favoriteMovieModel.runCatching { this!! }

    override suspend fun getFavoriteTv(accountId: Int, sessionId: String): Result<FavoriteTvModel> =
        favoriteTvModel.runCatching { this!! }

    override suspend fun logout(sessionId: String?): Result<Boolean> =
        logoutResponseModel.runCatching { this!! }
}
