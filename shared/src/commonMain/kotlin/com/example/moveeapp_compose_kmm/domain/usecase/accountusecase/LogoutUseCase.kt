package com.example.moveeapp_compose_kmm.domain.usecase.accountusecase

import com.example.moveeapp_compose_kmm.domain.account.SessionSettings
import com.example.moveeapp_compose_kmm.domain.account.AccountRepository

class LogoutUseCase(
    private val repository: AccountRepository,
    private val sessionSettings: SessionSettings
) {

    suspend fun execute(): Result<Boolean> {
        val result = repository.logout(sessionSettings.getSessionId())

        if (result.isSuccess) sessionSettings.setSessionId("")

        return result
    }
}
