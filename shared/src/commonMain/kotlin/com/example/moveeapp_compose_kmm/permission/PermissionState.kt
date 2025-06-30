package com.example.moveeapp_compose_kmm.permission

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalInspectionMode

@Composable
fun rememberPermissionState(
    permission: Permission,
    onPermissionResult: (Boolean) -> Unit = {}
): PermissionState {
    return if (LocalInspectionMode.current) {
        remember { FakePermissionState(permission) }
    } else {
        rememberMutablePermissionState(permission, onPermissionResult)
    }
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

/**
 * A fake implementation of [PermissionState] that can be used in previews and tests.
 * This class allows simulating different permission states by providing a custom [status].
 * The [launchPermissionRequest] and [openSettings] methods are no-ops.
 */
class FakePermissionState(
    override val permission: Permission,
    override val status: PermissionStatus = PermissionStatus.Granted
) : PermissionState {

    override fun launchPermissionRequest() {
        // no-op
    }

    override fun openSettings() {
        // no-op
    }
}
