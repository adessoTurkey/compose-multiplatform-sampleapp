package com.example.moveeapp_compose_kmm.ui.scene.search

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.layout.windowInsetsTopHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.moveeapp_compose_kmm.MR
import com.example.moveeapp_compose_kmm.core.ifNotNull
import com.example.moveeapp_compose_kmm.domain.MediaType
import com.example.moveeapp_compose_kmm.ui.components.CardImageItem
import com.example.moveeapp_compose_kmm.ui.components.ErrorScreen
import com.example.moveeapp_compose_kmm.ui.components.LoadingScreen
import com.example.moveeapp_compose_kmm.ui.components.SearchTextField
import com.example.moveeapp_compose_kmm.ui.components.TextItem
import com.example.moveeapp_compose_kmm.ui.scene.search.model.SearchUiModel
import dev.icerock.moko.resources.compose.painterResource
import dev.icerock.moko.resources.compose.stringResource

@Composable
fun SearchScreen(
    viewModel: SearchViewModel,
    navigateToMovie: (Int) -> Unit,
    navigateToTv: (Int) -> Unit,
    navigateToActor: (Int) -> Unit,
) {
    val uiState by viewModel.uiState.collectAsState()
    val queryState by viewModel.query.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.makeSearch()
    }

    Column(modifier = Modifier.background(MaterialTheme.colorScheme.secondaryContainer)) {
        Spacer(
            Modifier.fillMaxWidth().windowInsetsTopHeight(WindowInsets.statusBars)
                .background(MaterialTheme.colorScheme.primary)
        )

        uiState.error.ifNotNull {
            ErrorScreen(it)
        }

        SearchContent(uiState = uiState,
            query = queryState,
            handleQueryState = viewModel::handleQueryChange,
            onDetailClick = { id, mediaType ->
                when (mediaType) {
                    MediaType.MOVIE.title -> navigateToMovie(id)

                    MediaType.TV.title -> navigateToTv(id)

                    MediaType.PERSON.title -> navigateToActor(id)
                }
            })
    }
}

@Composable
fun SearchContent(
    uiState: SearchUiState,
    query: String,
    handleQueryState: (String) -> Unit,
    onDetailClick: (Int, String) -> Unit,
) {
    Column {
        Box(
            modifier = Modifier.fillMaxSize()
                .background(MaterialTheme.colorScheme.secondaryContainer).padding()
        ) {
            Spacer(
                modifier = Modifier.height(250.dp).fillMaxWidth().align(Alignment.TopCenter)
                    .background(MaterialTheme.colorScheme.primary)
            )
            Column(modifier = Modifier.padding(horizontal = 24.dp).padding(top = 40.dp)) {
                TextItem(
                    text = stringResource(MR.strings.tab_search),
                    fontSize = 34.sp,
                    fontWeight = FontWeight.Bold,
                    textColor = MaterialTheme.colorScheme.primaryContainer
                )
                SearchTextField(
                    modifier = Modifier.padding(top = 16.dp, bottom = 8.dp),
                    query = query,
                    onValueChange = handleQueryState
                )

                when {
                    uiState.isLoading -> {
                        LoadingScreen(
                            modifier = Modifier.padding(top = 100.dp),
                            contentAlignment = Alignment.TopCenter
                        )
                    }

                    !uiState.emptyState -> {
                        LazyColumn {
                            items(uiState.data) {
                                SearchRow(searchItem = it, onDetailClick = onDetailClick)
                            }
                        }
                    }

                    uiState.emptyState -> {
                        Box(
                            modifier = Modifier.fillMaxSize(),
                            contentAlignment = Alignment.Center
                        ) {
                            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                                Image(
                                    painter = painterResource(MR.images.search_place_holder),
                                    contentDescription = null
                                )
                                TextItem(
                                    modifier = Modifier.padding(top = 8.dp),
                                    text = stringResource(MR.strings.search_empty_text),
                                    textColor = MaterialTheme.colorScheme.primary,
                                    fontSize = 18.sp
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun SearchRow(searchItem: SearchUiModel, onDetailClick: (Int, String) -> Unit) {
    Card(
        modifier = Modifier.fillMaxWidth().padding(vertical = 10.dp)
            .clickable { onDetailClick(searchItem.id, searchItem.mediaType) },
        shape = RoundedCornerShape(8.dp),
        colors = CardDefaults.cardColors(MaterialTheme.colorScheme.primaryContainer),
        elevation = CardDefaults.cardElevation(5.dp)
    ) {
        Row {
            CardImageItem(
                modifier = Modifier.size(width = 66.6.dp, height = 100.dp),
                imagePath = searchItem.imagePath,
                contentScale = ContentScale.Fit
            )
            Column(
                modifier = Modifier.height(height = 100.dp).padding(horizontal = 16.dp)
                    .fillMaxWidth(), verticalArrangement = Arrangement.SpaceBetween
            ) {
                TextItem(
                    text = searchItem.name,
                    modifier = Modifier.padding(top = 8.dp),
                    fontSize = 16.sp, fontWeight = FontWeight.Bold,
                )

                Row(
                    modifier = Modifier.padding(bottom = 8.dp),
                    horizontalArrangement = Arrangement.spacedBy(5.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {

                    searchItem.iconType?.let { painterResource(it) }?.let {
                        Icon(
                            painter = it,
                            contentDescription = null,
                            modifier = Modifier.size(15.dp),
                            tint = MaterialTheme.colorScheme.primary
                        )
                    }
                    TextItem(
                        text = searchItem.mediaType,
                    )
                }
            }
        }
    }
}
