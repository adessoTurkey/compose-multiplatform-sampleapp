package com.example.moveeapp_compose_kmm.ui.scene.loginscreen

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import cafe.adriel.voyager.core.model.ScreenModel
import cafe.adriel.voyager.core.model.coroutineScope
import com.example.moveeapp_compose_kmm.data.repository.LoginRepository
import kotlinx.coroutines.launch

class LoginViewModel(
    private val repository: LoginRepository
) : ScreenModel {

    val hasUser: LoginState
        get() = if (loginUiState.isSuccessLogin){
            LoginState.LOGGED_IN
        }else{
            LoginState.LOGGED_OUT
        }

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
            repository.login(
                loginUiState.userName,
                loginUiState.password
            )
                .onSuccess {
                    loginUiState = loginUiState.copy(isLoading = false, isSuccessLogin = true)
                }
                .onFailure { e ->
                    loginUiState = loginUiState.copy(isLoading = false, loginError = e.message)
                    e.printStackTrace()
                }
        }
    }
}

enum class LoginState {
    LOGGED_IN,
    LOGGED_OUT
}