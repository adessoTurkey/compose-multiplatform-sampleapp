package com.example.moveeapp_compose_kmm.permission.delegate

import com.example.moveeapp_compose_kmm.permission.PermissionDelegate
import com.example.moveeapp_compose_kmm.permission.PermissionStatus
import platform.UserNotifications.UNAuthorizationOptionAlert
import platform.UserNotifications.UNAuthorizationOptionBadge
import platform.UserNotifications.UNAuthorizationOptionSound
import platform.UserNotifications.UNAuthorizationStatus
import platform.UserNotifications.UNAuthorizationStatusAuthorized
import platform.UserNotifications.UNAuthorizationStatusDenied
import platform.UserNotifications.UNAuthorizationStatusEphemeral
import platform.UserNotifications.UNAuthorizationStatusNotDetermined
import platform.UserNotifications.UNAuthorizationStatusProvisional
import platform.UserNotifications.UNUserNotificationCenter
import kotlin.coroutines.suspendCoroutine

internal class RemoteNotificationPermissionDelegate : PermissionDelegate {

    override fun requestPermission(onPermissionResult: (PermissionStatus) -> Unit) {
        UNUserNotificationCenter.currentNotificationCenter()
            .requestAuthorizationWithOptions(
                UNAuthorizationOptionSound
                    .or(UNAuthorizationOptionAlert)
                    .or(UNAuthorizationOptionBadge)
            ) { isOk, error ->
                if (isOk && error == null) {
                    onPermissionResult(PermissionStatus.Granted)
                } else {
                    onPermissionResult(PermissionStatus.Denied(false))
                }
            }
    }

    override suspend fun getPermissionStatus(): PermissionStatus {
        val currentCenter = UNUserNotificationCenter.currentNotificationCenter()

        val status = suspendCoroutine { continuation ->
            currentCenter.getNotificationSettingsWithCompletionHandler { settings ->
                continuation.resumeWith(
                    Result.success(
                        settings?.authorizationStatus ?: UNAuthorizationStatusNotDetermined
                    )
                )
            }
        }
        return status.toCommonStatus()
    }
}

private fun UNAuthorizationStatus.toCommonStatus(): PermissionStatus {
    return when (this) {
        UNAuthorizationStatusAuthorized,
        UNAuthorizationStatusProvisional,
        UNAuthorizationStatusEphemeral -> PermissionStatus.Granted

        UNAuthorizationStatusNotDetermined -> PermissionStatus.NotDetermined
        UNAuthorizationStatusDenied -> PermissionStatus.Denied(true)
        else -> error("unknown push authorization status $this")
    }
}
