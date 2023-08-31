package com.example.moveeapp_compose_kmm

import androidx.compose.runtime.Composable
import cafe.adriel.voyager.navigator.Navigator
import com.example.moveeapp_compose_kmm.ui.scene.loginscreen.LoginScreen
import com.example.moveeapp_compose_kmm.ui.theme.AppTheme

@Composable
internal fun App() {
    AppTheme {
        Navigator(LoginScreen())
    }
}