package com.example.moveeapp_compose_kmm.ui.scene.tvdetailscreen

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Card
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import com.example.moveeapp_compose_kmm.MR
import com.example.moveeapp_compose_kmm.core.BackHandler
import com.example.moveeapp_compose_kmm.core.Share
import com.example.moveeapp_compose_kmm.core.viewModel
import com.example.moveeapp_compose_kmm.data.uimodel.CreditUiModel
import com.example.moveeapp_compose_kmm.ui.components.BackPressedItem
import com.example.moveeapp_compose_kmm.ui.components.ChipItem
import com.example.moveeapp_compose_kmm.ui.components.DetailScreensAppBar
import com.example.moveeapp_compose_kmm.ui.components.ErrorScreen
import com.example.moveeapp_compose_kmm.ui.components.FavouriteItem
import com.example.moveeapp_compose_kmm.ui.components.FloatingActionButtonItem
import com.example.moveeapp_compose_kmm.ui.components.LoadingScreen
import com.example.moveeapp_compose_kmm.ui.components.PosterImageItem
import com.example.moveeapp_compose_kmm.ui.components.RateItem
import com.example.moveeapp_compose_kmm.ui.components.RateRow
import com.example.moveeapp_compose_kmm.ui.components.TextItem
import com.example.moveeapp_compose_kmm.ui.scene.actordetail.ActorDetailScreen
import com.example.moveeapp_compose_kmm.utils.Constants
import dev.icerock.moko.resources.compose.stringResource
import kotlin.math.round

class TvDetailScreen(private val tvId: Int) : Screen {

    @Composable
    override fun Content() {
        val navigator = LocalNavigator.currentOrThrow
        val viewModel: TvDetailViewModel = viewModel()
        val uiState by viewModel.uiState.collectAsState()
        val isFavorite by viewModel.isFavorite.collectAsState()
        val rating = viewModel.rating.collectAsState()

        LaunchedEffect(Unit) {
            viewModel.fetchData(tvId)
            viewModel.getTvState(tvId)
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
                onDetailClick = {
                    navigator.push(ActorDetailScreen(it))
                },
                onFavouriteClicked = { isFav, tvId ->
                    viewModel.addFavorite(
                        mediaId = tvId,
                        mediaType = Constants.TV,
                        isFavorite = isFav
                    )
                },
                onBackPressed = navigator::pop,
                isFavorite = isFavorite,
                ratingValue = rating,
                onRateTvShow = viewModel::rateTvShow
            )
        }

        BackHandler(isEnabled = true) {
            navigator.pop()
        }
    }
}

@Composable
fun SuccessContent(
    uiState: TvDetailUiState,
    isFavorite: Boolean,
    ratingValue: State<Int?>,
    onRateTvShow: (rate: Int, tvShowId: Int) -> Unit,
    onDetailClick: (Int) -> Unit,
    onBackPressed: () -> Unit,
    onFavouriteClicked: (isFav: Boolean, movieId: Int) -> Unit
) {
    val scrollState = rememberScrollState()

    Column(modifier = Modifier.verticalScroll(scrollState)) {
        DetailScreensAppBar(
            leadingIcon = {
                BackPressedItem(
                    modifier = Modifier.windowInsetsPadding(WindowInsets.statusBars)
                ) {
                    onBackPressed()
                }
            },
            trailingIcon = {
                FavouriteItem(
                    modifier = Modifier.windowInsetsPadding(WindowInsets.statusBars),
                    isFavorite = isFavorite,
                    onFavouriteClicked = {
                        onFavouriteClicked(
                            !isFavorite,
                            uiState.tvDetailData.tvSeriesId
                        )
                    }
                )
            },
            content = {
                PosterImageItem(
                    imagePath = uiState.tvDetailData.backdropPath,
                    modifier = Modifier.padding(bottom = 12.dp)
                )

                RateItem(
                    rate = round(uiState.tvDetailData.voteAverage).toString(),
                    modifier = Modifier.align(
                        Alignment.BottomStart
                    ).padding(start = 16.dp)
                )
            }
        )
        TvDetailContent(uiState = uiState, ratingValue = ratingValue, onRateTvShow = onRateTvShow)
        TvCreditLazyRow(uiState = uiState, onDetailClick = onDetailClick)
    }
}

@Composable
fun TvDetailContent(
    uiState: TvDetailUiState,
    ratingValue: State<Int?>,
    onRateTvShow: (rate: Int, tvShowId: Int) -> Unit,
) {
    Column(modifier = Modifier.padding(horizontal = 16.dp)) {

        TextItem(
            modifier = Modifier.padding(top = 8.dp),
            text = uiState.tvDetailData.title,
            fontSize = 32.sp,
            fontWeight = FontWeight.Bold,
            maxLines = Int.MAX_VALUE,
            lineHeight = 34.sp
        )

        TextItem(
            modifier = Modifier.padding(top = 8.dp),
            text = uiState.tvDetailData.genre,
            textColor = MaterialTheme.colorScheme.secondary
        )

        RateRow(
            modifier = Modifier.padding(vertical = 12.dp).height(IntrinsicSize.Min),
            ratingValue = ratingValue,
            onRatingValueChange = { onRateTvShow.invoke(it, uiState.tvDetailData.tvSeriesId) },
            hidableContent = {
                var shareText by remember { mutableStateOf("") }

                FloatingActionButtonItem(
                    text = stringResource(MR.strings.share),
                    icon = Icons.Default.Share,
                    onClick = { shareText = uiState.tvDetailData.homepage }
                )

                if (shareText.isNotEmpty()) {
                    Share(shareText)
                    shareText = ""
                }
            })


        Divider(
            modifier = Modifier.padding(vertical = 10.dp),
            thickness = 1.dp,
            color = MaterialTheme.colorScheme.secondaryContainer
        )

        TextItem(
            text = uiState.tvDetailData.overview,
            maxLines = Int.MAX_VALUE
        )

        Row(
            modifier = Modifier
                .padding(vertical = 8.dp)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.Start
        ) {
            ChipItem(
                string = stringResource(MR.strings.tv_detail_season) + " ${uiState.tvDetailData.numberOfSeasons}",
                backgroundColor = MaterialTheme.colorScheme.secondary
            )
            Spacer(modifier = Modifier.padding(8.dp))
            ChipItem(
                string = stringResource(MR.strings.tv_detail_episode) + " ${uiState.tvDetailData.numberOfEpisodes}",
                backgroundColor = MaterialTheme.colorScheme.secondary
            )
        }
    }
}

@Composable
fun TvCreditLazyRow(
    uiState: TvDetailUiState,
    onDetailClick: (Int) -> Unit
) {
    TextItem(
        text = stringResource(MR.strings.movie_detail_cast),
        fontSize = 28.sp,
        fontWeight = FontWeight.Bold,
        modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)
    )

    LazyRow(
        modifier = Modifier.padding(start = 16.dp, end = 16.dp, bottom = 16.dp),
        horizontalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        items(uiState.tvDetailData.credit.size) { index ->
            TvCreditCardView(
                credit = uiState.tvDetailData.credit[index],
                onClick = { id -> onDetailClick(id) })
        }
    }
}

@Composable
fun TvCreditCardView(
    credit: CreditUiModel,
    onClick: (Int) -> Unit
) {
    Column(
        modifier = Modifier.width(110.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
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
            overflow = TextOverflow.Ellipsis
        )
    }
}