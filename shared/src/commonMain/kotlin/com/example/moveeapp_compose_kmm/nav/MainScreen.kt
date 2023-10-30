package com.example.moveeapp_compose_kmm.nav

import androidx.compose.runtime.Composable
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.tab.TabNavigator
import com.example.moveeapp_compose_kmm.core.viewModel
import com.example.moveeapp_compose_kmm.ui.scene.main.MainScreen
import com.example.moveeapp_compose_kmm.ui.tab.TabItem

class MainScreen : Screen {

    @Composable
    override fun Content() {
        TabNavigator(TabItem.MoviesTab().tab, true) { navigator ->
            MainScreen(
                viewModel = viewModel(),
                isTabSelected = {
                    navigator.current.key == it.key
                },
                onTabSelected = {
                    navigator.current = it.tab
                }
            )
        }
    }
}
