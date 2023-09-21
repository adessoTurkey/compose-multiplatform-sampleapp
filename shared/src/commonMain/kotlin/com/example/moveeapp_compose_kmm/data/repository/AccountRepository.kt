package com.example.moveeapp_compose_kmm.data.repository

import com.example.moveeapp_compose_kmm.core.SessionSettings
import com.example.moveeapp_compose_kmm.data.remote.ApiInterface
import com.example.moveeapp_compose_kmm.data.remote.model.account.AccountDetailModel
import com.example.moveeapp_compose_kmm.data.remote.model.account.favorite.AddFavoriteRequestModel
import com.example.moveeapp_compose_kmm.data.remote.model.account.favorite.AddFavoriteResponseModel
import com.example.moveeapp_compose_kmm.data.remote.model.account.favorite.FavoriteMovieModel
import com.example.moveeapp_compose_kmm.data.remote.model.account.favorite.FavoriteTvModel
import com.example.moveeapp_compose_kmm.domain.model.IsFavorite
import com.example.moveeapp_compose_kmm.utils.resultOf

class AccountRepository(
    private val api: ApiInterface,
    private val sessionSettings: SessionSettings
) {

    suspend fun getAccountDetail(): Result<AccountDetailModel> {
        return resultOf {
            api.accountDetails(sessionSettings.getSessionId() ?: "")
        }
    }

    suspend fun addFavorite(
        accountId: Int,
        addFavoriteRequestModel: AddFavoriteRequestModel
    ): Result<AddFavoriteResponseModel> {
        return resultOf {
            api.addFavorite(
                accountId,
                addFavoriteRequestModel,
                sessionSettings.getSessionId() ?: ""
            )
        }
    }

    suspend fun getMovieIsFavorite(movieId: Int): Result<IsFavorite> {
        return resultOf {
            val isFavorite = api.getMovieState(
                sessionId = sessionSettings.getSessionId() ?: "",
                movieId
            ).favorite
            IsFavorite(isFavorite ?: false)
        }
    }

    suspend fun getTvIsFavorite(tvId: Int): Result<IsFavorite> {
        return resultOf {
            val isFavorite = api.getTvState(
                sessionId = sessionSettings.getSessionId() ?: "", tvId
            ).favorite
            IsFavorite(isFavorite ?: false)
        }
    }

    suspend fun getFavoriteMovie(accountId: Int, sessionId: String): Result<FavoriteMovieModel> {
        return resultOf {
            api.getFavoriteMovie(accountId = accountId, sessionId = sessionId)
        }
    }

    suspend fun getFavoriteTv(accountId: Int, sessionId: String): Result<FavoriteTvModel> {
        return resultOf {
            api.getFavoriteTv(accountId = accountId, sessionId = sessionId)
        }
    }
}