package com.example.moveeapp_compose_kmm.ui.scene.moviedetailscreen

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Card
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import com.example.moveeapp_compose_kmm.core.viewModel
import com.example.moveeapp_compose_kmm.data.uimodel.CreditUiModel
import com.example.moveeapp_compose_kmm.ui.components.BackPressedItem
import com.example.moveeapp_compose_kmm.ui.components.DateItem
import com.example.moveeapp_compose_kmm.ui.components.DetailScreensAppBar
import com.example.moveeapp_compose_kmm.ui.components.ErrorScreen
import com.example.moveeapp_compose_kmm.ui.components.FavouriteItem
import com.example.moveeapp_compose_kmm.ui.components.LoadingScreen
import com.example.moveeapp_compose_kmm.ui.components.PosterImageItem
import com.example.moveeapp_compose_kmm.ui.components.RateItem
import com.example.moveeapp_compose_kmm.ui.components.RuntimeItem
import com.example.moveeapp_compose_kmm.ui.components.TextItem
import kotlin.math.round

class MovieDetailScreen(
    private val movieId: Int
) : Screen {

    @Composable
    override fun Content() {
        val navigator = LocalNavigator.currentOrThrow
        val viewModel: MovieDetailViewModel = viewModel()
        val uiState by viewModel.uiState.collectAsState()

        LaunchedEffect(Unit) {
            viewModel.fetchData(movieId)
        }

        Box(
            modifier = Modifier.fillMaxSize()
                .background(color = MaterialTheme.colorScheme.primaryContainer)
        ) {
            if (uiState.error != null) {
                ErrorScreen(uiState.error!!)
            }

            if (uiState.isLoading) {
                LoadingScreen()
            }
            SuccessContent(
                uiState = uiState,
                onDetailClick = { }, //todo navigate to actor detail
                onBackPressed = navigator::pop
            )
        }
    }
}

@Composable
fun SuccessContent(
    uiState: MovieDetailUiState,
    onDetailClick: (Int) -> Unit,
    onBackPressed: () -> Unit
) {
    val scrollState = rememberScrollState()

    Column(modifier = Modifier.verticalScroll(scrollState)) {

        DetailScreensAppBar(
            leadingIcon = { BackPressedItem { onBackPressed() } },
            trailingIcon = { FavouriteItem { } },
            content = {
                PosterImageItem(
                    imagePath = uiState.movieDetailData.backdropPath,
                    modifier = Modifier.padding(bottom = 12.dp)
                )

                RateItem(
                    rate = round(uiState.movieDetailData.voteAverage).toString(),
                    modifier = Modifier.align(
                        Alignment.BottomStart
                    ).padding(start = 16.dp)
                )
            }
        )
        MovieDetailContent(uiState = uiState)
        MovieCreditLazyRow(uiState = uiState, onDetailClick = onDetailClick)
    }
}

@Composable
fun MovieDetailContent(uiState: MovieDetailUiState) {
    Column(modifier = Modifier.padding(start = 16.dp, end = 16.dp)) {

        TextItem(
            modifier = Modifier.padding(top = 8.dp),
            text = uiState.movieDetailData.title,
            fontSize = 32.sp,
            fontWeight = FontWeight.Bold,
            maxLines = Int.MAX_VALUE,
            lineHeight = 34.sp
        )

        TextItem(
            modifier = Modifier.padding(top = 8.dp),
            text = uiState.movieDetailData.genre,
            textColor = MaterialTheme.colorScheme.secondary
        )

        Row(
            modifier = Modifier
                .padding(vertical = 8.dp)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.Start
        ) {
            RuntimeItem(
                modifier = Modifier.padding(end = 8.dp),
                runtime = uiState.movieDetailData.runtime.toString()
            )
            DateItem(date = uiState.movieDetailData.releaseDate)
        }

        Divider(
            modifier = Modifier.padding(horizontal = 16.dp, vertical = 10.dp),
            thickness = 1.dp,
            color = MaterialTheme.colorScheme.secondaryContainer
        )

        TextItem(
            text = uiState.movieDetailData.overview,
            maxLines = Int.MAX_VALUE
        )
    }
}

@Composable
fun MovieCreditLazyRow(
    uiState: MovieDetailUiState,
    onDetailClick: (Int) -> Unit
) {
    TextItem(
        text = "Cast",
        fontSize = 28.sp,
        fontWeight = FontWeight.Bold,
        modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)
    )

    LazyRow(
        modifier = Modifier.padding(start = 16.dp, end = 16.dp, bottom = 16.dp),
        horizontalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        items(uiState.movieDetailData.credit.size) { index ->
            MovieCreditCardView(
                credit = uiState.movieDetailData.credit[index],
                onClick = { id -> onDetailClick(id) })
        }
    }
}

@Composable
fun MovieCreditCardView(
    credit: CreditUiModel,
    onClick: (Int) -> Unit
) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Card(
            modifier = Modifier
                .size(100.dp)
                .clickable { onClick.invoke(credit.castId) },
            shape = RoundedCornerShape(100.dp)
        ) {
            PosterImageItem(imagePath = credit.profilePath)
        }
        TextItem(
            text = credit.originalName,
            modifier = Modifier.padding(horizontal = 3.dp, vertical = 5.dp),
            fontSize = 15.sp,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
        )
    }
}
