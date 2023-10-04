package com.example.moveeapp_compose_kmm.data.repository

import com.example.moveeapp_compose_kmm.core.SessionSettings
import com.example.moveeapp_compose_kmm.data.remote.ApiInterface
import com.example.moveeapp_compose_kmm.data.remote.model.account.AccountDetailModel
import com.example.moveeapp_compose_kmm.data.remote.model.account.favorite.AddFavoriteRequestModel
import com.example.moveeapp_compose_kmm.data.remote.model.account.favorite.AddFavoriteResponseModel
import com.example.moveeapp_compose_kmm.data.remote.model.account.favorite.FavoriteMovieModel
import com.example.moveeapp_compose_kmm.data.remote.model.account.favorite.FavoriteTvModel
import com.example.moveeapp_compose_kmm.data.remote.model.account.rate.RateDto
import com.example.moveeapp_compose_kmm.data.remote.model.login.LogoutRequestModel
import com.example.moveeapp_compose_kmm.data.remote.model.login.LogoutResponseModel
import com.example.moveeapp_compose_kmm.domain.model.MovieAccountState
import com.example.moveeapp_compose_kmm.domain.model.TvAccountState
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

    suspend fun getMovieAccountState(movieId: Int): Result<MovieAccountState> {
        return resultOf {
            val response = api.getMovieState(
                sessionId = sessionSettings.getSessionId() ?: "",
                movieId
            )
            MovieAccountState(response.favorite ?: false, response.rated?.value)
        }
    }

    suspend fun getTvAccountState(tvId: Int): Result<TvAccountState> {
        return resultOf {
            val response = api.getTvState(
                sessionId = sessionSettings.getSessionId() ?: "", tvId
            )
            TvAccountState(response.favorite ?: false, response.rated?.value)
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

    suspend fun logout(logoutRequestModel: LogoutRequestModel): Result<LogoutResponseModel> {
        return resultOf {
            api.logout(logoutRequestModel)
        }
    }

    suspend fun rateMovie(rating: Double, movieId: Int): Result<Boolean> {
        val result = resultOf {
            api.rateMovie(
                rating = RateDto(value = rating),
                movieId = movieId,
                sessionId = sessionSettings.getSessionId()!!
            )
        }

        return result.map { it.success }
    }

    suspend fun removeMovieRating(movieId: Int): Result<Unit> {
        return resultOf {
            api.removeMovieRating(movieId, sessionSettings.getSessionId()!!)
        }
    }

    suspend fun removeTvShowRating(tvShowId: Int): Result<Unit> {
        return resultOf {
            api.removeTvShowRating(tvShowId, sessionSettings.getSessionId()!!)
        }
    }

    suspend fun rateTvShow(rating: Double, movieId: Int): Result<Boolean> {
        val result = resultOf {
            api.rateTvShow(
                rating = RateDto(value = rating),
                tvShowId = movieId,
                sessionId = sessionSettings.getSessionId()!!
            )
        }

        return result.map { it.success }
    }
}