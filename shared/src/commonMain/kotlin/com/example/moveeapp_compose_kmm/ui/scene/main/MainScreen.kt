package com.example.moveeapp_compose_kmm.ui.scene.main

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.moveeapp_compose_kmm.nav.CurrentTab
import com.example.moveeapp_compose_kmm.ui.tab.TabItem
import dev.icerock.moko.resources.compose.painterResource
import dev.icerock.moko.resources.compose.stringResource

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
            BottomNavigation(
                modifier = Modifier,
                backgroundColor = MaterialTheme.colorScheme.primaryContainer,
                contentColor = MaterialTheme.colorScheme.secondary,
                elevation = 4.dp,
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

    BottomNavigationItem(
        modifier = Modifier,
        unselectedContentColor = MaterialTheme.colorScheme.secondary,
        selectedContentColor = MaterialTheme.colorScheme.primary,
        alwaysShowLabel = true,
        selected = selected,
        onClick = onClick,
        label = { Text(text = title) },
        icon = { Icon(painter = painterResource(tab.icon), contentDescription = title) })
}
