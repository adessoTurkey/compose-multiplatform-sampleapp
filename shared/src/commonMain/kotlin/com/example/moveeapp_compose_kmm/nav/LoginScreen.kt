package com.example.moveeapp_compose_kmm.nav

import androidx.compose.runtime.Composable
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import com.example.moveeapp_compose_kmm.core.viewModel
import com.example.moveeapp_compose_kmm.ui.scene.login.LoginScreen
import com.example.moveeapp_compose_kmm.ui.scene.login.LoginViewModel

class LoginScreen : Screen {
    @Composable
    override fun Content() {
        val navigator = LocalNavigator.currentOrThrow
        val viewModel: LoginViewModel = viewModel()

        LoginScreen(
            viewModel = viewModel,
            navigateToWebViewScreen = { navigator.push(WebViewScreen(it)) },
            navigateToMainScreen = { navigator.replace(MainScreen()) }
        )
    }
}
