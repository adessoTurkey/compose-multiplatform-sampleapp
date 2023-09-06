package com.example.moveeapp_compose_kmm.ui.scene.moviedetailscreen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Card
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.Navigator
import cafe.adriel.voyager.navigator.currentOrThrow
import com.example.moveeapp_compose_kmm.core.viewModel
import com.example.moveeapp_compose_kmm.data.uimodel.CreditUiModel
import com.example.moveeapp_compose_kmm.ui.components.CardImageItem
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

        viewModel.fetchData(movieId)
        MovieDetailContent(navigator, uiState)
    }
}

@Composable
fun MovieDetailContent(
    navigator: Navigator,
    uiState: MovieDetailUiState
) {
    SuccessContent(navigator, uiState)
}

@Composable
fun SuccessContent(navigator: Navigator, uiState: MovieDetailUiState) {
    val scrollState = rememberScrollState()

    Column(modifier = Modifier.verticalScroll(scrollState)) {
        PosterImageItem(imagePath = uiState.movieDetailData.backdropPath)

        Column(modifier = Modifier.padding(start = 16.dp, end = 16.dp)) {

            TextItem(
                text = uiState.movieDetailData.title,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold
            )
            Row(
                modifier = Modifier
                    .padding(vertical = 8.dp)
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                RateItem(rate = round(uiState.movieDetailData.voteAverage).toString())

                RuntimeItem(runtime = uiState.movieDetailData.runtime.toString())
            }

            TextItem(
                text = uiState.movieDetailData.overview,
                maxLines = Int.MAX_VALUE
            )
        }

        TextItem(
            text = "Cast",
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)
        )
        LazyRow(
            modifier = Modifier.padding(start = 16.dp, end = 16.dp, bottom = 16.dp),
            horizontalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            items(uiState.movieDetailData.credit.size) { index ->
                MovieCredit(
                    credit = uiState.movieDetailData.credit[index],
                    onClick = { id -> })
                //TODO navigate to cast detail screen
            }
        }
    }

}

@Composable
fun MovieCredit(
    credit: CreditUiModel,
    onClick: (Int) -> Unit
) {
    Card(
        modifier = Modifier
            .width(100.dp)
            .fillMaxHeight()
            .clickable { onClick.invoke(credit.castId) },
        shape = RoundedCornerShape(5.dp),
        elevation = 5.dp
    ) {
        Column {

            CardImageItem(imagePath = credit.profilePath)

            TextItem(
                text = credit.originalName,
                modifier = Modifier.padding(horizontal = 3.dp, vertical = 5.dp),
                fontSize = 14.sp,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
        }
    }
}
