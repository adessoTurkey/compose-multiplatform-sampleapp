package com.example.moveeapp_compose_kmm.permission

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

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

    private fun getPermissionStatus() {
        scope.launch {
            status = delegate.getPermissionStatus()
        }
    }
}

