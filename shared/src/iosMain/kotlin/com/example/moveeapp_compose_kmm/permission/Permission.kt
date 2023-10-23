package com.example.moveeapp_compose_kmm.permission

import com.example.moveeapp_compose_kmm.permission.delegate.AVCapturePermissionDelegate
import com.example.moveeapp_compose_kmm.permission.delegate.AlwaysGrantedPermissionDelegate
import com.example.moveeapp_compose_kmm.permission.delegate.BluetoothPermissionDelegate
import com.example.moveeapp_compose_kmm.permission.delegate.GalleryPermissionDelegate
import com.example.moveeapp_compose_kmm.permission.delegate.LocationManagerDelegate
import com.example.moveeapp_compose_kmm.permission.delegate.LocationPermissionDelegate
import com.example.moveeapp_compose_kmm.permission.delegate.RemoteNotificationPermissionDelegate
import platform.AVFoundation.AVMediaTypeAudio
import platform.AVFoundation.AVMediaTypeVideo

internal fun Permission.getDelegate(): PermissionDelegate {
    return when (this) {
        Permission.LOCATION,
        Permission.COARSE_LOCATION -> LocationPermissionDelegate(LocationManagerDelegate())

        Permission.RECORD_AUDIO -> AVCapturePermissionDelegate(AVMediaTypeAudio)
        Permission.CAMERA -> AVCapturePermissionDelegate(AVMediaTypeVideo)
        Permission.GALLERY -> GalleryPermissionDelegate()

        Permission.STORAGE, Permission.WRITE_STORAGE -> AlwaysGrantedPermissionDelegate()

        Permission.REMOTE_NOTIFICATION -> RemoteNotificationPermissionDelegate()

        Permission.BLUETOOTH_LE,
        Permission.BLUETOOTH_SCAN,
        Permission.BLUETOOTH_ADVERTISE,
        Permission.BLUETOOTH_CONNECT -> BluetoothPermissionDelegate()
    }
}
