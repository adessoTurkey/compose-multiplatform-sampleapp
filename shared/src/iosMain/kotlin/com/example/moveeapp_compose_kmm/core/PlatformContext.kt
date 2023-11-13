package com.example.moveeapp_compose_kmm.core

import androidx.compose.runtime.Composable

actual class PlatformContext

@Composable
actual fun getPlatformContext(): PlatformContext = PlatformContext()