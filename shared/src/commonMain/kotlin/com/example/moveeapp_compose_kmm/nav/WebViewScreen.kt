package com.example.moveeapp_compose_kmm.nav

import androidx.compose.runtime.Composable
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import com.example.moveeapp_compose_kmm.ui.scene.webviewscreen.WebViewContent

class WebViewScreen(private val url: String) : Screen {

    @Composable
    override fun Content() {
        val navigator = LocalNavigator.currentOrThrow
        WebViewContent(url = url, onBackPressed = navigator::pop)
    }
}
