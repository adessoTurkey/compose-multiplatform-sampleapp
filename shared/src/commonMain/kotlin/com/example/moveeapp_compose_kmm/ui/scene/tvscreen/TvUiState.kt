package com.example.moveeapp_compose_kmm.ui.scene.tvscreen

import com.example.moveeapp_compose_kmm.data.uimodel.tv.PopularTvUiModel
import com.example.moveeapp_compose_kmm.data.uimodel.tv.TopRatedTvUiModel

data class TvUiState(
    val isLoading: Boolean = true,
    val popularTvData: List<PopularTvUiModel> = emptyList(),
    val topRatedTvData: List<TopRatedTvUiModel> = emptyList(),
    val error: String? = null
)