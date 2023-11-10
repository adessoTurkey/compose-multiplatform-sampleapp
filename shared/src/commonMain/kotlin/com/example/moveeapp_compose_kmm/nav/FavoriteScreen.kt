package com.example.moveeapp_compose_kmm.nav

import androidx.compose.runtime.Composable
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import com.example.moveeapp_compose_kmm.core.BackHandler
import com.example.moveeapp_compose_kmm.core.viewModel
import com.example.moveeapp_compose_kmm.domain.MediaType
import com.example.moveeapp_compose_kmm.ui.scene.account.favoritescreen.FavoriteScreen
import com.example.moveeapp_compose_kmm.ui.scene.account.favoritescreen.FavoriteViewModel

class FavoriteScreen(private val mediaType: MediaType) : Screen {

    @Composable
    override fun Content() {
        val navigator = LocalNavigator.currentOrThrow
        val viewModel: FavoriteViewModel = viewModel()

        FavoriteScreen(
            viewModel = viewModel,
            mediaType = mediaType,
            navigateToMovie = { navigator.push(MovieDetailScreen(it)) },
            navigateToTv = { navigator.push(TvDetailScreen(it)) },
            navigateBack = { navigator.pop() }
        )

        BackHandler(isEnabled = true) {
            navigator.pop()
        }
    }
}
