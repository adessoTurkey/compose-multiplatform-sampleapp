package com.example.moveeapp_compose_kmm.ui.tab

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Tv
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import cafe.adriel.voyager.navigator.Navigator
import cafe.adriel.voyager.navigator.tab.Tab
import cafe.adriel.voyager.navigator.tab.TabOptions
import cafe.adriel.voyager.transitions.SlideTransition
import com.example.moveeapp_compose_kmm.MR
import com.example.moveeapp_compose_kmm.ui.scene.tvscreen.TvScreen
import dev.icerock.moko.resources.compose.stringResource

internal class TvScreenTab : Tab {

    @Composable
    override fun Content() {
        Navigator(TvScreen()) {
            SlideTransition(it)
        }
    }

    override val options: TabOptions
        @Composable
        get() {
            val icon = rememberVectorPainter(Icons.Default.Tv) //TODO TvSeries icon will change
            val title = stringResource(MR.strings.tab_tv)

            return remember {
                TabOptions(
                    index = 2u,
                    title = title,
                    icon = icon
                )
            }
        }
}