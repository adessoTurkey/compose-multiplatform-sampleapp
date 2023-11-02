package com.example.moveeapp_compose_kmm.nav

import androidx.compose.runtime.Composable
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import com.example.moveeapp_compose_kmm.core.viewModel
import com.example.moveeapp_compose_kmm.ui.scene.movie.MovieScreen
import com.example.moveeapp_compose_kmm.ui.scene.movie.MovieViewModel

class MovieScreen : Screen {
    @Composable
    override fun Content() {
        val navigator = LocalNavigator.currentOrThrow
        val viewModel: MovieViewModel = viewModel()

        MovieScreen(viewModel) { navigator.push(MovieDetailScreen(it)) }
    }
}
