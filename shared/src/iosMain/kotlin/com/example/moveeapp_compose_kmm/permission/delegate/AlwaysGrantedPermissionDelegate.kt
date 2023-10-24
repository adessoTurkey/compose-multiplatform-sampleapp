package com.example.moveeapp_compose_kmm.permission.delegate

import com.example.moveeapp_compose_kmm.permission.PermissionDelegate
import com.example.moveeapp_compose_kmm.permission.PermissionStatus

internal class AlwaysGrantedPermissionDelegate : PermissionDelegate {

    override fun requestPermission(onPermissionResult: (PermissionStatus) -> Unit) {
        onPermissionResult(PermissionStatus.Granted)
    }

    override suspend fun getPermissionStatus(): PermissionStatus = PermissionStatus.Granted
}
