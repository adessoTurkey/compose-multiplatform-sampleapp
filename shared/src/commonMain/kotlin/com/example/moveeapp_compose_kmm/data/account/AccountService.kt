package com.example.moveeapp_compose_kmm.data.account

interface AccountService {

    suspend fun accountDetails(sessionId: String): AccountDetailModel

    suspend fun logout(logoutRequestModel: LogoutRequestModel): LogoutResponseModel
}
