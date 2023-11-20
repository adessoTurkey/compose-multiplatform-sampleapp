package com.example.moveeapp_compose_kmm.core

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun ErrorMessage(viewModel: ViewModel, backgroundColor: Color, content: @Composable () -> Unit) {
    val errorMessage by viewModel.errorMessage.collectAsState(null)

    ErrorMessage(message = errorMessage, backgroundColor = backgroundColor) {
        content()
    }
}

@Composable
fun ErrorMessage(
    message: String?,
    backgroundColor: Color,
    content: @Composable () -> Unit
) {
    if (message != null) {
        val text = getTextOrNull(message) ?: message

        Surface(
            modifier = Modifier.fillMaxSize(),
            color = backgroundColor,
        ) {
            Box(Modifier.fillMaxSize(), Alignment.Center) {
                Text(text = text, modifier = Modifier.padding(16.dp))
            }
        }
    } else {
        content()
    }
}
