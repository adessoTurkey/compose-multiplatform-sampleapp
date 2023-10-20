package com.example.moveeapp_compose_kmm.ui.tab

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import cafe.adriel.voyager.navigator.Navigator
import cafe.adriel.voyager.navigator.tab.Tab
import cafe.adriel.voyager.navigator.tab.TabOptions
import cafe.adriel.voyager.transitions.SlideTransition
import com.example.moveeapp_compose_kmm.MR
import com.example.moveeapp_compose_kmm.ui.scene.tvscreen.TvScreen
import dev.icerock.moko.resources.compose.painterResource
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
            val icon = painterResource(MR.images.ic_tabbar_tv)
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