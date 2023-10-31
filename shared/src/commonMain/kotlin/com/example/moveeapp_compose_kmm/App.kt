package com.example.moveeapp_compose_kmm

import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.LaunchedEffect
import cafe.adriel.voyager.navigator.CurrentScreen
import cafe.adriel.voyager.navigator.Navigator
import com.example.moveeapp_compose_kmm.nav.LocalMainNavigator
import com.example.moveeapp_compose_kmm.nav.SplashScreen
import com.example.moveeapp_compose_kmm.ui.theme.AppTheme

@Composable
fun App() {
    AppTheme {
        Navigator(SplashScreen()) {
            CompositionLocalProvider(LocalMainNavigator provides it) {
                CurrentScreen()
            }
        }

        LaunchedEffect(Unit) {
            log { "App started." }
        }
    }
}
