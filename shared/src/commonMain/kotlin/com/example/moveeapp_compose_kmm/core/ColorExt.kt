package com.example.moveeapp_compose_kmm.core

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import com.example.moveeapp_compose_kmm.ui.theme.isDark

@Composable
fun Color.setStatusBarAppearanceByColorBehind() {
    StatusBarAppearance(isBackgroundLight = !isDark)
}