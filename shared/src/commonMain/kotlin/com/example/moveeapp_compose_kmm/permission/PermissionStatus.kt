package com.example.moveeapp_compose_kmm.permission

sealed interface PermissionStatus {
    data object Granted : PermissionStatus

    data object NotDetermined : PermissionStatus

    data class Denied(
        val shouldShowRationale: Boolean
    ) : PermissionStatus
}

val PermissionStatus.isGranted: Boolean
    get() = this == PermissionStatus.Granted

/**
 * `true` if a rationale should be presented to the user.
 */
val PermissionStatus.shouldShowRationale: Boolean
    get() = when (this) {
        PermissionStatus.Granted -> false
        is PermissionStatus.Denied -> shouldShowRationale
        PermissionStatus.NotDetermined -> false
    }
