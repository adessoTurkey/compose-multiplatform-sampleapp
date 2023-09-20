package com.example.moveeapp_compose_kmm.ui.scene.account

import cafe.adriel.voyager.core.model.coroutineScope
import com.example.moveeapp_compose_kmm.core.ViewModel
import com.example.moveeapp_compose_kmm.data.repository.AccountRepository
import com.example.moveeapp_compose_kmm.data.uimodel.AccountUiModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update

class AccountDetailViewModel(
    private val repository: AccountRepository
) : ViewModel {

    private val _uiState = MutableStateFlow(AccountUiState())
    val uiState: StateFlow<AccountUiState> = _uiState

    init {
        getAccountDetail()
    }

    private fun getAccountDetail() {
        repository.getAccountDetail().onEach { result ->
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
        }.launchIn(coroutineScope)
    }
}