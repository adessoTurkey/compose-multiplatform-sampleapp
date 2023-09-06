package com.example.moveeapp_compose_kmm.ui.scene.tvdetailscreen

import com.example.moveeapp_compose_kmm.data.uimodel.tv.TvDetailUiModel

data class TvDetailUiState(
    val isLoading: Boolean = true,
    val tvDetailData: TvDetailUiModel = TvDetailUiModel(),
    val error: String? = null
)