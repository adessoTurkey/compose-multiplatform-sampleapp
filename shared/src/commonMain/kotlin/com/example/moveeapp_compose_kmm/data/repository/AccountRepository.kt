package com.example.moveeapp_compose_kmm.data.repository

import com.example.moveeapp_compose_kmm.core.SessionSettings
import com.example.moveeapp_compose_kmm.data.remote.ApiInterface
import com.example.moveeapp_compose_kmm.data.remote.model.account.AccountResponse
import com.example.moveeapp_compose_kmm.data.remote.model.account.AddFavoriteModel
import com.example.moveeapp_compose_kmm.data.remote.model.account.AddFavoriteRequestModel
import com.example.moveeapp_compose_kmm.domain.model.IsFavorite
import com.example.moveeapp_compose_kmm.utils.resultOf

class AccountRepository(
    private val api: ApiInterface,
    private val sessionSettings: SessionSettings
) {

    suspend fun getAccountDetail(): Result<AccountResponse> {
        return resultOf {
            api.accountDetails(sessionSettings.getSessionId() ?: "")
        }
    }

    suspend fun addFavorite(
        accountId: Int,
        addFavoriteRequestModel: AddFavoriteRequestModel
    ): Result<AddFavoriteModel> {
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
}