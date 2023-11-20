package com.example.moveeapp_compose_kmm.core

import androidx.compose.runtime.Composable
import cafe.adriel.voyager.core.model.ScreenModel
import cafe.adriel.voyager.core.model.rememberScreenModel
import cafe.adriel.voyager.core.model.screenModelScope
import cafe.adriel.voyager.core.screen.Screen
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.MutableStateFlow
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

private class Tags {
    companion object {
        private val weakMap: WeakMap<String, Any> = WeakMap()

        @Suppress("UNCHECKED_CAST")
        fun <T : Any> setTagIfAbsent(key: String, newValue: T): T {
            weakMap.get(key)?.let { return it as T }

            weakMap.put(key, newValue)

            return weakMap.get(key) as T
        }
    }
}

val ViewModel.errorMessage: MutableStateFlow<String?>
    get() {
        return Tags.setTagIfAbsent(
            key = this::class.simpleName!! + hashCode().toString(),
            newValue = MutableStateFlow(null)
        )
    }

fun ViewModel.setErrorMessage(message: String?) {
    errorMessage.tryEmit(message)
}