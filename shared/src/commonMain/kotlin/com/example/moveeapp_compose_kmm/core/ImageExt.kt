package com.example.moveeapp_compose_kmm.core

import androidx.compose.ui.graphics.ImageBitmap
import com.seiko.imageloader.Image

expect fun Image.toComposeImageBitmap(): ImageBitmap