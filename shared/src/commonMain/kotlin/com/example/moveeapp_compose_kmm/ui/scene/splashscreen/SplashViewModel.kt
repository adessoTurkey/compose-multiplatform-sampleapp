package com.example.moveeapp_compose_kmm.ui.scene.splashscreen

import com.example.moveeapp_compose_kmm.core.ViewModel
import com.example.moveeapp_compose_kmm.data.account.LoginState
import com.example.moveeapp_compose_kmm.domain.account.AccountRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class SplashViewModel(
    repository: AccountRepository
) : ViewModel {

    private val _isLoggedIn = MutableStateFlow(repository.getLoginState())

    val isLoggedIn: StateFlow<LoginState>
        get() = _isLoggedIn
}
