package com.example.moveeapp_compose_kmm.nav

import androidx.compose.runtime.Composable
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import com.example.moveeapp_compose_kmm.core.BackHandler
import com.example.moveeapp_compose_kmm.core.viewModel
import com.example.moveeapp_compose_kmm.ui.scene.moviedetail.MovieDetailScreen
import com.example.moveeapp_compose_kmm.ui.scene.moviedetail.MovieDetailViewModel

class MovieDetailScreen(
    private val movieId: Int,
) : Screen {

    @Composable
    override fun Content() {
        val navigator = LocalNavigator.currentOrThrow

        val viewModel: MovieDetailViewModel = viewModel()
        MovieDetailScreen(
            viewModel = viewModel,
            movieId = movieId,
            navigateToActor = { navigator.push(ArtistDetailScreen(it)) },
            onBackPressed = navigator::pop,
        )

        BackHandler(isEnabled = true) {
            navigator.pop()
        }
    }
}
