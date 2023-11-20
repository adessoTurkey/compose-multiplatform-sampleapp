package com.example.moveeapp_compose_kmm.core

import androidx.compose.runtime.Composable

@Composable
expect fun getTextOrNull(key: String, vararg args: Any): String?