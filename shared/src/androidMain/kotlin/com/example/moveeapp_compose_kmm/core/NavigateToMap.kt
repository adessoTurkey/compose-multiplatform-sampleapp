package com.example.moveeapp_compose_kmm.core

import android.content.Intent
import android.net.Uri
import androidx.compose.runtime.NoLiveLiterals
import com.example.moveeapp_compose_kmm.domain.location.DeviceLocation

@NoLiveLiterals
actual fun navigateToMap(
    context: PlatformContext,
    deviceLocation: DeviceLocation?,
    destinationName: String
) {
    val destinationUri = "google.navigation:q=${deviceLocation?.latitude},${deviceLocation?.longitude}"

    val intent = Intent(Intent.ACTION_VIEW, Uri.parse(destinationUri))

    if (intent.resolveActivity(context.androidContext.packageManager) != null) {
        context.androidContext.startActivity(intent)
    }
}
