package com.example.moveeapp_compose_kmm.ui.scene.splashscreen

import com.example.moveeapp_compose_kmm.core.ViewModel
import com.example.moveeapp_compose_kmm.data.repository.LoginRepository
import com.example.moveeapp_compose_kmm.data.repository.LoginState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class SplashViewModel(
    private val repository: LoginRepository
) : ViewModel {

    private val _isLoggedIn = MutableStateFlow(repository.getLoginState())

    val isLoggedIn: StateFlow<LoginState>
        get() = _isLoggedIn

}