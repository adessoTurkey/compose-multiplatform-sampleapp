package com.example.moveeapp_compose_kmm.ui.scene

import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import com.example.moveeapp_compose_kmm.navigation.Route
import moe.tlaster.precompose.navigation.Navigator

@Composable
fun HomeScreen(navigator: Navigator, onClick: () -> Unit) {

    Button(onClick = {onClick()} ){
        navigator
        Text("HomeToDetail")

    }
}
