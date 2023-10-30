package com.example.moveeapp_compose_kmm.ui.scene.account

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.layout.windowInsetsTopHeight
import androidx.compose.material.Surface
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.moveeapp_compose_kmm.MR
import com.example.moveeapp_compose_kmm.ui.components.TextItem
import dev.icerock.moko.resources.compose.fontFamilyResource
import dev.icerock.moko.resources.compose.stringResource

@Composable
fun AccountScreen(
    viewModel: AccountDetailViewModel,
    navigateToSplash: () -> Unit,
    navigateToFavorite: (MediaType) -> Unit,
) {
    val uiState by viewModel.uiState.collectAsState()
    val logoutState by viewModel.logoutState.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.getAccountDetail()
    }

    LaunchedEffect(logoutState) {
        if (logoutState) {
            navigateToSplash()
        }
    }

    Column(modifier = Modifier.background(MaterialTheme.colorScheme.secondaryContainer)) {
        Spacer(
            Modifier.fillMaxWidth().windowInsetsTopHeight(WindowInsets.statusBars)
                .background(MaterialTheme.colorScheme.primary)
        )

        SuccessContent(
            uiState = uiState,
            onFavMovieClick = navigateToFavorite,
            onFavTvClick = navigateToFavorite,
            onLogoutClick = { viewModel.logout() }
        )
    }
}

@Composable
fun SuccessContent(
    uiState: AccountUiState,
    onFavMovieClick: (MediaType) -> Unit,
    onFavTvClick: (MediaType) -> Unit,
    onLogoutClick: () -> Unit,
) {
    Column {
        Surface(
            modifier = Modifier.fillMaxWidth().height(250.dp),
            color = MaterialTheme.colorScheme.primary
        ) {
            Column(modifier = Modifier.padding(horizontal = 16.dp)) {
                TextItem(
                    modifier = Modifier.padding(top = 24.dp),
                    text = stringResource(MR.strings.tab_profile),
                    fontSize = 34.sp,
                    textColor = MaterialTheme.colorScheme.primaryContainer,
                    fontFamily = fontFamilyResource(MR.fonts.sfpro.bold)
                )
                TextItem(
                    modifier = Modifier.padding(top = 31.dp),
                    text = stringResource(MR.strings.hello), fontSize = 20.sp,
                    textColor = MaterialTheme.colorScheme.primaryContainer,
                    fontFamily = fontFamilyResource(MR.fonts.sfpro.medium)
                )
                TextItem(
                    modifier = Modifier.padding(top = 8.dp),
                    text = uiState.accountData.fullName,
                    fontSize = 25.sp,
                    textColor = MaterialTheme.colorScheme.primaryContainer,
                    fontFamily = fontFamilyResource(MR.fonts.sfpro.bold)
                )
            }
        }
        Column(modifier = Modifier.padding(horizontal = 16.dp)) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(60.dp)
                    .padding(vertical = 8.dp)
                    .background(MaterialTheme.colorScheme.primaryContainer)
                    .clickable {
                        onFavMovieClick(MediaType.MOVIE)
                    },
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                TextItem(
                    modifier = Modifier.padding(start = 16.dp),
                    text = stringResource(MR.strings.fav_movie),
                )
                Icon(
                    Icons.Default.ArrowForward,
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.primary,
                    modifier = Modifier.padding(end = 16.dp)
                )
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(60.dp)
                    .padding(vertical = 8.dp)
                    .background(MaterialTheme.colorScheme.primaryContainer)
                    .clickable {
                        onFavTvClick(MediaType.TV)
                    },
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                TextItem(
                    modifier = Modifier.padding(start = 16.dp),
                    text = stringResource(MR.strings.fav_tv),
                )
                Icon(
                    Icons.Default.ArrowForward,
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.primary,
                    modifier = Modifier.padding(end = 16.dp)
                )
            }

            Spacer(modifier = Modifier.weight(1f))
            Text(
                modifier = Modifier
                    .padding(8.dp)
                    .fillMaxWidth()
                    .clickable {
                        onLogoutClick.invoke()
                    },
                text = "Logout",
                color = MaterialTheme.colorScheme.primary,
                style = TextStyle(textDecoration = TextDecoration.Underline),
                textAlign = TextAlign.Center,
                fontSize = 16.sp,
                fontFamily = fontFamilyResource(MR.fonts.sfpro.regular)
            )
        }
    }
}

enum class MediaType(val mediaType: String) {
    MOVIE("movie"),
    TV("tv")
}


