package com.example.moveeapp_compose_kmm.utils

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.asSkiaBitmap
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import kotlinx.cinterop.ByteVar
import kotlinx.cinterop.CValuesRef
import kotlinx.cinterop.ExperimentalForeignApi
import kotlinx.cinterop.refTo
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.skia.ColorAlphaType
import org.jetbrains.skia.ColorSpace
import org.jetbrains.skia.ColorType
import org.jetbrains.skia.ImageInfo
import platform.CoreGraphics.CGBitmapContextCreate
import platform.CoreGraphics.CGBitmapContextCreateImage
import platform.CoreGraphics.CGColorRenderingIntent
import platform.CoreGraphics.CGColorSpaceCreateDeviceRGB
import platform.CoreGraphics.CGColorSpaceRelease
import platform.CoreGraphics.CGContextDrawImage
import platform.CoreGraphics.CGContextRelease
import platform.CoreGraphics.CGDataProviderCreateWithData
import platform.CoreGraphics.CGDataProviderRelease
import platform.CoreGraphics.CGImageAlphaInfo
import platform.CoreGraphics.CGImageCreate
import platform.CoreGraphics.CGImageRelease
import platform.CoreGraphics.CGRectMake
import platform.CoreGraphics.kCGBitmapByteOrderDefault
import platform.UIKit.UIImage
import platform.UIKit.UIImageOrientation
import platform.UIKit.UIScreen
import platform.posix.free
import platform.posix.malloc

@Composable
fun DrawableResource.asUiImage(width: Dp = 24.dp, height: Dp = 24.dp): UIImage? =
    asBitmap(width, height).toUiImage()

@OptIn(ExperimentalForeignApi::class)
fun ImageBitmap.toUiImage(): UIImage? {
    val buffer = IntArray(width * height)
    readPixels(buffer)
    val pixels = this.asSkiaBitmap().readPixels(
        ImageInfo(width, height, ColorType.RGBA_8888, ColorAlphaType.PREMUL, ColorSpace.sRGB)
    ) ?: return null

    return createWithCGImage(pixels.refTo(0), width.toULong(), height.toULong())
}

/**
 * inspired from [gist.github.com](https://gist.github.com/PaulSolt/739132)
 */
@OptIn(ExperimentalForeignApi::class)
private fun createWithCGImage(buffer: CValuesRef<ByteVar>, width: ULong, height: ULong): UIImage? {
    val bufferLength = width * height * 4u
    val bitsPerComponent = 8uL
    val bitsPerPixel = 32uL
    val bytesPerRow = 4u * width

    val colorSpaceRef = CGColorSpaceCreateDeviceRGB() ?: return null
    val bitmapInfo =
        kCGBitmapByteOrderDefault or CGImageAlphaInfo.kCGImageAlphaPremultipliedLast.value
    val provider = CGDataProviderCreateWithData(null, buffer, bufferLength, null)

    val iref = CGImageCreate(
        width,
        height,
        bitsPerComponent,
        bitsPerPixel,
        bytesPerRow,
        colorSpaceRef,
        bitmapInfo,
        provider,
        null,
        true,
        CGColorRenderingIntent.kCGRenderingIntentDefault
    )

    val pixels = malloc(bufferLength) ?: return null

    val context = CGBitmapContextCreate(
        pixels,
        width,
        height,
        bitsPerComponent,
        bytesPerRow,
        colorSpaceRef,
        bitmapInfo
    ) ?: return null

    CGContextDrawImage(context, CGRectMake(0.0, 0.0, width.toDouble(), height.toDouble()), iref)

    val imageRef = CGBitmapContextCreateImage(context)

    val scale = UIScreen.mainScreen.scale
    val image = UIImage.imageWithCGImage(imageRef, scale, UIImageOrientation.UIImageOrientationUp)

    CGImageRelease(imageRef)
    CGContextRelease(context)
    CGColorSpaceRelease(colorSpaceRef)
    CGImageRelease(iref)
    CGDataProviderRelease(provider)
    free(pixels)

    return image
}
