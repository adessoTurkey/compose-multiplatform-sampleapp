package com.example.moveeapp_compose_kmm

import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.Navigator
import cafe.adriel.voyager.navigator.tab.CurrentTab
import cafe.adriel.voyager.navigator.tab.LocalTabNavigator
import cafe.adriel.voyager.navigator.tab.Tab
import cafe.adriel.voyager.navigator.tab.TabNavigator
import com.example.moveeapp_compose_kmm.ui.scene.splashscreen.SplashScreen
import com.example.moveeapp_compose_kmm.ui.tab.AccountTab
import com.example.moveeapp_compose_kmm.ui.tab.MovieScreenTab
import com.example.moveeapp_compose_kmm.ui.tab.SearchTab
import com.example.moveeapp_compose_kmm.ui.tab.TvScreenTab
import com.example.moveeapp_compose_kmm.ui.theme.AppTheme

@Composable
fun App() {
    AppTheme {
        Navigator(SplashScreen())
    }
}

internal object MainScreen : Screen {
    @Composable
    override fun Content() {
        TabNavigator(MovieScreenTab) {
            Scaffold(
                modifier = Modifier.windowInsetsPadding(WindowInsets.navigationBars),
                content = {
                    CurrentTab()
                },
                bottomBar = {
                    BottomNavigation(
                        modifier = Modifier,
                        backgroundColor = MaterialTheme.colorScheme.primaryContainer,
                        contentColor = MaterialTheme.colorScheme.secondary,
                        elevation = 4.dp,
                    ) {
                        TabNavigationItem(tab = MovieScreenTab)
                        TabNavigationItem(tab = TvScreenTab)
                        TabNavigationItem(tab = SearchTab)
                        TabNavigationItem(tab = AccountTab)
                    }
                },
                contentColor = MaterialTheme.colorScheme.secondaryContainer
            )
        }
    }
}

@Composable
private fun RowScope.TabNavigationItem(tab: Tab) {
    val tabNavigator = LocalTabNavigator.current
    val title = tab.options.title
    BottomNavigationItem(
        modifier = Modifier,
        unselectedContentColor = MaterialTheme.colorScheme.secondary,
        selectedContentColor = MaterialTheme.colorScheme.primary,
        alwaysShowLabel = true,
        label = {
            Text(
                text = title,
            )
        },
        selected = tabNavigator.current.key == tab.key,
        onClick = { tabNavigator.current = tab },
        icon = { Icon(painter = tab.options.icon!!, contentDescription = tab.options.title) })
}
