package com.example.moveeapp_compose_kmm.permission

import androidx.compose.runtime.Composable

@Composable
actual fun rememberMutablePermissionState(
    permission: Permission,
    onPermissionResult: (Boolean) -> Unit
): PermissionState = object : PermissionState {
    override val permission: Permission
        get() = permission

    override val status: PermissionStatus
        get() = PermissionStatus.Granted

    override fun launchPermissionRequest() {
    }

    override fun openSettings() {
    }
}
