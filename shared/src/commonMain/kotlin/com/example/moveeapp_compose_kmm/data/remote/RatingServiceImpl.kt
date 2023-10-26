package com.example.moveeapp_compose_kmm.data.remote

import com.example.moveeapp_compose_kmm.data.remote.model.account.rate.RateDto
import com.example.moveeapp_compose_kmm.data.remote.model.account.rate.RateResponse
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.delete
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.http.ContentType
import io.ktor.http.contentType

class RatingServiceImpl(
    private val client: HttpClient,
) : RatingService {

    override suspend fun rateMovie(rating: RateDto, movieId: Int, sessionId: String): RateResponse {
        return client.post("movie/$movieId/rating") {
            contentType(ContentType.Application.Json)
            url {
                parameters.append(ApiImpl.SESSION_ID, sessionId)
            }
            setBody(rating)
        }.body()
    }

    override suspend fun rateTvShow(
        rating: RateDto,
        tvShowId: Int,
        sessionId: String
    ): RateResponse {
        return client.post("tv/$tvShowId/rating") {
            contentType(ContentType.Application.Json)
            url {
                parameters.append(ApiImpl.SESSION_ID, sessionId)
            }
            setBody(rating)
        }.body()
    }

    override suspend fun removeMovieRating(movieId: Int, sessionId: String) {
        client.delete("movie/$movieId/rating") {
            url {
                parameters.append(ApiImpl.SESSION_ID, sessionId)
            }
        }
    }

    override suspend fun removeTvShowRating(tvShowId: Int, sessionId: String) {
        client.delete("tv/$tvShowId/rating") {
            url {
                parameters.append(ApiImpl.SESSION_ID, sessionId)
            }
        }
    }
}
