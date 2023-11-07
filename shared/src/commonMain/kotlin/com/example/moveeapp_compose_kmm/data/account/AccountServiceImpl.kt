package com.example.moveeapp_compose_kmm.data.account

import com.example.moveeapp_compose_kmm.data.account.login.LoginRequestModel
import com.example.moveeapp_compose_kmm.data.account.login.LoginResponseModel
import com.example.moveeapp_compose_kmm.data.account.login.RequestTokenResponseModel
import com.example.moveeapp_compose_kmm.data.account.login.SessionRequestModel
import com.example.moveeapp_compose_kmm.data.account.login.SessionResponseModel
import com.example.moveeapp_compose_kmm.data.account.favorite.AccountStateResponse
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

    //login

    override suspend fun createRequestToken(): RequestTokenResponseModel {
        return client.get(REQUEST_TOKEN).body()
    }

    override suspend fun createRequestTokenWithLogin(requestModel: LoginRequestModel): LoginResponseModel {
        return client.post(LOGIN) {
            contentType(ContentType.Application.Json)
            setBody(requestModel)
        }.body()
    }

    override suspend fun createSession(requestModel: SessionRequestModel): SessionResponseModel {
        return client.post(SESSION) {
            contentType(ContentType.Application.Json)
            setBody(requestModel)
        }.body()
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

    override suspend fun logout(logoutRequestModel: LogoutRequestModel): LogoutResponseModel {
        return client.delete(ApiImpl.LOGOUT) {
            setBody(logoutRequestModel)
            contentType(ContentType.Application.Json)
        }.body()
    }

    companion object {
        const val ACCOUNT = "account"
        const val SESSION_ID = "session_id"
        const val REQUEST_TOKEN = "authentication/token/new"
        const val LOGIN = "authentication/token/validate_with_login"
        const val SESSION = "authentication/session/new"
    }
}
