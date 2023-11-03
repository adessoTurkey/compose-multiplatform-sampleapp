package com.example.moveeapp_compose_kmm.ui.scene.tvdetail

import com.example.moveeapp_compose_kmm.ui.scene.tvdetail.model.TvDetailUiModel

data class TvDetailUiState(
    val isLoading: Boolean = true,
    val tvDetailData: TvDetailUiModel = TvDetailUiModel(),
    val error: String? = null
)