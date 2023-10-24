package com.example.moveeapp_compose_kmm.ui.scene.map

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import com.example.moveeapp_compose_kmm.MR
import com.example.moveeapp_compose_kmm.core.viewModel
import com.example.moveeapp_compose_kmm.map.Map
import com.example.moveeapp_compose_kmm.ui.components.BackPressedItem
import com.example.moveeapp_compose_kmm.ui.components.TextItem
import dev.icerock.moko.resources.compose.fontFamilyResource
import dev.icerock.moko.resources.compose.stringResource

class MapScreen : Screen {
    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    override fun Content() {
        val navigator = LocalNavigator.currentOrThrow
        val viewModel: MapViewModel = viewModel()
        val uiState by viewModel.uiState.collectAsState()

        LaunchedEffect(Unit) {
            viewModel.loadForecastWithLocation()
        }

        Scaffold(topBar = {
            TopAppBar(
                title = {
                    TextItem(
                        text = stringResource(MR.strings.cinema),
                        fontSize = 20.sp,
                        fontFamily = fontFamilyResource(MR.fonts.sfpro.bold),
                        textColor = MaterialTheme.colorScheme.primaryContainer
                    )
                },
                colors = TopAppBarDefaults.topAppBarColors(MaterialTheme.colorScheme.primary),
                navigationIcon = {
                    BackPressedItem { navigator.pop() }
                }
            )
        }) { paddingValues ->

            Map(
                modifier = Modifier.padding(paddingValues),
                uiState = uiState,
                onMarkerClick = viewModel::setSelectedCinema
            )
        }
    }
}