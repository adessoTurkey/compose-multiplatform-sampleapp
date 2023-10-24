package com.example.moveeapp_compose_kmm.permission

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import platform.Foundation.NSURL
import platform.UIKit.UIApplication
import platform.UIKit.UIApplicationOpenSettingsURLString

@Composable
internal actual fun rememberMutablePermissionState(
    permission: Permission,
    onPermissionResult: (Boolean) -> Unit
): PermissionState {

    val scope = rememberCoroutineScope()
    return remember(permission) {
        MutablePermissionState(permission, scope)
    }
}

internal class MutablePermissionState(
    override val permission: Permission,
    private val scope: CoroutineScope,
) : PermissionState {

    override var status: PermissionStatus by mutableStateOf(PermissionStatus.Denied(false))
        private set

    private val delegate: PermissionDelegate = permission.getDelegate()

    init {
        getPermissionStatus()
    }

    override fun launchPermissionRequest() {
        delegate.requestPermission {
            status = it
        }
    }

    override fun openSettings() {
        val settingsUrl: NSURL = NSURL.URLWithString(UIApplicationOpenSettingsURLString)!!
        UIApplication.sharedApplication.openURL(settingsUrl)
    }

    private fun getPermissionStatus() {
        scope.launch {
            status = delegate.getPermissionStatus()
        }
    }
}

