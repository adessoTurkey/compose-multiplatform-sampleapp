package com.example.moveeapp_compose_kmm.ui.scene.tv

import com.example.moveeapp_compose_kmm.domain.tv.PopularTv
import com.example.moveeapp_compose_kmm.domain.tv.TopRatedTv

data class TvUiState(
    val isLoading: Boolean = true,
    val popularTvData: List<PopularTv> = emptyList(),
    val topRatedTvData: List<TopRatedTv> = emptyList(),
    val error: String? = null
)