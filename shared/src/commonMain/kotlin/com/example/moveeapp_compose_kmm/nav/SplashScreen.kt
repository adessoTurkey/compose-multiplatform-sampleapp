package com.example.moveeapp_compose_kmm.nav

import androidx.compose.runtime.Composable
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import com.example.moveeapp_compose_kmm.core.viewModel
import com.example.moveeapp_compose_kmm.ui.scene.splashscreen.SplashScreen
import com.example.moveeapp_compose_kmm.ui.scene.splashscreen.SplashViewModel

class SplashScreen : Screen {

    @Composable
    override fun Content() { //todo splash api

        val navigator = LocalNavigator.currentOrThrow
        val viewModel: SplashViewModel = viewModel()

        SplashScreen(
            viewModel = viewModel,
            navigateToLogin = { navigator.replace(LoginScreen()) },
            navigateToMain = { navigator.replace(MainScreen()) }
        )
    }
}
