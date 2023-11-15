package com.example.moveeapp_compose_kmm.ui.scene.artistdetail

import com.example.moveeapp_compose_kmm.ui.scene.artistdetail.model.ArtistDetailUiModel

data class ArtistDetailUiState(
    val isLoading: Boolean = true,
    val artistDetailData: ArtistDetailUiModel = ArtistDetailUiModel(),
    val error: String? = null
)