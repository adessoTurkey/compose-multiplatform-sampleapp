package com.example.moveeapp_compose_kmm.ui.components


import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBars
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.ContentScale.Companion.Crop
import androidx.compose.ui.layout.ContentScale.Companion.FillWidth
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.dp
import com.example.moveeapp_compose_kmm.MR
import com.example.moveeapp_compose_kmm.core.getDominantColor
import com.example.moveeapp_compose_kmm.core.setStatusBarAppearanceByColorBehind
import com.example.moveeapp_compose_kmm.core.toComposeImageBitmap
import com.example.moveeapp_compose_kmm.core.toImage
import com.example.moveeapp_compose_kmm.utils.Constants
import com.seiko.imageloader.LocalImageLoader
import com.seiko.imageloader.asImageBitmap
import com.seiko.imageloader.model.ImageRequest
import com.seiko.imageloader.model.ImageResult
import com.seiko.imageloader.rememberAsyncImagePainter
import dev.icerock.moko.resources.compose.painterResource
import kotlin.math.roundToInt

@Composable
fun PosterImageItem(
    modifier: Modifier = Modifier,
    contentScale: ContentScale = Crop,
    imagePath: String?
) {
    Image(
        modifier = modifier.fillMaxSize(),
        painter = rememberAsyncImagePainter(
            Constants.IMAGE_BASE.plus(imagePath)
        ),
        contentDescription = null,
        contentScale = contentScale
    )
}

@Composable
fun DetailPosterImage(
    modifier: Modifier = Modifier,
    imagePath: String?
) {
    val url = Constants.IMAGE_BASE.plus(imagePath)
    val request = remember(url) { ImageRequest(url) }
    val imageLoader = LocalImageLoader.current
    var viewWidth by remember { mutableStateOf(0) }
    val statusBarHeight = WindowInsets.statusBars.getTop(LocalDensity.current)

    var dominantColor by remember { mutableStateOf<Color?>(null) }
    dominantColor?.setStatusBarAppearanceByColorBehind()

    Image(
        modifier = modifier.fillMaxSize().onGloballyPositioned {
            viewWidth = it.size.width
        },
        painter = rememberAsyncImagePainter(request, imageLoader),
        contentDescription = null,
        contentScale = FillWidth
    )

    LaunchedEffect(imagePath) {
        val imageBitmap = when (val imageResult = imageLoader.execute(request)) {
            is ImageResult.Bitmap -> imageResult.bitmap.asImageBitmap()

            is ImageResult.Image -> imageResult.toImage().toComposeImageBitmap()

            else -> null
        }

        dominantColor = imageBitmap?.getDominantColor(
            height = getLengthPxScaledByView(
                length = statusBarHeight,
                itemWidth = imageBitmap.width,
                viewWidth = viewWidth
            )
        )
    }
}

private fun getLengthPxScaledByView(
    length: Int,
    itemWidth: Int,
    viewWidth: Int,
): Int {
    return (length * (itemWidth.toFloat() / viewWidth)).roundToInt()
}

@Composable
fun CardImageItem(
    modifier: Modifier = Modifier,
    imagePath: String?,
    contentScale: ContentScale = Crop
) {

    Image(
        painter = if (imagePath.isNullOrEmpty()) {
            painterResource(MR.images.search_place_holder)
        } else {
            rememberAsyncImagePainter(
                Constants.IMAGE_BASE.plus(imagePath)
            )
        },
        contentDescription = null,
        modifier = modifier.size(width = 100.dp, height = 150.dp),
        contentScale = contentScale,
    )
}