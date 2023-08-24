package com.example.moveeapp_compose_kmm.ui.scene.moviescreen

import com.example.moveeapp_compose_kmm.data.uimodel.movie.NowPlayingMovieUiModel
import com.example.moveeapp_compose_kmm.data.uimodel.movie.PopularMovieUiModel

data class MovieUiState(
    val isLoading: Boolean = true,
    val popularMovieData: List<PopularMovieUiModel> = emptyList(),
    val nowPlayingMovieData: List<NowPlayingMovieUiModel> = emptyList(),
    val error: String? = null
)