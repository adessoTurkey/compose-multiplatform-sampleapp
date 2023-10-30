package com.example.moveeapp_compose_kmm.ui.scene.webviewscreen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.moveeapp_compose_kmm.core.WebView
import com.example.moveeapp_compose_kmm.ui.components.TransparentIconHolder

@Composable
fun WebViewContent(url: String, onBackPressed: () -> Unit) {
    Column(modifier = Modifier.fillMaxSize()) {
        Row(
            modifier = Modifier.height(70.dp)
                .fillMaxWidth()
                .background(color = Color.Black.copy(alpha = 0.6f)),
            horizontalArrangement = Arrangement.Start,
            verticalAlignment = Alignment.CenterVertically
        ) {
            TransparentIconHolder(icon = Icons.Rounded.ArrowBack, onClick = onBackPressed)
        }
        WebView(modifier = Modifier.fillMaxSize(), url)
    }
}
