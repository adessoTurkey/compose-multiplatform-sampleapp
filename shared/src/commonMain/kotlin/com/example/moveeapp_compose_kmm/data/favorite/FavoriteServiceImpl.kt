package com.example.moveeapp_compose_kmm.data.favorite

import com.example.moveeapp_compose_kmm.utils.Constants.SESSION_ID
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.http.ContentType
import io.ktor.http.contentType

class FavoriteServiceImpl(
    private val client: HttpClient
) : FavoriteService {

    override suspend fun addFavorite(
        accountId: Int,
        addFavoriteRequestModel: AddFavoriteRequestModel,
        sessionId: String
    ): AddFavoriteResponseModel {
        return client.post("account/$accountId/favorite") {
            contentType(ContentType.Application.Json)
            url {
                parameters.append(SESSION_ID, sessionId)
            }
            setBody(addFavoriteRequestModel)
        }.body()
    }

    override suspend fun getFavoriteMovie(accountId: Int, sessionId: String): FavoriteMovieModel {
        return client.get("account/{$accountId}/favorite/movies") {
            url {
                parameters.append(SESSION_ID, sessionId)
            }
        }.body()
    }

    override suspend fun getFavoriteTv(accountId: Int, sessionId: String): FavoriteTvModel {
        return client.get("account/{$accountId}/favorite/tv") {
            url {
                parameters.append(SESSION_ID, sessionId)
            }
        }.body()
    }
}
