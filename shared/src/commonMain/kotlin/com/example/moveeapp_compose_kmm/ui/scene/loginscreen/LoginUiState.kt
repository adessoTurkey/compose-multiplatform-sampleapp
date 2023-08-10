package com.example.moveeapp_compose_kmm.ui.scene.loginscreen

data class LoginUiState(
    val userName:String = "",
    val password:String = "",
    val isLoading:Boolean = false,
    val isSuccessLogin:Boolean = false,
    val loginError:String? = null,
)