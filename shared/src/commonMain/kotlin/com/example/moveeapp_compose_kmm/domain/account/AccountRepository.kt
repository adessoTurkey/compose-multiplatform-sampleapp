package com.example.moveeapp_compose_kmm.domain.account

import com.example.moveeapp_compose_kmm.data.account.LoginState
import com.example.moveeapp_compose_kmm.data.account.login.LoginResponseModel
import kotlinx.coroutines.flow.StateFlow

interface AccountRepository {
    suspend fun getAccountDetail(): Result<AccountDetail>

    suspend fun login(username: String, password: String): Result<LoginResponseModel>

    fun getLoginState(): StateFlow<LoginState>

    suspend fun logout(sessionId: String?): Result<Boolean>
}
