package com.example.moveeapp_compose_kmm.ui.scene.moviedetail

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.defaultMinSize
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
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.Card
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.moveeapp_compose_kmm.core.getPlatformContext
import com.example.moveeapp_compose_kmm.core.ifNotNull
import com.example.moveeapp_compose_kmm.core.share
import com.example.moveeapp_compose_kmm.domain.MediaType
import com.example.moveeapp_compose_kmm.domain.artist.Credits
import com.example.moveeapp_compose_kmm.ui.components.BackPressedItem
import com.example.moveeapp_compose_kmm.ui.components.DateItem
import com.example.moveeapp_compose_kmm.ui.components.DetailPosterImage
import com.example.moveeapp_compose_kmm.ui.components.DetailScreensAppBar
import com.example.moveeapp_compose_kmm.ui.components.ErrorScreen
import com.example.moveeapp_compose_kmm.ui.components.FavouriteItem
import com.example.moveeapp_compose_kmm.ui.components.FloatingActionButtonItem
import com.example.moveeapp_compose_kmm.ui.components.LoadingScreen
import com.example.moveeapp_compose_kmm.ui.components.PosterImageItem
import com.example.moveeapp_compose_kmm.ui.components.RateItem
import com.example.moveeapp_compose_kmm.ui.components.RateRow
import com.example.moveeapp_compose_kmm.ui.components.RuntimeItem
import com.example.moveeapp_compose_kmm.ui.components.TextItem
import com.example.moveeapp_compose_kmm.ui.scene.moviedetail.model.MovieDetailUiModel
import com.example.moveeapp_compose_kmm.ui.theme.AppTheme
import movee.shared.generated.resources.Res
import movee.shared.generated.resources.movie_detail_cast
import movee.shared.generated.resources.share
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.ui.tooling.preview.Preview
import kotlin.math.round

@Composable
fun MovieDetailScreen(
    viewModel: MovieDetailViewModel,
    movieId: Int,
    navigateToActor: (Int) -> Unit,
    onBackPressed: () -> Unit,
) {
    val uiState by viewModel.uiState.collectAsState()
    val isFavorite by viewModel.isFavorite.collectAsState()
    val ratingValue = viewModel.rating.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.fetchData(movieId)
        viewModel.getMovieState(movieId)
    }

    Box(
        modifier = Modifier.fillMaxSize()
            .background(color = MaterialTheme.colorScheme.primaryContainer)
    ) {
        uiState.error.ifNotNull {
            ErrorScreen(it)
        }

        if (uiState.isLoading) {
            LoadingScreen()
        }

        SuccessContent(
            movieDetailData = uiState.movieDetailData,
            onDetailClick = navigateToActor,
            onFavouriteClicked = { isFav, movieId ->
                viewModel.addFavorite(
                    mediaType = MediaType.MOVIE.mediaType,
                    mediaId = movieId,
                    isFavorite = isFav
                )
            },
            onBackPressed = onBackPressed,
            isFavorite = isFavorite,
            ratingValue = ratingValue,
            onRateMovie = viewModel::rateMovie
        )
    }
}

@Composable
private fun SuccessContent(
    modifier: Modifier = Modifier,
    movieDetailData: MovieDetailUiModel,
    isFavorite: Boolean,
    onDetailClick: (Int) -> Unit,
    onBackPressed: () -> Unit,
    onFavouriteClicked: (isFav: Boolean, movieId: Int) -> Unit,
    ratingValue: State<Int?>,
    onRateMovie: (rate: Int, movieId: Int) -> Unit,
) {
    val scrollState = rememberScrollState()

    Column(modifier = modifier.verticalScroll(scrollState)) {
        DetailScreensAppBar(
            modifier = Modifier.windowInsetsPadding(WindowInsets.statusBars),
            leadingIcon = {
                BackPressedItem { onBackPressed() }
            },
            trailingIcon = {
                FavouriteItem(
                    isFavorite = isFavorite,
                    onFavouriteClicked = {
                        onFavouriteClicked(!isFavorite, movieDetailData.movieId)
                    }
                )
            },
            content = {
                DetailPosterImage(
                    imagePath = movieDetailData.backdropPath,
                    modifier = Modifier.padding(bottom = 12.dp).defaultMinSize(minHeight = 128.dp)
                )

                RateItem(
                    rate = round(movieDetailData.voteAverage).toString(),
                    modifier = Modifier.align(
                        Alignment.BottomStart
                    ).padding(start = 16.dp)
                )
            }
        )

        MovieDetailContent(
            data = movieDetailData,
            ratingValue = ratingValue,
            onRateMovie = onRateMovie
        )

        MovieCreditLazyRow(credit = movieDetailData.credit, onDetailClick = onDetailClick)
    }
}

