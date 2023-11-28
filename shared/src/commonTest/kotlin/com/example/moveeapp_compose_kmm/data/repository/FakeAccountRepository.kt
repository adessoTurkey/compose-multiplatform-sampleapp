package com.example.moveeapp_compose_kmm.data.repository

import com.example.moveeapp_compose_kmm.data.account.LoginState
import com.example.moveeapp_compose_kmm.data.account.login.LoginResponseModel
import com.example.moveeapp_compose_kmm.data.favorite.AddFavoriteResponseModel
import com.example.moveeapp_compose_kmm.domain.account.AccountDetail
import com.example.moveeapp_compose_kmm.domain.account.AccountRepository
import com.example.moveeapp_compose_kmm.domain.favorite.FavoriteMovie
import com.example.moveeapp_compose_kmm.domain.favorite.FavoriteTv
import com.example.moveeapp_compose_kmm.domain.favorite.MovieAccountState
import com.example.moveeapp_compose_kmm.domain.favorite.TvAccountState
import kotlinx.coroutines.flow.StateFlow

class FakeAccountRepository : AccountRepository {

    var accountDetailModel: AccountDetail? = null
    var addFavoriteResponseModel: AddFavoriteResponseModel? = null
    var movieAccountState: MovieAccountState? = null
    var tvAccountState: TvAccountState? = null
    var favoriteMovieModel: List<FavoriteMovie>? = null
    var favoriteTvModel: List<FavoriteTv>? = null
    var logoutResponseModel: Boolean? = null

    override suspend fun getAccountDetail(): Result<AccountDetail> =
        accountDetailModel.runCatching { this!! }

    override suspend fun login(username: String, password: String): Result<LoginResponseModel> {
        TODO("Not yet implemented")
    }

    override fun getLoginState(): StateFlow<LoginState> {
        TODO("Not yet implemented")
    }

//    override suspend fun addFavorite(
//        accountId: Int,
//        addFavoriteRequestModel: AddFavoriteRequestModel
//    ): Result<AddFavoriteResponseModel> = addFavoriteResponseModel.runCatching { this!! }
//
//    override suspend fun getMovieAccountState(movieId: Int): Result<MovieAccountState> =
//        movieAccountState.runCatching { this!! }
//
//    override suspend fun getTvAccountState(tvId: Int): Result<TvAccountState> =
//        tvAccountState.runCatching { this!! }
//
//    override suspend fun getFavoriteMovie(
//        accountId: Int,
//        sessionId: String
//    ): Result<List<FavoriteMovie>> = favoriteMovieModel.runCatching { this!! }
//
//    override suspend fun getFavoriteTv(
//        accountId: Int, sessionId: String
//    ): Result<List<FavoriteTv>> =
//        favoriteTvModel.runCatching { this!! }

    override suspend fun logout(sessionId: String?): Result<Boolean> =
        logoutResponseModel.runCatching { this!! }
}
