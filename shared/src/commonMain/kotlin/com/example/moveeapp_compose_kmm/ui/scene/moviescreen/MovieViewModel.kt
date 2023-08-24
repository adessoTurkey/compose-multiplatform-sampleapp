package com.example.moveeapp_compose_kmm.ui.scene.moviescreen

import cafe.adriel.voyager.core.model.ScreenModel
import cafe.adriel.voyager.core.model.coroutineScope
import com.example.moveeapp_compose_kmm.data.repository.MovieRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.update

class MovieViewModel(private val repository: MovieRepository) : ScreenModel {

    private val _uiState = MutableStateFlow(MovieUiState())
    val uiState: StateFlow<MovieUiState> = _uiState

    init {
        fetchData()
    }

    private fun fetchData() {

        combine(
            repository.getPopularMovie(),
            repository.getNowPlayingMovie()
        ) { popularMovieResult, nowPlayingMovieResult ->
            if (popularMovieResult.isSuccess && nowPlayingMovieResult.isSuccess) {
                _uiState.update { uiState ->
                    uiState.copy(
                        isLoading = false,
                        popularMovieData = popularMovieResult.getOrNull()?.movies?.map { it.toUiModel() }
                            ?: listOf(),
                        nowPlayingMovieData = nowPlayingMovieResult.getOrNull()?.movies?.map { it.toUiModel() }
                            ?: listOf()
                    )
                }
            } else {
                _uiState.update { uiState ->
                    uiState.copy(isLoading = false, error = "Hata!")
                }
            }
        }.launchIn(coroutineScope)
    }
}