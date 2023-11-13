package com.example.moveeapp_compose_kmm.core

import androidx.compose.runtime.Composable

expect class PlatformContext

@Composable
expect fun getPlatformContext(): PlatformContext