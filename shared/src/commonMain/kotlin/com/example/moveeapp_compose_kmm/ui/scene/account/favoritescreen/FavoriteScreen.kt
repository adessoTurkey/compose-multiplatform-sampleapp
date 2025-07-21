package com.example.moveeapp_compose_kmm.ui.scene.account.favoritescreen

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.moveeapp_compose_kmm.domain.MediaType
import com.example.moveeapp_compose_kmm.domain.favorite.FavoriteMovie
import com.example.moveeapp_compose_kmm.domain.favorite.FavoriteTv
import com.example.moveeapp_compose_kmm.ui.components.BackPressedItem
import com.example.moveeapp_compose_kmm.ui.components.CardImageItem
import com.example.moveeapp_compose_kmm.ui.components.DateItem
import com.example.moveeapp_compose_kmm.ui.components.RateItem
import com.example.moveeapp_compose_kmm.ui.components.TextItem
import com.example.moveeapp_compose_kmm.ui.scene.account.FavoriteMovieUiState
import com.example.moveeapp_compose_kmm.ui.scene.account.FavoriteTvUiState
import com.example.moveeapp_compose_kmm.ui.theme.AppTheme
import com.example.moveeapp_compose_kmm.ui.theme.Fonts
import movee.shared.generated.resources.Res
import movee.shared.generated.resources.fav_movie
import movee.shared.generated.resources.fav_tv
import org.jetbrains.compose.resources.StringResource
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun FavoriteScreen(
    viewModel: FavoriteViewModel,
    mediaType: MediaType,
    navigateToMovie: (Int) -> Unit,
    navigateToTv: (Int) -> Unit,
    navigateBack: () -> Unit,
) {
    val favoriteMovieUiState by viewModel.favoriteMovieUiState.collectAsState()
    val favoriteTvUiState by viewModel.favoriteTvUiState.collectAsState()

    when (mediaType) {
        MediaType.MOVIE -> {
            viewModel.getPopularMovie()

            FavoriteScreen(Res.string.fav_movie, navigateBack) {
                FavoriteMovieContent(favoriteMovieUiState, navigateToMovie)
            }
        }

        MediaType.TV -> {
            viewModel.getPopularTv()

            FavoriteScreen(Res.string.fav_tv, navigateBack) {
                FavoriteTvContent(favoriteTvUiState, navigateToTv)
            }
        }

        else -> Unit
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun FavoriteScreen(
    title: StringResource,
    navigateBack: () -> Unit,
    content: @Composable BoxScope.() -> Unit,
) {
    Scaffold(topBar = {
        TopAppBar(
            title = {
                TextItem(
                    text = stringResource(title),
                    fontSize = 20.sp,
                    fontFamily = Fonts.bold,
                    textColor = MaterialTheme.colorScheme.primaryContainer
                )
            },
            colors = TopAppBarDefaults.topAppBarColors(MaterialTheme.colorScheme.primary),
            navigationIcon = {
                BackPressedItem(onBackPressed = navigateBack)
            }
        )
    }) { contentPadding ->
        Box(modifier = Modifier.padding(top = contentPadding.calculateTopPadding())) {
            Spacer(
                modifier = Modifier.height(190.dp).fillMaxWidth()
                    .align(Alignment.TopCenter)
                    .background(MaterialTheme.colorScheme.primary)
            )
            content()
        }
    }
}

@Composable
private fun FavoriteMovieContent(
    favoriteMovieUiState: FavoriteMovieUiState,
    onMovieDetailClick: (Int) -> Unit,
) {
    LazyColumn(modifier = Modifier.padding(horizontal = 16.dp)) {
        items(favoriteMovieUiState.favoriteMovieData) {
            FavoriteMovieRow(favoriteMovie = it) { movieId ->
                onMovieDetailClick(movieId)
            }
        }
    }
}

@Composable
private fun FavoriteTvContent(
    favoriteTvUiState: FavoriteTvUiState,
    onTvDetailClick: (Int) -> Unit,
) {
    LazyColumn(modifier = Modifier.padding(horizontal = 16.dp)) {
        items(favoriteTvUiState.favoriteTvData) {
            FavoriteTvRow(favoriteTv = it) { tvId ->
                onTvDetailClick(tvId)
            }
        }
    }
}

@Composable
private fun FavoriteMovieRow(
    favoriteMovie: FavoriteMovie,
    onDetailClick: (Int) -> Unit,
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(150.dp)
            .padding(horizontal = 16.dp, vertical = 8.dp)
            .clickable { onDetailClick.invoke(favoriteMovie.movieId) },
        shape = MaterialTheme.shapes.small,
    ) {
        Row {
            CardImageItem(imagePath = favoriteMovie.posterPath)
            Column(
                modifier = Modifier
                    .fillMaxSize(),
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                TextItem(
                    text = favoriteMovie.title,
                    modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp),
                    fontWeight = FontWeight.Bold
                )
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp, vertical = 8.dp),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    DateItem(date = favoriteMovie.releaseDate)
                    RateItem(rate = favoriteMovie.voteAverage.toString())
                }
            }
        }
    }
}

@Composable
private fun FavoriteTvRow(
    favoriteTv: FavoriteTv,
    onDetailClick: (Int) -> Unit,
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(150.dp)
            .padding(horizontal = 16.dp, vertical = 8.dp)
            .clickable { onDetailClick.invoke(favoriteTv.tvId) },
        shape = MaterialTheme.shapes.small,
    ) {
        Row {
            CardImageItem(imagePath = favoriteTv.posterPath)
            Column(
                modifier = Modifier
                    .fillMaxSize(),
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                TextItem(
                    text = favoriteTv.title,
                    modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp),
                    fontWeight = FontWeight.Bold
                )
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp, vertical = 8.dp),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    RateItem(rate = favoriteTv.voteAverage.toString())
                }
            }
        }
    }
}

@Preview
@Composable
private fun FavoriteMoviePreview() {
    AppTheme {
        FavoriteScreen(Res.string.fav_movie, {}) {
            FavoriteMovieContent(
                FavoriteMovieUiState(
                    favoriteMovieData = listOf(
                        FavoriteMovie(
                            movieId = 1,
                            title = "Final Destination",
                            posterPath = "",
                            releaseDate = "2025-05-14",
                            voteAverage = 8.0
                        ),
                        FavoriteMovie(
                            movieId = 2,
                            title = "Final Destination",
                            posterPath = "",
                            releaseDate = "2025-05-14",
                            voteAverage = 8.0
                        )
                    )
                )
            ) {}
        }
    }
}

@Preview
@Composable
private fun FavoriteTvPreview() {
    AppTheme {
        FavoriteScreen(Res.string.fav_tv, {}) {
            FavoriteTvContent(
                FavoriteTvUiState(
                    favoriteTvData = listOf(
                        FavoriteTv(
                            tvId = 1,
                            title = "The Office",
                            posterPath = "",
                            voteAverage = 8.0
                        ),
                        FavoriteTv(
                            tvId = 2,
                            title = "The Office",
                            posterPath = "",
                            voteAverage = 8.0
                        )
                    )
                )
            ) {}
        }
    }
}
