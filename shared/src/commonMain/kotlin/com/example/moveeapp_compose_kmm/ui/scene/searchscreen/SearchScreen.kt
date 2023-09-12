package com.example.moveeapp_compose_kmm.ui.scene.searchscreen

import androidx.compose.foundation.layout.Box
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import cafe.adriel.voyager.core.screen.Screen

class SearchScreen: Screen {

    @Composable
    override fun Content() {
        SearchContent()
    }
}

@Composable
fun SearchContent() {
    Box {
        Text("search", color = Color.Blue)
    }
}