package com.example.moveeapp_compose_kmm.ui.components


import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.ContentScale.Companion.Crop
import androidx.compose.ui.unit.dp
import com.example.moveeapp_compose_kmm.MR
import com.example.moveeapp_compose_kmm.utils.Constants
import com.seiko.imageloader.rememberAsyncImagePainter
import dev.icerock.moko.resources.compose.painterResource

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