package com.example.moveeapp_compose_kmm.permission

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable

@Composable
fun rememberPermissionState(
    permission: Permission,
    onPermissionResult: (Boolean) -> Unit = {}
): PermissionState {
    return rememberMutablePermissionState(permission, onPermissionResult)
}

@Stable
interface PermissionState {

    /**
     * The permission to control and observe.
     */
    public val permission: Permission

    /**
     * [permission]'s status
     */
    public val status: PermissionStatus

    /**
     * Request the [permission] to the user.
     *
     * This should always be triggered from non-composable scope, for example, from a side-effect
     * or a non-composable callback. Otherwise, this will result in an IllegalStateException.
     *
     * This triggers a system dialog that asks the user to grant or revoke the permission.
     * Note that this dialog might not appear on the screen if the user doesn't want to be asked
     * again or has denied the permission multiple times.
     * This behavior varies depending on the System level API.
     */
    public fun launchPermissionRequest(): Unit

    /**
     * Opens the system settings screen for the application.
     */
    fun openSettings()
}

