package com.example.moveeapp_compose_kmm.ui.scene.loginscreen

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import cafe.adriel.voyager.core.model.coroutineScope
import com.example.moveeapp_compose_kmm.core.SessionSettings
import com.example.moveeapp_compose_kmm.core.ViewModel
import com.example.moveeapp_compose_kmm.data.repository.AccountRepository
import com.example.moveeapp_compose_kmm.data.repository.LoginRepository
import com.example.moveeapp_compose_kmm.data.repository.LoginState
import com.example.moveeapp_compose_kmm.utils.ShadredPrefConstants
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

class LoginViewModel(
    private val loginRepository: LoginRepository,
    private val accountRepository: AccountRepository,
    private val sessionSettings: SessionSettings
) : ViewModel {

    private val _isLoggedIn = MutableStateFlow(loginRepository.getLoginState())
    val isLoggedIn: StateFlow<LoginState>
        get() = _isLoggedIn
    var loginUiState by mutableStateOf(LoginUiState())
        private set

    fun onUserNameChange(username: String) {
        loginUiState = loginUiState.copy(userName = username, loginError = null)
    }

    fun onPasswordChange(password: String) {
        loginUiState = loginUiState.copy(password = password, loginError = null)
    }

    private fun validateLoginForm() =
        loginUiState.userName.isNotBlank() && loginUiState.password.isNotBlank()
    //todo username ve passworda gore farklı farklı mesaj eklenebilir


    fun login() {
        coroutineScope.launch {
            if (!validateLoginForm()) {
                loginUiState = loginUiState.copy(loginError = "Email and password can not be empty")
                return@launch
            }
            loginUiState = loginUiState.copy(isLoading = true, loginError = null)
            loginRepository.login(
                loginUiState.userName,
                loginUiState.password
            )
                .onSuccess {
                    getAccountDetail()
                }
                .onFailure { e ->
                    loginUiState = loginUiState.copy(isLoading = false, loginError = e.message)
                    e.printStackTrace()
                }
        }
    }

    private fun getAccountDetail() {
        accountRepository.getAccountDetail().onEach { result ->
            if (result.isSuccess) {
                sessionSettings.setInt(
                    key = ShadredPrefConstants.KEY_ACCOUNT_ID,
                    value = result.getOrNull()?.id ?: 0
                )
                loginUiState = loginUiState.copy(isLoading = false, isSuccessLogin = true)
                _isLoggedIn.value = LoginState.LOGGED_IN
            } else {
                "hata"
            }
        }.launchIn(coroutineScope)
    }
}
