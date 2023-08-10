package com.example.moveeapp_compose_kmm.data.remote

import com.example.moveeapp_compose_kmm.data.remote.model.PopularMovieModel
import com.example.moveeapp_compose_kmm.data.remote.model.login.LoginRequestModel
import com.example.moveeapp_compose_kmm.data.remote.model.login.LoginResponseModel
import com.example.moveeapp_compose_kmm.data.remote.model.login.RequestTokenResponseModel
import com.example.moveeapp_compose_kmm.data.remote.model.login.SessionRequestModel
import com.example.moveeapp_compose_kmm.data.remote.model.login.SessionResponseModel
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.http.ContentType
import io.ktor.http.contentType

class ApiImpl(private val client: HttpClient) : ApiInterface {


    override suspend fun popularMovieList(page: Int): PopularMovieModel {
        return client.get(POPULAR_MOVIE) {
            url {
                parameters.append("page", page.toString())
            }
        }.body()
    }

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

    companion object {
        const val POPULAR_MOVIE = "movie/popular"
        const val REQUEST_TOKEN = "authentication/token/new"
        const val LOGIN = "authentication/token/validate_with_login"
        const val SESSION = "authentication/session/new"
    }
}


