package com.example.moveeapp_compose_kmm.core

import android.content.Intent

actual fun share(context: PlatformContext, text: String) {

    val sendIntent: Intent = Intent().apply {
        action = Intent.ACTION_SEND
        putExtra(Intent.EXTRA_TEXT, text)
        type = "text/plain"
    }
    val shareIntent = Intent.createChooser(sendIntent, null)

    context.androidContext.startActivity(shareIntent)
}