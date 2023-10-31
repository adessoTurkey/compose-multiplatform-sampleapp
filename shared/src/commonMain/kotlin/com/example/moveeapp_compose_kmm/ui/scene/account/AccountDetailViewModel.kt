package com.example.moveeapp_compose_kmm.ui.scene.account

import com.example.moveeapp_compose_kmm.core.ViewModel
import com.example.moveeapp_compose_kmm.core.viewModelScope
import com.example.moveeapp_compose_kmm.domain.account.GetAccountDetailUseCase
import com.example.moveeapp_compose_kmm.domain.usecase.accountusecase.LogoutUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class AccountDetailViewModel(
    private val getAccountDetailUseCase: GetAccountDetailUseCase,
    private val logoutUseCase: LogoutUseCase
) : ViewModel {

    private val _uiState = MutableStateFlow(AccountUiState())
    val uiState: StateFlow<AccountUiState> = _uiState

    private val _logoutState = MutableStateFlow(false)
    val logoutState = _logoutState.asStateFlow()

    fun getAccountDetail() {
        viewModelScope.launch {
            getAccountDetailUseCase.execute()
                .onSuccess {
                    _uiState.update { uiState ->
                        uiState.copy(
                            isLoading = false,
                            accountData = it
                        )
                    }
                }
                .onFailure {
                    _uiState.update { uiState ->
                        uiState.copy(isLoading = false, error = "Hata!")
                    }
                }
        }
    }

    fun logout() {
        viewModelScope.launch {
            val result = logoutUseCase.execute()
            if (result.isSuccess) {
                _logoutState.value = result.getOrNull() ?: false
            }
        }
    }
}
