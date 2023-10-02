package com.example.moveeapp_compose_kmm.core

import androidx.compose.runtime.Composable

@Composable
actual fun BackHandler(isEnabled: Boolean, onBack: () -> Unit) {
    androidx.activity.compose.BackHandler(isEnabled, onBack)
}