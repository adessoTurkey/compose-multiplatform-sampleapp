package com.example.moveeapp_compose_kmm.core

import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import com.example.moveeapp_compose_kmm.domain.location.DeviceLocation

@Composable
actual fun NavigateToMap(deviceLocation: DeviceLocation) {
    val context = LocalContext.current
    val destinationUri = "google.navigation:q=${deviceLocation.latitude},${deviceLocation.longitude}"

    val intent = Intent(Intent.ACTION_VIEW, Uri.parse(destinationUri))

    if (intent.resolveActivity(context.packageManager) != null) {
        context.startActivity(intent)
    } else {
        // Örnek: Toast.makeText(this, "Google Haritalar uygulaması yüklü değil.", Toast.LENGTH_SHORT).show()
    }
}
