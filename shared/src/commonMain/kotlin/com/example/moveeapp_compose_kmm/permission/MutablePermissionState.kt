package com.example.moveeapp_compose_kmm.permission

import androidx.compose.runtime.Composable

@Composable
internal expect fun rememberMutablePermissionState(
    permission: Permission,
    onPermissionResult: (Boolean) -> Unit
): PermissionState
