package com.example.moveeapp_compose_kmm.utils

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Canvas
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.drawscope.CanvasDrawScope
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.painterResource

/**
 * Converts any compose drawable to [ImageBitmap].
 *
 * Currently it draws into a canvas to create the bitmap but can be optimized along with coming changes to compose resources library.
 */
@Composable
fun DrawableResource.asBitmap(width: Dp = 24.dp, height: Dp = 24.dp): ImageBitmap {
    val density = LocalDensity.current
    val layoutDirection = LocalLayoutDirection.current
    val painter = painterResource(this)

    return remember(density, layoutDirection, painter) {
        val size = if (painter.intrinsicSize.isEmpty()) {
            with(density) {
                Size(width.toPx(), height.toPx())
            }
        } else {
            painter.intrinsicSize
        }

        val bitmap = ImageBitmap(size.width.toInt(), size.height.toInt())
        CanvasDrawScope().draw(density, layoutDirection, Canvas(bitmap), size) {
            with(painter) {
                draw(this@draw.size)
            }
        }
        bitmap
    }
}
