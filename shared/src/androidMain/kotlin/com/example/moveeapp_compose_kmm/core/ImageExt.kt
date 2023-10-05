package com.example.moveeapp_compose_kmm.core

import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.asImageBitmap
import androidx.core.graphics.drawable.toBitmap
import com.seiko.imageloader.Image

actual fun Image.toComposeImageBitmap(): ImageBitmap {
    return drawable.toBitmap().asImageBitmap()
}