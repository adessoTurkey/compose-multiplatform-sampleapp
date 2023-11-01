package com.example.moveeapp_compose_kmm.ui.scene.tv

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.layout.windowInsetsTopHeight
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.util.lerp
import com.example.moveeapp_compose_kmm.core.ifNotNull
import com.example.moveeapp_compose_kmm.domain.tv.PopularTv
import com.example.moveeapp_compose_kmm.domain.tv.TopRatedTv
import com.example.moveeapp_compose_kmm.ui.components.ErrorScreen
import com.example.moveeapp_compose_kmm.ui.components.LoadingScreen
import com.example.moveeapp_compose_kmm.ui.components.PosterImageItem
import com.example.moveeapp_compose_kmm.ui.components.RateItem
import com.example.moveeapp_compose_kmm.ui.components.TextItem

@Composable
fun TvScreen(
    viewModel: TvViewModel,
    navigateToDetail: (Int) -> Unit,
) {
    val uiState by viewModel.uiState.collectAsState()

    Column(modifier = Modifier.fillMaxSize()) {
        Spacer(
            Modifier.fillMaxWidth()
                .windowInsetsTopHeight(WindowInsets.statusBars)
                .background(MaterialTheme.colorScheme.primary)
        )

        uiState.error.ifNotNull {
            ErrorScreen(it)
        }

        if (uiState.isLoading) {
            LoadingScreen()
        }

        SuccessContent(
            modifier = Modifier.weight(1f),
            popularTv = uiState.popularTvData,
            topRatedTv = uiState.topRatedTvData,
            navigateToDetail,
        )
    }
}

@Composable
fun SuccessContent(
    modifier: Modifier = Modifier,
    popularTv: List<PopularTv>,
    topRatedTv: List<TopRatedTv>,
    navigateToDetail: (Int) -> Unit,
) {
    TvLazyVerticalGrid(
        modifier = modifier,
        topRatedTv = topRatedTv,
        popularTv = popularTv,
        onclick = navigateToDetail
    )
}

@Composable
fun TvLazyVerticalGrid(
    modifier: Modifier = Modifier,
    topRatedTv: List<TopRatedTv>,
    popularTv: List<PopularTv>,
    onclick: (Int) -> Unit,
) {
    LazyVerticalGrid(modifier = modifier, columns = GridCells.Fixed(2),
        content = {
            item(span = { GridItemSpan(2) }) {
                Box {
                    Surface(
                        modifier = Modifier.fillMaxWidth().height(250.dp),
                        color = MaterialTheme.colorScheme.primary
                    ) {}
                    HorizontalMoviePager(popularTv) {
                        onclick(it)
                    }
                }
            }

            items(topRatedTv) { tvShow ->
                Card(
                    modifier = Modifier.background(MaterialTheme.colorScheme.background)
                        .padding(horizontal = 12.dp, vertical = 6.dp),
                    shape = MaterialTheme.shapes.small
                ) {
                    Column {
                        PosterImageItem(imagePath = tvShow.posterPath,
                            modifier = Modifier.clickable {
                                onclick(tvShow.tvId)
                            }
                        )

                        TextItem(
                            text = tvShow.title,
                            modifier = Modifier.padding(start = 6.dp, top = 6.dp, bottom = 6.dp)
                        )

                        RateItem(
                            rate = tvShow.voteAverage.toString(),
                            modifier = Modifier.padding(start = 6.dp, bottom = 6.dp)
                        )
                    }

                }
            }
        })
}


@OptIn(ExperimentalFoundationApi::class)
@Composable
fun HorizontalMoviePager(
    popularTv: List<PopularTv>, onclick: (Int) -> Unit,
) {
    val pagerState = rememberPagerState(
        initialPage = 0, initialPageOffsetFraction = 0f
    ) {
        popularTv.size
    }

    HorizontalPager(
        state = pagerState, modifier = Modifier.fillMaxWidth()
    ) { page ->
        Card(
            Modifier.graphicsLayer {
                val pageOffset = pagerState.initialPageOffsetFraction
                lerp(
                    start = 0.65f, stop = 1f, fraction = 0.5f - pageOffset.coerceIn(0f, 1f)
                ).also { scale ->
                    scaleX = scale
                    scaleY = scale
                }
            }.fillMaxWidth()
                .clickable {
                    onclick(popularTv[page].tvId)
                }.aspectRatio(0.666f)
        ) {
            PosterImageItem(imagePath = popularTv[page].posterPath)
        }
    }
}
