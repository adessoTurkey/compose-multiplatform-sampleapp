package com.example.moveeapp_compose_kmm.core

import com.example.moveeapp_compose_kmm.domain.location.DeviceLocation


expect fun navigateToMap(context: PlatformContext, deviceLocation: DeviceLocation?, destinationName: String = "")