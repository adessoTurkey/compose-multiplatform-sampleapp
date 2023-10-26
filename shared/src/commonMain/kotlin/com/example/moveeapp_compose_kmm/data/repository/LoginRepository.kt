package com.example.moveeapp_compose_kmm.data.repository


import com.example.moveeapp_compose_kmm.domain.account.SessionSettings
import com.example.moveeapp_compose_kmm.data.remote.ApiInterface
import com.example.moveeapp_compose_kmm.data.remote.model.login.LoginRequestModel
import com.example.moveeapp_compose_kmm.data.remote.model.login.SessionRequestModel
import com.example.moveeapp_compose_kmm.utils.invoke
import com.example.moveeapp_compose_kmm.utils.resultOf

class LoginRepository(
    private val api: ApiInterface,
    private val sessionSettings: SessionSettings
) {

    private val sessionId = sessionSettings.getSessionId()
    fun getLoginState(): LoginState {
        return if (sessionId.isNullOrEmpty()) {
            LoginState.LOGGED_OUT
        } else {
            LoginState.LOGGED_IN
        }
    }

    suspend fun login(username: String, password: String) = resultOf {
        invoke {
            val requestTokenResponse = api.createRequestToken()

            val loginRequestTokenResponse =
                api.createRequestTokenWithLogin(
                    LoginRequestModel(
                        username = username,
                        password = password,
                        request_token = requestTokenResponse.request_token
                    )
                )
            val sessionResponse = api.createSession(
                SessionRequestModel(
                    loginRequestTokenResponse.request_token
                )
            )

            sessionSettings.setSessionId(sessionResponse.session_id)

            loginRequestTokenResponse
        }
    }
}

enum class LoginState {
    LOGGED_IN,
    LOGGED_OUT
}
