package com.example.moveeapp_compose_kmm.core

import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember

@Composable
actual fun StatusBarAppearance(isBackgroundLight: Boolean) {
    val insetsController = findWindow()?.getInsetsControllerCompat()
    val isDefaultAppearanceLight by remember {
        mutableStateOf(insetsController?.isAppearanceLightStatusBars)
    }

    DisposableEffect(isBackgroundLight) {
        insetsController?.isAppearanceLightStatusBars = isBackgroundLight

        onDispose {
            insetsController?.isAppearanceLightStatusBars = isDefaultAppearanceLight ?: false
        }
    }
}