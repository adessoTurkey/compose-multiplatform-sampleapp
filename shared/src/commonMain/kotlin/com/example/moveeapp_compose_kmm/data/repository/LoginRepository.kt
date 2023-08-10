package com.example.moveeapp_compose_kmm.data.repository

import com.example.moveeapp_compose_kmm.data.remote.ApiInterface
import com.example.moveeapp_compose_kmm.data.remote.model.login.LoginRequestModel
import com.example.moveeapp_compose_kmm.utils.invoke
import com.example.moveeapp_compose_kmm.utils.resultOf

class LoginRepository(private val api: ApiInterface) {

    suspend fun login(username: String, password: String) = resultOf {


        invoke {
            val requestTokenResponse = api.createRequestToken()

                api.createRequestTokenWithLogin(
                    LoginRequestModel(
                        username = username,
                        password = password,
                        request_token = requestTokenResponse.request_token
                    )
                )
//            val sessionResponse = api.createSession(
//                SessionRequestModel(
//                    loginRequestTokenResponse.request_token
//                )
//            )
        }
    }
}