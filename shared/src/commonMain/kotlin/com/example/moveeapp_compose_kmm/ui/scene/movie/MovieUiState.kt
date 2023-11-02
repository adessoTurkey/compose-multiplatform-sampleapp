package com.example.moveeapp_compose_kmm.ui.scene.movie

import com.example.moveeapp_compose_kmm.domain.movie.NowPlayingMovie
import com.example.moveeapp_compose_kmm.domain.movie.PopularMovie

data class MovieUiState(
    val isLoading: Boolean = true,
    val popularMovieData: List<PopularMovie> = emptyList(),
    val nowPlayingMovieData: List<NowPlayingMovie> = emptyList(),
    val error: String? = null
)