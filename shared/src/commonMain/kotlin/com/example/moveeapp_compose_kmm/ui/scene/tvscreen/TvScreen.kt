package com.example.moveeapp_compose_kmm.ui.scene.tvscreen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import cafe.adriel.voyager.core.screen.Screen

class TvScreen : Screen {
    @Composable
    override fun Content() {
        TvContent()
    }
}

@Composable
fun TvContent() {
    Box(modifier = Modifier.fillMaxSize()) {
        Text("TV SCREEN", color = MaterialTheme.colorScheme.secondary)
    }
}