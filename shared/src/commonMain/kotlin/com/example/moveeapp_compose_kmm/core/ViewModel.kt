package com.example.moveeapp_compose_kmm.core

import androidx.compose.runtime.Composable
import cafe.adriel.voyager.core.model.ScreenModel
import cafe.adriel.voyager.core.model.rememberScreenModel
import cafe.adriel.voyager.core.model.screenModelScope
import cafe.adriel.voyager.core.screen.Screen
import kotlinx.coroutines.CoroutineScope
import org.koin.compose.LocalKoinScope

typealias ViewModel = ScreenModel

@Composable
inline fun <reified T : ViewModel> Screen.viewModel(): T {
    val koinScope = LocalKoinScope.current
    return rememberScreenModel {
        koinScope.get()
    }
}

val ViewModel.viewModelScope: CoroutineScope get() = screenModelScope
