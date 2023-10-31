package com.example.moveeapp_compose_kmm.nav

import androidx.compose.runtime.Composable
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import com.example.moveeapp_compose_kmm.core.BackHandler
import com.example.moveeapp_compose_kmm.core.viewModel
import com.example.moveeapp_compose_kmm.ui.scene.searchscreen.SearchScreen
import com.example.moveeapp_compose_kmm.ui.scene.searchscreen.SearchViewModel

class SearchScreen : Screen {

    @Composable
    override fun Content() {
        val navigator = LocalNavigator.currentOrThrow
        val viewModel: SearchViewModel = viewModel()

        SearchScreen(
            viewModel = viewModel,
            navigateToMovie = { navigator.push(MovieDetailScreen(it)) },
            navigateToTv = { navigator.push(TvDetailScreen(it)) },
            navigateToActor = { navigator.push(ActorDetailScreen(it)) }
        )

        BackHandler(isEnabled = true) {
            navigator.pop()
        }
    }
}
