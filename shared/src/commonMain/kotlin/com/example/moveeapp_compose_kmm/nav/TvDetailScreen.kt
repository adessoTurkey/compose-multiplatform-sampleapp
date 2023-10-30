package com.example.moveeapp_compose_kmm.nav

import androidx.compose.runtime.Composable
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import com.example.moveeapp_compose_kmm.core.BackHandler
import com.example.moveeapp_compose_kmm.core.viewModel
import com.example.moveeapp_compose_kmm.ui.scene.tvdetailscreen.TvDetailScreen
import com.example.moveeapp_compose_kmm.ui.scene.tvdetailscreen.TvDetailViewModel

class TvDetailScreen(private val tvId: Int) : Screen {

    @Composable
    override fun Content() {
        val navigator = LocalNavigator.currentOrThrow
        val viewModel: TvDetailViewModel = viewModel()
        TvDetailScreen(
            viewModel = viewModel,
            tvId = tvId,
            navigateToActor = { navigator.push(ActorDetailScreen(it)) },
            onBackPressed = navigator::pop
        )

        BackHandler(isEnabled = true) {
            navigator.pop()
        }
    }
}
