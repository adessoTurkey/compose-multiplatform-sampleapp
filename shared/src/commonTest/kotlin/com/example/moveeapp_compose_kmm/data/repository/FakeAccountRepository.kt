package com.example.moveeapp_compose_kmm.data.repository

import com.example.moveeapp_compose_kmm.data.account.LoginState
import com.example.moveeapp_compose_kmm.data.account.login.LoginResponseModel
import com.example.moveeapp_compose_kmm.domain.account.AccountDetail
import com.example.moveeapp_compose_kmm.domain.account.AccountRepository
import kotlinx.coroutines.flow.StateFlow

class FakeAccountRepository : AccountRepository {
    var accountDetailModel: AccountDetail? = null
    var loginResponseModel: LoginResponseModel? = null
    var loginStateFlow: StateFlow<LoginState>? = null
    var logoutResponseModel: Boolean? = null

    override suspend fun getAccountDetail(): Result<AccountDetail> =
        accountDetailModel.runCatching { this!! }

    override suspend fun login(username: String, password: String): Result<LoginResponseModel> =
        loginResponseModel.runCatching { this!! }

    override fun getLoginState(): StateFlow<LoginState> = loginStateFlow!!

    override suspend fun logout(sessionId: String?): Result<Boolean> =
        logoutResponseModel.runCatching { this!! }
}
