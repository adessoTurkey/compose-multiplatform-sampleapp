package com.example.moveeapp_compose_kmm.data.account

import com.example.moveeapp_compose_kmm.data.account.favorite.AddFavoriteRequestModel
import com.example.moveeapp_compose_kmm.data.account.favorite.AddFavoriteResponseModel
import com.example.moveeapp_compose_kmm.domain.account.AccountDetail
import com.example.moveeapp_compose_kmm.domain.account.AccountRepository
import com.example.moveeapp_compose_kmm.domain.account.SessionSettings
import com.example.moveeapp_compose_kmm.domain.account.favorite.FavoriteMovie
import com.example.moveeapp_compose_kmm.domain.account.favorite.FavoriteTv
import com.example.moveeapp_compose_kmm.domain.account.favorite.MovieAccountState
import com.example.moveeapp_compose_kmm.domain.account.favorite.TvAccountState
import com.example.moveeapp_compose_kmm.utils.resultOf

class AccountRepositoryImpl(
    private val service: AccountService,
    private val sessionSettings: SessionSettings,
) : AccountRepository {

    override suspend fun getAccountDetail(): Result<AccountDetail> {
        return resultOf {
            service.accountDetails(sessionSettings.getSessionId()!!)
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
            service.addFavorite(
                accountId,
                addFavoriteRequestModel,
                sessionSettings.getSessionId() ?: ""
            )
        }
    }

    override suspend fun getMovieAccountState(movieId: Int): Result<MovieAccountState> {
        return resultOf {
            val response = service.getMovieState(
                sessionId = sessionSettings.getSessionId() ?: "",
                movieId
            )
            MovieAccountState(response.favorite ?: false, response.rated?.value)
        }
    }

    override suspend fun getTvAccountState(tvId: Int): Result<TvAccountState> {
        return resultOf {
            val response = service.getTvState(
                sessionId = sessionSettings.getSessionId() ?: "", tvId
            )
            TvAccountState(response.favorite ?: false, response.rated?.value)
        }
    }

    override suspend fun getFavoriteMovie(
        accountId: Int,
        sessionId: String
    ): Result<List<FavoriteMovie>> {
        return resultOf {
            service.getFavoriteMovie(
                accountId = accountId,
                sessionId = sessionId
            ).favMovies.map { it.toDomain() }
        }
    }

    override suspend fun getFavoriteTv(
        accountId: Int,
        sessionId: String
    ): Result<List<FavoriteTv>> {
        return resultOf {
            service.getFavoriteTv(
                accountId = accountId,
                sessionId = sessionId
            ).favTv.map { it.toDomain() }
        }
    }

    override suspend fun logout(sessionId: String?): Result<Boolean> {
        return resultOf {
            service.logout(LogoutRequestModel(sessionId!!)).success
        }
    }
}
