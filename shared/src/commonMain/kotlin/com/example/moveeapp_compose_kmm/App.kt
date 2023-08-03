package com.example.moveeapp_compose_kmm

import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import com.example.moveeapp_compose_kmm.navigation.Navigation
import moe.tlaster.precompose.navigation.rememberNavigator

@Composable
internal fun App() {
    val navigator = rememberNavigator()

    MaterialTheme {
        Scaffold {
            Navigation(navigator)
        }
    }
}