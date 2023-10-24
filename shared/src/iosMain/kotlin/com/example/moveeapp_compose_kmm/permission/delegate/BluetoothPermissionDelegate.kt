package com.example.moveeapp_compose_kmm.permission.delegate

import com.example.moveeapp_compose_kmm.permission.PermissionDelegate
import com.example.moveeapp_compose_kmm.permission.PermissionStatus
import platform.CoreBluetooth.CBCentralManager
import platform.CoreBluetooth.CBCentralManagerDelegateProtocol
import platform.CoreBluetooth.CBManagerState
import platform.CoreBluetooth.CBManagerStatePoweredOff
import platform.CoreBluetooth.CBManagerStatePoweredOn
import platform.CoreBluetooth.CBManagerStateResetting
import platform.CoreBluetooth.CBManagerStateUnauthorized
import platform.CoreBluetooth.CBManagerStateUnknown
import platform.CoreBluetooth.CBManagerStateUnsupported
import platform.darwin.NSObject

internal class BluetoothPermissionDelegate : PermissionDelegate {

    override fun requestPermission(onPermissionResult: (PermissionStatus) -> Unit) {
        if (CBCentralManager().state == CBManagerStateUnknown) {
            CBCentralManager(object : NSObject(), CBCentralManagerDelegateProtocol {
                override fun centralManagerDidUpdateState(central: CBCentralManager) {
                    onPermissionResult(central.state.toCommonStatus())
                }
            }, null)
        } else {
            onPermissionResult(CBCentralManager().state.toCommonStatus())
        }
    }

    override suspend fun getPermissionStatus(): PermissionStatus {
        return CBCentralManager().state.toCommonStatus()
    }
}

private fun CBManagerState.toCommonStatus(): PermissionStatus {
    return when (this) {
        CBManagerStatePoweredOn -> PermissionStatus.Granted
        CBManagerStateUnauthorized -> PermissionStatus.Denied(true)

        CBManagerStatePoweredOff,
        CBManagerStateResetting,
        CBManagerStateUnsupported -> PermissionStatus.Denied(false)

        CBManagerStateUnknown -> PermissionStatus.NotDetermined
        else -> error("unknown state $this")
    }
}
