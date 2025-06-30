package com.example.moveeapp_compose_kmm.ui.scene.main

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.screen.ScreenKey
import cafe.adriel.voyager.navigator.tab.Tab
import cafe.adriel.voyager.navigator.tab.TabNavigator
import cafe.adriel.voyager.navigator.tab.TabOptions
import com.example.moveeapp_compose_kmm.nav.CurrentTab
import com.example.moveeapp_compose_kmm.ui.tab.TabItem
import com.example.moveeapp_compose_kmm.ui.theme.AppTheme
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun MainScreen(
    viewModel: MainViewModel,
    isTabSelected: (TabItem) -> Boolean,
    onTabSelected: (TabItem) -> Unit,
) {
    val tabItems by viewModel.tabItems.collectAsState()

    Scaffold(
        modifier = Modifier.windowInsetsPadding(WindowInsets.navigationBars),
        content = {
            Box(modifier = Modifier.padding(bottom = it.calculateBottomPadding())) {
                CurrentTab()
            }
        },
        bottomBar = {
            NavigationBar(
                modifier = Modifier,
                containerColor = MaterialTheme.colorScheme.primaryContainer,
                contentColor = MaterialTheme.colorScheme.secondary,
                tonalElevation = 4.dp,
            ) {
                for (tabItem in tabItems) {
                    TabNavigationItem(tab = tabItem, selected = isTabSelected(tabItem)) {
                        onTabSelected(tabItem)
                    }
                }
            }
        },
        contentColor = MaterialTheme.colorScheme.secondaryContainer
    )
}

@Composable
private fun RowScope.TabNavigationItem(
    tab: TabItem,
    selected: Boolean,
    onClick: () -> Unit,
) {
    val title = stringResource(tab.title)

    NavigationBarItem(
        modifier = Modifier,
        colors = NavigationBarItemDefaults.colors(
            selectedIconColor = MaterialTheme.colorScheme.primary,
            selectedTextColor = MaterialTheme.colorScheme.primary,
            unselectedIconColor = MaterialTheme.colorScheme.secondary,
            unselectedTextColor = MaterialTheme.colorScheme.secondary,
        ),
        alwaysShowLabel = true,
        selected = selected,
        onClick = onClick,
        label = { Text(text = title) },
        icon = { Icon(painter = painterResource(tab.icon), contentDescription = title) })
}

@Preview
@Composable
fun MainPreview() {
    class TestTab : Tab {
        override val options: TabOptions
            @Composable get() {
                return TabOptions(0u, "")
            }

        override val key: ScreenKey get() = "test"

        @Composable
        override fun Content() {
            Box(Modifier.fillMaxSize())
        }
    }

    AppTheme {
        TabNavigator(TestTab()) {
            MainScreen(
                viewModel = MainViewModel(),
                isTabSelected = { it.key == "MoviesTab" },
                onTabSelected = {},
            )
        }
    }
}
