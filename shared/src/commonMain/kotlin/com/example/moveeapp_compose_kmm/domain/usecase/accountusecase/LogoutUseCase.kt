package com.example.moveeapp_compose_kmm.domain.usecase.accountusecase

import com.example.moveeapp_compose_kmm.core.SessionSettings
import com.example.moveeapp_compose_kmm.data.remote.model.login.LogoutRequestModel
import com.example.moveeapp_compose_kmm.data.remote.model.login.LogoutResponseModel
import com.example.moveeapp_compose_kmm.data.repository.AccountRepository

class LogoutUseCase(
    private val repository: AccountRepository,
    private val sessionSettings: SessionSettings
) {

    suspend fun execute(): Result<LogoutResponseModel> {
        return repository.logout(LogoutRequestModel(sessionId = sessionSettings.getSessionId() ?: ""))
    }
}