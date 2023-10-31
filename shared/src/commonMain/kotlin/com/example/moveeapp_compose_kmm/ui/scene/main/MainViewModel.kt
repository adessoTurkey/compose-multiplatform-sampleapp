package com.example.moveeapp_compose_kmm.ui.scene.main

import com.example.moveeapp_compose_kmm.core.ViewModel
import com.example.moveeapp_compose_kmm.ui.tab.TabItem
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class MainViewModel : ViewModel {
    val tabItems = MutableStateFlow(
        listOf(
            TabItem.MoviesTab(),
            TabItem.TvShowsTab(),
            TabItem.SearchTab(),
            TabItem.AccountTab()
        )
    ).asStateFlow()
}
