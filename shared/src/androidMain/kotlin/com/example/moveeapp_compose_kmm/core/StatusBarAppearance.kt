package com.example.moveeapp_compose_kmm.core

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect

@Composable
actual fun StatusBarAppearance(isBackgroundLight: Boolean) {
    val insetsController = findWindow()?.getInsetsControllerCompat()

    LaunchedEffect(isBackgroundLight) {
        insetsController?.isAppearanceLightStatusBars = isBackgroundLight
    }
}