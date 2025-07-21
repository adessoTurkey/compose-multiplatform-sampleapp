package com.example.moveeapp_compose_kmm.core

import android.content.Intent
import androidx.core.net.toUri
import com.example.moveeapp_compose_kmm.utils.Constants

actual fun share(
    context: PlatformContext,
    title: String,
    description: String?,
    imageUrl: String?
) {
    val text = title + description?.let { "\n$it" }

    val sendIntent: Intent = Intent().apply {
        action = Intent.ACTION_SEND
        putExtra(Intent.EXTRA_TEXT, text)
        if (imageUrl != null) {
            putExtra(Intent.EXTRA_STREAM, (Constants.IMAGE_BASE + imageUrl).toUri())
            addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
            type = "image/*"
        } else {
            type = "text/*"
        }
    }
    val shareIntent = Intent.createChooser(sendIntent, null)

    context.androidContext.startActivity(shareIntent)
}
