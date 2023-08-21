package com.example.moveeapp_compose_kmm

import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import cafe.adriel.voyager.navigator.Navigator
import com.example.moveeapp_compose_kmm.ui.scene.loginscreen.LoginScreen

@Composable
internal fun App() {
    MaterialTheme {
        Scaffold {
            Navigator(LoginScreen())
        }
    }
}