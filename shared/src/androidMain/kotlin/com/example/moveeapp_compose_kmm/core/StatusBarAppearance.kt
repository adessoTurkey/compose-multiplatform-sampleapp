package com.example.moveeapp_compose_kmm.core

import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect

@Composable
actual fun StatusBarAppearance(isBackgroundLight: Boolean) {
    val insetsController = findWindow()?.getInsetsControllerCompat()

    DisposableEffect(isBackgroundLight) {
        insetsController?.isAppearanceLightStatusBars = isBackgroundLight

        onDispose {
            insetsController?.isAppearanceLightStatusBars = false
        }
    }
}