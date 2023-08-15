package com.example.moveeapp_compose_kmm.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import com.example.moveeapp_compose_kmm.data.remote.model.PopularMovieModel
import com.example.moveeapp_compose_kmm.utils.Constants
import com.seiko.imageloader.rememberAsyncImagePainter

@Composable
internal fun MovieList(listItems: List<PopularMovieModel.PopularMovies>, onclick: () -> Unit) {
    LazyVerticalGrid(columns = GridCells.Fixed(2),
        modifier = Modifier.padding(start = 5.dp, end = 5.dp, top = 10.dp),
        content = {
            items(listItems) {
                Column(
                    modifier = Modifier.padding(
                        start = 5.dp, end = 5.dp, top = 0.dp, bottom = 10.dp
                    )
                ) {
                    ImageItem(imagePath = it.poster_path, onClick = onclick)
                }
            }
        })
}