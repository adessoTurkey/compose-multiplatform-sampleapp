package com.example.moveeapp_compose_kmm.ui.scene.detailscreen

import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import moe.tlaster.precompose.navigation.Navigator

@Composable
fun DetailScreen(navigator: Navigator, onClick: () -> Unit) {

    Button(onClick = {onClick()} ){
        Text("DetailToHome")
    }
}