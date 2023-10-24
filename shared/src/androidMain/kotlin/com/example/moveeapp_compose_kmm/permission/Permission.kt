package com.example.moveeapp_compose_kmm.permission

import android.Manifest
import android.os.Build

internal fun Permission.toPlatformPermission(): List<String> {
    return when (this) {
        Permission.CAMERA -> listOf(Manifest.permission.CAMERA)
        Permission.GALLERY -> galleryCompat()
        Permission.STORAGE -> allStoragePermissions()
        Permission.WRITE_STORAGE -> listOf(Manifest.permission.WRITE_EXTERNAL_STORAGE)
        Permission.LOCATION -> fineLocationCompat()
        Permission.COARSE_LOCATION -> listOf(Manifest.permission.ACCESS_COARSE_LOCATION)
        Permission.REMOTE_NOTIFICATION -> {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                listOf(Manifest.permission.POST_NOTIFICATIONS)
            } else {
                emptyList()
            }
        }

        Permission.RECORD_AUDIO -> listOf(Manifest.permission.RECORD_AUDIO)
        Permission.BLUETOOTH_LE -> allBluetoothPermissions()
        Permission.BLUETOOTH_SCAN -> bluetoothScanCompat()
        Permission.BLUETOOTH_ADVERTISE -> bluetoothAdvertiseCompat()
        Permission.BLUETOOTH_CONNECT -> bluetoothConnectCompat()
    }
}

/**
 * Behavior changes: Apps targeting Android 13 or higher
 *
 * @see https://developer.android.com/about/versions/13/behavior-changes-13#granular-media-permissions
 */

private fun allStoragePermissions() =
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
        listOf(
            Manifest.permission.READ_MEDIA_AUDIO,
            Manifest.permission.READ_MEDIA_IMAGES,
            Manifest.permission.READ_MEDIA_VIDEO
        )
    } else {
        listOf(Manifest.permission.READ_EXTERNAL_STORAGE)
    }

private fun galleryCompat() =
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
        listOf(
            Manifest.permission.READ_MEDIA_IMAGES,
            Manifest.permission.READ_MEDIA_VIDEO
        )
    } else {
        listOf(Manifest.permission.READ_EXTERNAL_STORAGE)
    }

private fun fineLocationCompat() =
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
        listOf(
            Manifest.permission.ACCESS_FINE_LOCATION,
            Manifest.permission.ACCESS_COARSE_LOCATION,
        )
    } else {
        listOf(Manifest.permission.ACCESS_FINE_LOCATION)
    }

/**
 * Bluetooth permissions
 *
 * @see https://developer.android.com/guide/topics/connectivity/bluetooth/permissions
 */

private fun allBluetoothPermissions() =
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
        listOf(
            Manifest.permission.BLUETOOTH_CONNECT,
            Manifest.permission.BLUETOOTH_SCAN,
            Manifest.permission.BLUETOOTH_ADVERTISE,
            Manifest.permission.ACCESS_COARSE_LOCATION
        )
    } else {
        listOf(
            Manifest.permission.BLUETOOTH,
            Manifest.permission.ACCESS_COARSE_LOCATION
        )
    }

private fun bluetoothScanCompat() =
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
        listOf(Manifest.permission.BLUETOOTH_SCAN)
    } else {
        listOf(Manifest.permission.BLUETOOTH)
    }

private fun bluetoothAdvertiseCompat() =
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
        listOf(Manifest.permission.BLUETOOTH_ADVERTISE)
    } else {
        listOf(Manifest.permission.BLUETOOTH)
    }

private fun bluetoothConnectCompat() =
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
        listOf(Manifest.permission.BLUETOOTH_CONNECT)
    } else {
        listOf(Manifest.permission.BLUETOOTH)
    }

