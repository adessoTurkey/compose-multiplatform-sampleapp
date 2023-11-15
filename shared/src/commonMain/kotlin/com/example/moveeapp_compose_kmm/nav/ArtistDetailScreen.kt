package com.example.moveeapp_compose_kmm.nav

import androidx.compose.runtime.Composable
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import com.example.moveeapp_compose_kmm.core.BackHandler
import com.example.moveeapp_compose_kmm.core.viewModel
import com.example.moveeapp_compose_kmm.ui.scene.artistdetail.ArtistDetailScreen
import com.example.moveeapp_compose_kmm.ui.scene.artistdetail.ArtistDetailViewModel

class ArtistDetailScreen(
    private val actorId: Int,
) : Screen {

    @Composable
    override fun Content() {
        val navigator = LocalNavigator.currentOrThrow
        val viewModel: ArtistDetailViewModel = viewModel()

        ArtistDetailScreen(
            viewModel = viewModel,
            actorId = actorId,
            navigateToMovie = { navigator.push(MovieDetailScreen(movieId = it)) },
            navigateToTv = { navigator.push(TvDetailScreen(tvId = it)) },
            onBackPressed = navigator::pop
        )

        BackHandler(isEnabled = true) {
            navigator.pop()
        }
    }
}
