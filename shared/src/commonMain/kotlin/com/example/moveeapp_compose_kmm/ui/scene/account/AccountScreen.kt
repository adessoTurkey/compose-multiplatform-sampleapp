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
import androidx.compose.material.Button
import androidx.compose.material.Surface
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import com.example.moveeapp_compose_kmm.MR
import com.example.moveeapp_compose_kmm.core.viewModel
import com.example.moveeapp_compose_kmm.ui.components.TextItem
import com.example.moveeapp_compose_kmm.ui.scene.account.favoritescreen.FavoriteScreen
import com.example.moveeapp_compose_kmm.ui.scene.splashscreen.SplashScreen
import dev.icerock.moko.resources.compose.fontFamilyResource
import dev.icerock.moko.resources.compose.stringResource

class AccountScreen : Screen {
    @Composable
    override fun Content() {
        val navigator = LocalNavigator.currentOrThrow
        val viewModel: AccountDetailViewModel = viewModel()
        val uiState by viewModel.uiState.collectAsState()
        val logoutState by viewModel.logoutState.collectAsState()

        LaunchedEffect(logoutState) {
            if (logoutState) {
                navigator.parent?.parent?.replaceAll(SplashScreen())
            }
        }

        Column(modifier = Modifier.background(MaterialTheme.colorScheme.secondaryContainer)) {
            Spacer(
                Modifier.fillMaxWidth().windowInsetsTopHeight(WindowInsets.statusBars)
                    .background(MaterialTheme.colorScheme.primary)
            )

            SuccessContent(
                uiState = uiState,
                onFavMovieClick = { navigator.push(FavoriteScreen(it)) },
                onFavTvClick = { navigator.push(FavoriteScreen(it)) },
                onLogoutClick = { viewModel.logout() }
            )
        }
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
                    text = uiState.accountData.originalName,
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

            Button(onClick = onLogoutClick) {
                TextItem(text = "Logout")
            }
        }
    }
}

enum class MediaType(val mediaType: String) {
    MOVIE("movie"),
    TV("tv")
}


