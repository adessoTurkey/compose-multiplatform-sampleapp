package com.example.moveeapp_compose_kmm.ui.scene.account

import com.example.moveeapp_compose_kmm.CoroutineTest
import com.example.moveeapp_compose_kmm.domain.account.SessionSettings
import com.example.moveeapp_compose_kmm.data.account.LogoutResponseModel
import com.example.moveeapp_compose_kmm.data.repository.FakeAccountRepository
import com.example.moveeapp_compose_kmm.domain.account.AccountDetail
import com.example.moveeapp_compose_kmm.domain.account.GetAccountDetailUseCase
import com.example.moveeapp_compose_kmm.domain.usecase.accountusecase.LogoutUseCase
import com.example.moveeapp_compose_kmm.TestSettingsProvider
import kotlinx.coroutines.test.runTest
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class AccountDetailViewModelTest : CoroutineTest() {
    private lateinit var viewModel: AccountDetailViewModel
    private val repository = FakeAccountRepository()

    override fun setUp() {
        viewModel = AccountDetailViewModel(
            GetAccountDetailUseCase(repository),
            LogoutUseCase(repository, SessionSettings(TestSettingsProvider()))
        )
    }

    @Test
    fun `failed account detail`() = runTest {
        // Given
        repository.accountDetailModel = null

        // When
        viewModel.getAccountDetail()

        // Then
        assertEquals(AccountUiState(false, "Hata!"), viewModel.uiState.value)
    }

    @Test
    fun `loading state should be true initially then false`() = runTest {
        // Given
        repository.accountDetailModel = AccountDetail(1, "", "username", "name")

        assertTrue(viewModel.uiState.value.isLoading)
        viewModel.getAccountDetail()
        assertFalse(viewModel.uiState.value.isLoading)
    }

    @Test
    fun `successful account detail`() = runTest {
        // Given
        repository.accountDetailModel = AccountDetail(1, "name", "username", "tr")

        // When
        viewModel.getAccountDetail()

        // Then
        assertEquals(
            AccountUiState(false, null, AccountDetail(1, "name", "username", "tr")),
            viewModel.uiState.value
        )
    }

    @Test
    fun `successful logout should return true`() = runTest {
        // Given
        repository.logoutResponseModel = LogoutResponseModel(true)

        // When
        viewModel.logout()

        // Then
        assertTrue(viewModel.logoutState.value)
    }
}
