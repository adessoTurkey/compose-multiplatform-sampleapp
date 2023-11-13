package com.example.moveeapp_compose_kmm.core

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext

actual class PlatformContext(val androidContext: Context)

@Composable
actual fun getPlatformContext(): PlatformContext {
    val context = LocalContext.current
    return remember(context) {
        PlatformContext(context)
    }
}

