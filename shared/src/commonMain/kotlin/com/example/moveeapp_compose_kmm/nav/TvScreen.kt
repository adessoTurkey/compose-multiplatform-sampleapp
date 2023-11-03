package com.example.moveeapp_compose_kmm.nav

import androidx.compose.runtime.Composable
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import com.example.moveeapp_compose_kmm.core.viewModel
import com.example.moveeapp_compose_kmm.ui.scene.tv.TvScreen
import com.example.moveeapp_compose_kmm.ui.scene.tv.TvViewModel

class TvScreen : Screen {

    @Composable
    override fun Content() {
        val navigator = LocalNavigator.currentOrThrow
        val viewModel: TvViewModel = viewModel()

        TvScreen(viewModel) {
            navigator.push(TvDetailScreen(it))
        }
    }
}
