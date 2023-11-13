package com.example.moveeapp_compose_kmm.data.account

import com.example.moveeapp_compose_kmm.data.account.login.LoginRequestModel
import com.example.moveeapp_compose_kmm.data.account.login.LoginResponseModel
import com.example.moveeapp_compose_kmm.data.account.login.RequestTokenResponseModel
import com.example.moveeapp_compose_kmm.data.account.login.SessionRequestModel
import com.example.moveeapp_compose_kmm.data.account.login.SessionResponseModel

interface AccountService {

    suspend fun accountDetails(sessionId: String): AccountDetailModel

    //login
    suspend fun createRequestToken(): RequestTokenResponseModel

    suspend fun createRequestTokenWithLogin(requestModel: LoginRequestModel): LoginResponseModel

    suspend fun createSession(requestModel: SessionRequestModel): SessionResponseModel

    suspend fun logout(logoutRequestModel: LogoutRequestModel): LogoutResponseModel

}
