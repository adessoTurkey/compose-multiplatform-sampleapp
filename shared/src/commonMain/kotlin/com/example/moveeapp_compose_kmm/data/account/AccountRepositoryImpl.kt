package com.example.moveeapp_compose_kmm.data.account

import com.example.moveeapp_compose_kmm.data.account.login.LoginRequestModel
import com.example.moveeapp_compose_kmm.data.account.login.SessionRequestModel
import com.example.moveeapp_compose_kmm.domain.account.AccountDetail
import com.example.moveeapp_compose_kmm.domain.account.AccountRepository
import com.example.moveeapp_compose_kmm.domain.account.SessionSettings
import com.example.moveeapp_compose_kmm.utils.resultOf

class AccountRepositoryImpl(
    private val service: AccountService,
    private val sessionSettings: SessionSettings,
) : AccountRepository {

    override suspend fun getAccountDetail(): Result<AccountDetail> {
        return resultOf {
            service.accountDetails(sessionSettings.getSessionId()!!)
                .run {
                    AccountDetail(id, name, username, country)
                }
        }
    }

    private val sessionId = sessionSettings.getSessionId()
    override fun getLoginState(): LoginState {
        return if (sessionId.isNullOrEmpty()) {
            LoginState.LOGGED_OUT
        } else {
            LoginState.LOGGED_IN
        }
    }

    override suspend fun login(username: String, password: String) = resultOf {
        com.example.moveeapp_compose_kmm.utils.invoke {
            val requestTokenResponse = service.createRequestToken()

            val loginRequestTokenResponse =
                service.createRequestTokenWithLogin(
                    LoginRequestModel(
                        username = username,
                        password = password,
                        requestToken = requestTokenResponse.requestToken
                    )
                )
            val sessionResponse = service.createSession(
                SessionRequestModel(
                    loginRequestTokenResponse.requestToken
                )
            )
            sessionSettings.setSessionId(sessionResponse.sessionId)

            loginRequestTokenResponse
        }
    }

    override suspend fun logout(sessionId: String?): Result<Boolean> {
        return resultOf {
            service.logout(LogoutRequestModel(sessionId!!)).success
        }
    }
}

enum class LoginState {
    LOGGED_IN,
    LOGGED_OUT
}