@Composable
fun MovieDetailContent(
    data: MovieDetailUiModel,
    ratingValue: State<Int?>,
    onRateMovie: (rate: Int, movieId: Int) -> Unit,
) {
    val platformContext = getPlatformContext()

    Column(modifier = Modifier.padding(start = 16.dp, end = 16.dp)) {

        TextItem(
            modifier = Modifier.padding(top = 8.dp),
            text = data.title,
            fontSize = 32.sp,
            fontWeight = FontWeight.Bold,
            maxLines = Int.MAX_VALUE,
            lineHeight = 34.sp
        )

        TextItem(
            modifier = Modifier.padding(top = 8.dp),
            text = data.genre,
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
                runtime = data.runtime.toString()
            )
            DateItem(date = data.releaseDate)
        }

        RateRow(
            modifier = Modifier.padding(vertical = 12.dp).height(IntrinsicSize.Min),
            ratingValue = ratingValue,
            onRatingValueChange = { onRateMovie.invoke(it, data.movieId) },
            hidableContent = {
                FloatingActionButtonItem(
                    text = stringResource(Res.string.share),
                    icon = Icons.Default.Share,
                    onClick = {
                        share(
                            platformContext,
                            data.title,
                            data.overview,
                            data.posterPath.ifBlank { null }
                        )
                    }
                )
            })

        HorizontalDivider(
            modifier = Modifier.padding(vertical = 10.dp),
            thickness = 1.dp,
            color = MaterialTheme.colorScheme.secondaryContainer
        )

        TextItem(
            text = data.overview,
            maxLines = Int.MAX_VALUE
        )
    }
}

@Composable
fun MovieCreditLazyRow(
    credit: List<Credits>,
    onDetailClick: (Int) -> Unit,
) {
    TextItem(
        text = stringResource(Res.string.movie_detail_cast),
        fontSize = 28.sp,
        fontWeight = FontWeight.Bold,
        modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)
    )

    LazyRow(
        modifier = Modifier.padding(start = 16.dp, end = 16.dp, bottom = 16.dp),
        horizontalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        items(credit.size) { index ->
            MovieCreditCardView(
                credit = credit[index],
                onClick = { id -> onDetailClick(id) })
        }
    }
}

@Composable
fun MovieCreditCardView(
    credit: Credits,
    onClick: (Int) -> Unit,
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

@Preview
@Composable
fun SuccessContentPreview() {
    AppTheme {
        SuccessContent(
            modifier = Modifier
                .fillMaxSize()
                .background(color = MaterialTheme.colorScheme.primaryContainer),
            movieDetailData = MovieDetailUiModel(
                movieId = 1,
                title = "Movie Title",
                genre = "Action",
                runtime = 120,
                releaseDate = "2023-01-01",
                overview = "This is a great movie.",
                voteAverage = 8.5,
                posterPath = "",
                backdropPath = "",
                credit = listOf(
                    Credits(1, "John Doe", "profile_path_1"),
                    Credits(2, "Jane Smith", "profile_path_2"),
                    Credits(3, "Bob Johnson", "profile_path_3"),
                )
            ),
            isFavorite = false,
            onDetailClick = {},
            onBackPressed = {},
            onFavouriteClicked = { _, _ -> },
            ratingValue = mutableStateOf(3),
            onRateMovie = { _, _ -> }
        )
    }
}
