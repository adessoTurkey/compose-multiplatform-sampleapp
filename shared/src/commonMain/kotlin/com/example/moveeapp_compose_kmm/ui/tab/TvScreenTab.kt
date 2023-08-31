package com.example.moveeapp_compose_kmm.ui.tab

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Tv
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import cafe.adriel.voyager.navigator.Navigator
import cafe.adriel.voyager.navigator.tab.Tab
import cafe.adriel.voyager.navigator.tab.TabOptions
import com.example.moveeapp_compose_kmm.ui.scene.tvscreen.TvScreen

object TvScreenTab : Tab {

    @Composable
    override fun Content() {
        Navigator(TvScreen())
    }

    override val options: TabOptions
        @Composable
        get() {
            val icon = rememberVectorPainter(Icons.Default.Tv) //TODO TvSeries icon will change

            return remember {
                TabOptions(
                    index = 0u,
                    title = "Tv Series",
                    icon = icon
                )
            }
        }
}