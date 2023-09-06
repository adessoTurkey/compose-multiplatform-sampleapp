package com.example.moveeapp_compose_kmm.ui.scene.splashscreen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import com.example.moveeapp_compose_kmm.MainScreen
import com.example.moveeapp_compose_kmm.core.viewModel
import com.example.moveeapp_compose_kmm.data.repository.LoginState
import com.example.moveeapp_compose_kmm.ui.scene.loginscreen.LoginScreen

class SplashScreen : Screen {
    @Composable
    override fun Content() { //todo splash api

        val navigator = LocalNavigator.currentOrThrow
        val viewModel: SplashViewModel = viewModel()

        Spacer(modifier = Modifier.fillMaxSize().background(MaterialTheme.colorScheme.primary))
        val uiState by viewModel.isLoggedIn.collectAsState()
        when (uiState) {
            LoginState.LOGGED_OUT -> {
                navigator.replace(LoginScreen())
            }

            LoginState.LOGGED_IN -> {
                navigator.replace(MainScreen)
            }
        }
    }
}