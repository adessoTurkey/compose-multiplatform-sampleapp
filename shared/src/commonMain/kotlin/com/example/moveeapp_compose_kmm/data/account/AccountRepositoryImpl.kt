package com.example.moveeapp_compose_kmm.data.account

import com.example.moveeapp_compose_kmm.data.remote.ApiInterface
import com.example.moveeapp_compose_kmm.data.remote.model.account.favorite.AddFavoriteRequestModel
import com.example.moveeapp_compose_kmm.data.remote.model.account.favorite.AddFavoriteResponseModel
import com.example.moveeapp_compose_kmm.data.remote.model.account.favorite.FavoriteMovieModel
import com.example.moveeapp_compose_kmm.data.remote.model.account.favorite.FavoriteTvModel
import com.example.moveeapp_compose_kmm.domain.account.AccountDetail
import com.example.moveeapp_compose_kmm.domain.account.AccountRepository
import com.example.moveeapp_compose_kmm.domain.account.SessionSettings
import com.example.moveeapp_compose_kmm.domain.model.MovieAccountState
import com.example.moveeapp_compose_kmm.domain.model.TvAccountState
import com.example.moveeapp_compose_kmm.utils.resultOf

class AccountRepositoryImpl(
    private val api: ApiInterface,
    private val accountService: AccountService,
    private val sessionSettings: SessionSettings,
) : AccountRepository {

    override suspend fun getAccountDetail(): Result<AccountDetail> {
        return resultOf {
            accountService.accountDetails(sessionSettings.getSessionId()!!)
                .run {
                    AccountDetail(id, name, username, country)
                }
        }
    }

    override suspend fun addFavorite(
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

    override suspend fun getMovieAccountState(movieId: Int): Result<MovieAccountState> {
        return resultOf {
            val response = api.getMovieState(
                sessionId = sessionSettings.getSessionId() ?: "",
                movieId
            )
            MovieAccountState(response.favorite ?: false, response.rated?.value)
        }
    }

    override suspend fun getTvAccountState(tvId: Int): Result<TvAccountState> {
        return resultOf {
            val response = api.getTvState(
                sessionId = sessionSettings.getSessionId() ?: "", tvId
            )
            TvAccountState(response.favorite ?: false, response.rated?.value)
        }
    }

    override suspend fun getFavoriteMovie(
        accountId: Int,
        sessionId: String
    ): Result<FavoriteMovieModel> {
        return resultOf {
            api.getFavoriteMovie(accountId = accountId, sessionId = sessionId)
        }
    }

    override suspend fun getFavoriteTv(accountId: Int, sessionId: String): Result<FavoriteTvModel> {
        return resultOf {
            api.getFavoriteTv(accountId = accountId, sessionId = sessionId)
        }
    }

    override suspend fun logout(sessionId: String?): Result<Boolean> {
        return resultOf {
            accountService.logout(LogoutRequestModel(sessionId!!)).success
        }
    }
}
