package com.example.moveeapp_compose_kmm.permission

import android.app.Activity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts.RequestMultiplePermissions
import androidx.activity.result.contract.ActivityResultContracts.RequestPermission
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext

@Composable
internal actual fun rememberMutablePermissionState(
    permission: Permission,
    onPermissionResult: (Boolean) -> Unit
): PermissionState {
    val context = LocalContext.current

    val permissionState = remember(permission) {
        MutablePermissionState(permission, context.findActivity())
    }

    PermissionLifecycleCheckerEffect(permissionState)

    val launcher = createLauncher(permission) {
        permissionState.refreshPermissionStatus()
        onPermissionResult(it)
    }

    DisposableEffect(permissionState, launcher) {
        permissionState.launcher = launcher
        onDispose {
            permissionState.launcher = null
        }
    }

    return permissionState
}

@Composable
private fun createLauncher(
    permission: Permission,
    onResult: (Boolean) -> Unit
): PermissionLauncher {
    return if (permission.toPlatformPermission().isEmpty())
        PermissionLauncher(permission, null, null)
    else if (permission.toPlatformPermission().size == 1) {
        PermissionLauncher(
            permission,
            rememberLauncherForActivityResult(RequestPermission()) { onResult(it) }
        )
    } else {
        PermissionLauncher(
            permission,
            rememberLauncherForActivityResult(RequestMultiplePermissions()) { result ->
                onResult(result.values.all { it })
            })
    }
}

internal class PermissionLauncher(
    private val permission: Permission,
    private val launcher: ActivityResultLauncher<String>? = null,
    private val launcher2: ActivityResultLauncher<Array<String>>? = null,
) {

    constructor(permission: Permission, launcher2: ActivityResultLauncher<Array<String>>)
            : this(permission, null, launcher2)

    fun launch() {
        val permissions = permission.toPlatformPermission()

        launcher?.launch(permissions.first())
        launcher2?.launch(permissions.toTypedArray())
    }
}

internal class MutablePermissionState(
    override val permission: Permission,
    private val activity: Activity
) : PermissionState {

    override var status: PermissionStatus by mutableStateOf(getPermissionStatus())

    internal var launcher: PermissionLauncher? = null

    override fun launchPermissionRequest() {
        launcher?.launch()
    }

    internal fun refreshPermissionStatus() {
        status = getPermissionStatus()
    }

    private fun getPermissionStatus(): PermissionStatus {
        val platformPermissions = permission.toPlatformPermission()
        val hasPermission = platformPermissions.all { activity.checkPermission(it) }
        return if (hasPermission) {
            PermissionStatus.Granted
        } else {
            PermissionStatus.Denied(platformPermissions.any { activity.shouldShowRationale(it) })
        }
    }
}
