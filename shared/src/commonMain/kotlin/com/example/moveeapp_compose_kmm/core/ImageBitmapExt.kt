package com.example.moveeapp_compose_kmm.core

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.withContext

suspend fun ImageBitmap.getDominantColor(height: Int): Color {
    return withContext(Dispatchers.IO) {
        val pixels = IntArray(width * height)
        readPixels(pixels, width = width, height = height)

        val colorCountMap = mutableMapOf<Int, Int>()

        for (color in pixels) {
            if (colorCountMap.containsKey(color)) {
                colorCountMap[color] = colorCountMap[color]!! + 1
            } else {
                colorCountMap[color] = 1
            }
        }

        val dominantColor = colorCountMap.maxByOrNull { it.value }?.key
        return@withContext dominantColor?.let { Color(it) } ?: Color.Black
    }
}
