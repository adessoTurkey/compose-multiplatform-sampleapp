package com.example.moveeapp_compose_kmm.data.repository

import com.example.moveeapp_compose_kmm.core.SessionSettings
import com.example.moveeapp_compose_kmm.data.remote.ApiInterface
import com.example.moveeapp_compose_kmm.data.remote.model.account.AddFavoriteRequestModel
import com.example.moveeapp_compose_kmm.utils.resultOf
import kotlinx.coroutines.flow.flow

class AccountRepository(
    private val api: ApiInterface,
    private val sessionSettings: SessionSettings
) {

    private val sessionId = sessionSettings.getSessionId()
    fun getAccountDetail() = flow {
        emit(
            resultOf {
                api.accountDetails(sessionSettings.getSessionId() ?: "")
            }
        )
    }

    fun addFavorite(accountId: Int,  addFavoriteRequestModel: AddFavoriteRequestModel) = flow {
        emit(
            resultOf {
                api.addFavorite(accountId, addFavoriteRequestModel, sessionSettings.getSessionId() ?: "")
            }
        )
    }
}