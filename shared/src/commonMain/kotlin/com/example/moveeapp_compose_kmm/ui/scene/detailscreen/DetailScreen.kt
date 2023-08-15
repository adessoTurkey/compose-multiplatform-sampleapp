package com.example.moveeapp_compose_kmm.ui.scene.detailscreen

import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.screen.Screen

class DetailScreen : Screen {
    @Composable
    override fun Content() {
        DetailScreen()
    }

    @Composable
    fun DetailScreen() {

        Text(modifier = Modifier.padding(30.dp), text = "DetailToHome")
    }
}
