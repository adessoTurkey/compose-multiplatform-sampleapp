package com.example.moveeapp_compose_kmm.domain.usecase.accountusecase

import com.example.moveeapp_compose_kmm.TestSettingsProvider
import com.example.moveeapp_compose_kmm.data.repository.FakeAccountRepository
import com.example.moveeapp_compose_kmm.domain.account.SessionSettings
import kotlinx.coroutines.test.runTest
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

private const val ACCOUNT_ID = "acc-123"

class LogoutUseCaseTest {
    private lateinit var useCase: LogoutUseCase

    private lateinit var accountRepository: FakeAccountRepository

    private lateinit var settings: SessionSettings

    @BeforeTest
    fun start() {
        accountRepository = FakeAccountRepository()
        settings = SessionSettings(TestSettingsProvider())
        useCase = LogoutUseCase(accountRepository, settings)
    }

    @Test
    fun `when logout succeeds true result returns`() = runTest {
        settings.setSessionId(ACCOUNT_ID)
        accountRepository.logoutResponseModel = true

        val result = useCase.execute()

        assertTrue(result.getOrNull()!!)
    }

    @Test
    fun `when logout succeeds session id is removed`() = runTest {
        settings.setSessionId(ACCOUNT_ID)
        accountRepository.logoutResponseModel = true

        useCase.execute()

        assertTrue(settings.getSessionId().isNullOrBlank())
    }

    @Test
    fun `when logout fails session id remains as is`() = runTest {
        settings.setSessionId(ACCOUNT_ID)
        accountRepository.logoutResponseModel = null

        useCase.execute()

        assertEquals(ACCOUNT_ID, settings.getSessionId())
    }
}
