package com.example.moveeapp_compose_kmm.ui.scene.moviedetail

import com.example.moveeapp_compose_kmm.ui.scene.moviedetail.model.MovieDetailUiModel

data class MovieDetailUiState(
    val isLoading: Boolean = true,
    val movieDetailData: MovieDetailUiModel = MovieDetailUiModel(),
    val error: String? = null
)
