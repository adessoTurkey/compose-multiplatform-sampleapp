package com.example.moveeapp_compose_kmm.ui.scene.moviescreen

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material.Card
import androidx.compose.material.Surface
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.util.lerp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import com.example.moveeapp_compose_kmm.core.viewModel
import com.example.moveeapp_compose_kmm.data.uimodel.movie.NowPlayingMovieUiModel
import com.example.moveeapp_compose_kmm.data.uimodel.movie.PopularMovieUiModel
import com.example.moveeapp_compose_kmm.ui.components.CardImageItem
import com.example.moveeapp_compose_kmm.ui.components.DateItem
import com.example.moveeapp_compose_kmm.ui.components.ErrorScreen
import com.example.moveeapp_compose_kmm.ui.components.LoadingScreen
import com.example.moveeapp_compose_kmm.ui.components.PosterImageItem
import com.example.moveeapp_compose_kmm.ui.components.RateItem
import com.example.moveeapp_compose_kmm.ui.components.TextItem
import com.example.moveeapp_compose_kmm.ui.scene.moviedetailscreen.MovieDetailScreen
import com.example.moveeapp_compose_kmm.utils.Serializable

class MovieScreen : Screen, Serializable {
    @Composable
    override fun Content() {
        val navigator = LocalNavigator.currentOrThrow
        val viewModel: MovieViewModel = viewModel()

        val uiState by viewModel.uiState.collectAsState()
        MovieContent(uiState = uiState, onDetailClick = { navigator.push(MovieDetailScreen(it)) })
    }
}

@Composable
fun MovieContent(
    uiState: MovieUiState,
    onDetailClick: (Int) -> Unit
) {
    Box(modifier = Modifier.fillMaxSize()) {
        if (uiState.error != null) {
            ErrorScreen(uiState.error)
        }

        if (uiState.isLoading) {
            LoadingScreen()
        }
        SuccessContent(
            popularMovieData = uiState.popularMovieData,
            nowPlayingMovieData = uiState.nowPlayingMovieData,
            onDetailClick = onDetailClick
        )
    }
}

@Composable
fun SuccessContent(
    popularMovieData: List<PopularMovieUiModel>,
    nowPlayingMovieData: List<NowPlayingMovieUiModel>,
    onDetailClick: (Int) -> Unit
) {
    LazyColumn {
        item {
            HorizontalMoviePager(popularMovieData) { id ->
                onDetailClick(id)
            }
        }
        items(nowPlayingMovieData) { nowPlayingMovies ->
            NowPlayingMovieRow(nowPlayingMovies = nowPlayingMovies) { id ->
                onDetailClick(id)
            }
        }
    }
}

@Composable
fun NowPlayingMovieRow(
    nowPlayingMovies: NowPlayingMovieUiModel,
    onDetailClick: (Int) -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(150.dp)
            .padding(horizontal = 16.dp, vertical = 8.dp)
            .clickable { onDetailClick.invoke(nowPlayingMovies.movieId) },
        shape = MaterialTheme.shapes.small,
    ) {
        Row {

            CardImageItem(imagePath = nowPlayingMovies.posterPath)

            Column(
                modifier = Modifier
                    .fillMaxSize(),
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                TextItem(
                    text = nowPlayingMovies.title,
                    modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp),
                    fontWeight = FontWeight.Bold
                )
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp, vertical = 8.dp),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    DateItem(date = nowPlayingMovies.releaseDate)
                    RateItem(rate = nowPlayingMovies.voteAverage.toString())
                }
            }
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun HorizontalMoviePager(
    popularMovie: List<PopularMovieUiModel>,
    onDetailClick: (Int) -> Unit
) {
    val pagerState = rememberPagerState(
        initialPage = 0,
        initialPageOffsetFraction = 0f
    ) {
        popularMovie.size
    }

    Box {
        Surface(
            modifier = Modifier.fillMaxWidth().height(250.dp),
            color = MaterialTheme.colorScheme.primary
        ) {}

        HorizontalPager(
            state = pagerState,
            modifier = Modifier.fillMaxSize()
        ) { page ->
            Card(
                Modifier
                    .graphicsLayer {
                        val pageOffset = pagerState.initialPageOffsetFraction
                        lerp(
                            start = 0.65f,
                            stop = 1f,
                            fraction = 0.5f - pageOffset.coerceIn(0f, 1f)
                        ).also { scale ->
                            scaleX = scale
                            scaleY = scale
                        }
                    }
                    .fillMaxWidth()
                    .clickable {
                        onDetailClick(popularMovie[page].movieId)
                    }
                    .aspectRatio(0.666f)) {

                PosterImageItem(imagePath = popularMovie[page].posterPath)
            }
        }
    }
}
