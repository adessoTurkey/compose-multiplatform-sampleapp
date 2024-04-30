package com.example.moveeapp_compose_kmm.utils

import android.graphics.Bitmap
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.asAndroidBitmap
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import org.jetbrains.compose.resources.DrawableResource

@Composable
fun DrawableResource.asAndroidBitmap(width: Dp = 24.dp, height: Dp = 24.dp): Bitmap =
    asBitmap(width, height).asAndroidBitmap()
