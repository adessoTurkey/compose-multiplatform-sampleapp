package com.example.moveeapp_compose_kmm.permission

internal interface PermissionDelegate {
    fun requestPermission(onPermissionResult: (PermissionStatus) -> Unit)
    suspend fun getPermissionStatus(): PermissionStatus
}
