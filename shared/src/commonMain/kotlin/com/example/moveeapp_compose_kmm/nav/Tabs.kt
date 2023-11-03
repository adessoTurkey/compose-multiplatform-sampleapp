package com.example.moveeapp_compose_kmm.nav

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.staticCompositionLocalOf
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.core.screen.ScreenKey
import cafe.adriel.voyager.navigator.Navigator
import cafe.adriel.voyager.navigator.tab.LocalTabNavigator
import cafe.adriel.voyager.navigator.tab.Tab
import cafe.adriel.voyager.navigator.tab.TabOptions
import cafe.adriel.voyager.transitions.SlideTransition
import com.example.moveeapp_compose_kmm.ui.tab.TabItem
import dev.icerock.moko.resources.compose.painterResource
import dev.icerock.moko.resources.compose.stringResource
import kotlin.jvm.Transient

@Composable
fun CurrentTab() {
    val tabNavigator = LocalTabNavigator.current
    val currentTab = tabNavigator.current

    tabNavigator.saveableState("currentTab") {
        currentTab.Content()
    }
}

val TabItem.tab: Tab
    get() = TabData(this, tabContent)

private val TabItem.tabContent: Screen
    get() {
        return when (this) {
            is TabItem.MoviesTab -> MovieScreen()
            is TabItem.TvShowsTab -> TvScreen()
            is TabItem.SearchTab -> SearchScreen()
            is TabItem.AccountTab -> AccountScreen()
        }
    }

private class TabData(@Transient private val tab: TabItem, private val screen: Screen) : Tab {
    override val key: ScreenKey
        get() = tab.key

    @Composable
    override fun Content() {
        Navigator(screen) {
            SlideTransition(it)
        }
    }

    override val options: TabOptions
        @Composable get() {
            val icon = painterResource(tab.icon)
            val title = stringResource(tab.title)

            return remember {
                TabOptions(index = 0u, title = title, icon = icon)
            }
        }
}

val LocalMainNavigator = staticCompositionLocalOf<Navigator> { error("no navigator provided!") }
