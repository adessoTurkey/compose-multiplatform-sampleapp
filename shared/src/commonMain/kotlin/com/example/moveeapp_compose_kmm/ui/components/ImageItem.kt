package com.example.moveeapp_compose_kmm.ui.components


import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import com.example.moveeapp_compose_kmm.utils.Constants
import com.seiko.imageloader.rememberAsyncImagePainter

@Composable
fun ImageItem(
    modifier: Modifier = Modifier,
    imagePath: String?,
    onClick: () -> Unit
) {

    Image(
        painter = rememberAsyncImagePainter(
            Constants.IMAGE_BASE.plus(imagePath)
        ),
        contentDescription = null,
        modifier = Modifier.size(250.dp).clickable {
            onClick()
        },
        contentScale = ContentScale.Crop
    )
}