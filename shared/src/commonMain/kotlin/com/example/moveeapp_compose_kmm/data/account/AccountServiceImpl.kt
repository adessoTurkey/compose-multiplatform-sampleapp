package com.example.moveeapp_compose_kmm.data.account

import com.example.moveeapp_compose_kmm.data.account.favorite.AccountStateDataModel
import com.example.moveeapp_compose_kmm.data.account.favorite.AccountStateResponse
import com.example.moveeapp_compose_kmm.data.account.favorite.AddFavoriteRequestModel
import com.example.moveeapp_compose_kmm.data.account.favorite.AddFavoriteResponseModel
import com.example.moveeapp_compose_kmm.data.account.favorite.FavoriteMovieModel
import com.example.moveeapp_compose_kmm.data.account.favorite.FavoriteTvModel
import com.example.moveeapp_compose_kmm.data.remote.ApiImpl
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.delete
import io.ktor.client.request.get
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.http.ContentType
import io.ktor.http.contentType

class AccountServiceImpl(
    private val client: HttpClient,
) : AccountService {

    override suspend fun accountDetails(sessionId: String): AccountDetailModel {
        return client.get(ACCOUNT) {
            url {
                parameters.append(SESSION_ID, sessionId)
            }
        }.body()
    }

    override suspend fun logout(logoutRequestModel: LogoutRequestModel): LogoutResponseModel {
        return client.delete(ApiImpl.LOGOUT) {
            setBody(logoutRequestModel)
            contentType(ContentType.Application.Json)
        }.body()
    }

    override suspend fun addFavorite(
        accountId: Int,
        addFavoriteRequestModel: AddFavoriteRequestModel,
        sessionId: String
    ): AddFavoriteResponseModel {
        return client.post("account/$accountId/favorite") {
            contentType(ContentType.Application.Json)
            url {
                parameters.append(ApiImpl.SESSION_ID, sessionId)
            }
            setBody(addFavoriteRequestModel)
        }.body()
    }

    override suspend fun getMovieState(sessionId: String, movieId: Int): AccountStateDataModel {
        return client.get("movie/${movieId}/account_states") {
            contentType(ContentType.Application.Json)
            url {
                parameters.append(ApiImpl.SESSION_ID, sessionId)
            }
        }.body<AccountStateResponse>().mapToDataModel()
    }

    override suspend fun getTvState(sessionId: String, tvId: Int): AccountStateDataModel {
        return client.get("tv/${tvId}/account_states") {
            contentType(ContentType.Application.Json)
            url {
                parameters.append(ApiImpl.SESSION_ID, sessionId)
            }
        }.body<AccountStateResponse>().mapToDataModel()
    }

    override suspend fun getFavoriteMovie(accountId: Int, sessionId: String): FavoriteMovieModel {
        return client.get("account/{$accountId}/favorite/movies") {
            url {
                parameters.append(ApiImpl.SESSION_ID, sessionId)
            }
        }.body()
    }

    override suspend fun getFavoriteTv(accountId: Int, sessionId: String): FavoriteTvModel {
        return client.get("account/{$accountId}/favorite/tv") {
            url {
                parameters.append(ApiImpl.SESSION_ID, sessionId)
            }
        }.body()
    }

    companion object {
        const val ACCOUNT = "account"
        const val SESSION_ID = "session_id"
    }
}

private fun AccountStateResponse.mapToDataModel(): AccountStateDataModel {
    return when (this) {
        is AccountStateResponse.RatedAccountStateResponse -> AccountStateDataModel(
            favorite = favorite,
            id = id,
            isRated = true,
            userRate = rated?.value,
            watchlist = watchlist
        )

        is AccountStateResponse.NotRatedAccountStateResponse -> AccountStateDataModel(
            favorite = favorite,
            id = id,
            isRated = rated,
            userRate = null,
            watchlist = watchlist
        )
    }
}
