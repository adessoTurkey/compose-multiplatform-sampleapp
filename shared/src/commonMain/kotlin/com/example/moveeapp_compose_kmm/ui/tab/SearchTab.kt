package com.example.moveeapp_compose_kmm.ui.tab

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import cafe.adriel.voyager.navigator.Navigator
import cafe.adriel.voyager.navigator.tab.Tab
import cafe.adriel.voyager.navigator.tab.TabOptions
import com.example.moveeapp_compose_kmm.MR
import com.example.moveeapp_compose_kmm.ui.scene.searchscreen.SearchScreen
import dev.icerock.moko.resources.compose.painterResource

internal object SearchTab : Tab {

    @Composable
    override fun Content() {
        Navigator(SearchScreen())
    }

    override val options: TabOptions
        @Composable
        get() {
            val icon = painterResource(MR.images.ic_tabbar_search)

            return remember {
                TabOptions(
                    index = 0u,
                    title = "Search",
                    icon = icon
                )
            }
        }
}