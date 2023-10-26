package com.example.moveeapp_compose_kmm.data.account

import com.example.moveeapp_compose_kmm.data.remote.ApiImpl
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.delete
import io.ktor.client.request.get
import io.ktor.client.request.setBody
import io.ktor.http.ContentType
import io.ktor.http.contentType

class AccountServiceImpl(
    private val client: HttpClient,
) : AccountService {

    override suspend fun accountDetails(sessionId: String): AccountDetailModel {
        return client.get("account") {
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
}
