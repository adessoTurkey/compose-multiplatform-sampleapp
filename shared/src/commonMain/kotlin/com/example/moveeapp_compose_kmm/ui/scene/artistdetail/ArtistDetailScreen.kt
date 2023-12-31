package com.example.moveeapp_compose_kmm.ui.scene.artistdetail

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Card
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
import com.example.moveeapp_compose_kmm.MR
import com.example.moveeapp_compose_kmm.domain.MediaType
import com.example.moveeapp_compose_kmm.domain.artist.ArtistCredit
import com.example.moveeapp_compose_kmm.ui.components.BackPressedItem
import com.example.moveeapp_compose_kmm.ui.components.DetailPosterImage
import com.example.moveeapp_compose_kmm.ui.components.DetailScreensAppBar
import com.example.moveeapp_compose_kmm.ui.components.ExpandableText
import com.example.moveeapp_compose_kmm.ui.components.PosterImageItem
import com.example.moveeapp_compose_kmm.ui.components.TextItem
import dev.icerock.moko.resources.compose.stringResource

@Composable
fun ArtistDetailScreen(
    viewModel: ArtistDetailViewModel,
    actorId: Int,
    navigateToMovie: (Int) -> Unit,
    navigateToTv: (Int) -> Unit,
    onBackPressed: () -> Unit,
) {
    val uiState by viewModel.uiState.collectAsState()

    SuccessContent(uiState, onBackPressed = onBackPressed, onDetailClick = {
        when (it.second) {
            MediaType.MOVIE.mediaType -> navigateToMovie(it.first)
            MediaType.TV.mediaType -> navigateToTv(it.first)
        }
    })

    LaunchedEffect(Unit) {
        viewModel.fetchData(actorId)
    }
}

@Composable
fun SuccessContent(
    uiState: ArtistDetailUiState,
    onDetailClick: (Pair<Int, String>) -> Unit,
    onBackPressed: () -> Unit,
) {
    val scrollState = rememberScrollState()

    Column(modifier = Modifier.verticalScroll(scrollState)) {
        DetailScreensAppBar(
            leadingIcon = { BackPressedItem { onBackPressed() } },
            content = {
                DetailPosterImage(
                    imagePath = uiState.artistDetailData.profilePath
                )
            }
        )

        Column(modifier = Modifier.padding(horizontal = 16.dp).padding(top = 16.dp)) {
            TextItem(
                text = uiState.artistDetailData.name,
                fontSize = 28.sp,
                fontWeight = FontWeight.ExtraBold,
                modifier = Modifier.padding(bottom = 8.dp)
            )
            ExpandableText(
                text = uiState.artistDetailData.biography
            )
            Row(
                modifier = Modifier.padding(bottom = 24.dp, top = 24.dp),
                horizontalArrangement = Arrangement.spacedBy(5.dp)
            ) {
                TextItem(
                    text = stringResource(MR.strings.actor_born),
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold
                )
                TextItem(
                    maxLines = Int.MAX_VALUE,
                    fontSize = 16.sp,
                    text = "${uiState.artistDetailData.birthday}  ${uiState.artistDetailData.placeOfBirth}"
                )
            }
        }
        PersonCreditLazyRow(uiState, onDetailClick)
    }
}

@Composable
fun PersonCreditLazyRow(
    uiState: ArtistDetailUiState,
    onDetailClick: (Pair<Int, String>) -> Unit,
) {
    LazyRow(
        modifier = Modifier.padding(start = 16.dp, end = 16.dp, bottom = 16.dp),
        horizontalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        items(uiState.artistDetailData.credit.size) { index ->
            PersonCreditCardView(
                credit = uiState.artistDetailData.credit[index],
                onClick = onDetailClick
            )
        }
    }
}

@Composable
fun PersonCreditCardView(
    credit: ArtistCredit,
    onClick: (Pair<Int, String>) -> Unit,
) {
    Column(
        modifier = Modifier.width(110.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Card(
            modifier = Modifier
                .size(100.dp)
                .clickable { onClick.invoke(Pair(credit.id ?: 0, credit.mediaType ?: "")) },
            shape = RoundedCornerShape(100.dp)
        ) {
            PosterImageItem(imagePath = credit.imagePath ?: "")
        }
        TextItem(
            text = credit.name ?: "",
            modifier = Modifier.padding(horizontal = 3.dp, vertical = 5.dp),
            fontSize = 15.sp,
            overflow = TextOverflow.Ellipsis
        )
    }
}
