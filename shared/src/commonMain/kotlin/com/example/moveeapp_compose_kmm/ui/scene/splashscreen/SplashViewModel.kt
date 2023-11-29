package com.example.moveeapp_compose_kmm.ui.scene.splashscreen

import com.example.moveeapp_compose_kmm.core.ViewModel
import com.example.moveeapp_compose_kmm.domain.account.AccountRepository

class SplashViewModel(
    repository: AccountRepository
) : ViewModel {
    val isLoggedIn = repository.getLoginState()
}
