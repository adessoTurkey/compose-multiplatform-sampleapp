package com.example.moveeapp_compose_kmm.ui.scene.detailscreen

import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.Navigator
import cafe.adriel.voyager.navigator.currentOrThrow
import com.example.moveeapp_compose_kmm.ui.scene.moviescreen.MovieScreen

class DetailScreen() : Screen {
    @Composable
    override fun Content() {
        val navigator = LocalNavigator.currentOrThrow
        DetailContent(navigator = navigator)
    }
}

@Composable
fun DetailContent(navigator: Navigator) {
    Button(onClick = { navigator.push(MovieScreen()) }){
        Text(modifier = Modifier.padding(30.dp), text = "DetailToHome")
    }
}
