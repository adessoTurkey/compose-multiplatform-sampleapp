package com.example.moveeapp_compose_kmm.ui.scene.account

import cafe.adriel.voyager.core.model.coroutineScope
import com.example.moveeapp_compose_kmm.core.ViewModel
import com.example.moveeapp_compose_kmm.data.uimodel.account.AccountUiModel
import com.example.moveeapp_compose_kmm.domain.usecase.accountusecase.GetAccountDetailUseCase
import com.example.moveeapp_compose_kmm.domain.usecase.accountusecase.LogoutUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class AccountDetailViewModel(
    private val getAccountDetailUseCase: GetAccountDetailUseCase,
    private val logoutUseCase: LogoutUseCase
) : ViewModel {

    private val _uiState = MutableStateFlow(AccountUiState())
    val uiState: StateFlow<AccountUiState> = _uiState

    val logoutState = MutableStateFlow(false)

    init {
        getAccountDetail()
    }

    private fun getAccountDetail() {
        coroutineScope.launch {
            val result = getAccountDetailUseCase.execute()
            if (result.isSuccess) {
                _uiState.update { uiState ->
                    uiState.copy(
                        isLoading = false,
                        accountData = result.getOrNull()?.toUiModel() ?: AccountUiModel()
                    )
                }
            } else {
                _uiState.update { uiState ->
                    uiState.copy(isLoading = false, error = "Hata!")
                }
            }
        }
    }

    fun logout() {
        coroutineScope.launch {
            val result = logoutUseCase.execute()
            if (result.isSuccess) {
                logoutState.value = result.getOrNull()?.success ?: false
            }
        }
    }
}