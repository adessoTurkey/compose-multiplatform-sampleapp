package com.example.moveeapp_compose_kmm.ui.scene.movie

import com.example.moveeapp_compose_kmm.core.ViewModel
import com.example.moveeapp_compose_kmm.core.viewModelScope
import com.example.moveeapp_compose_kmm.domain.movie.MovieRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class MovieViewModel(private val repository: MovieRepository) : ViewModel {

    private val _uiState = MutableStateFlow(MovieUiState())
    val uiState: StateFlow<MovieUiState> = _uiState

    init {
        fetchData()
    }

    private fun fetchData() {
        viewModelScope.launch {
            val popularMovieResult = repository.getPopularMovie()
            val nowPlayingMovieResult = repository.getNowPlayingMovie()

            if (popularMovieResult.isSuccess && nowPlayingMovieResult.isSuccess) {
                _uiState.update { uiState ->
                    uiState.copy(
                        isLoading = false,
                        popularMovieData = popularMovieResult.getOrDefault(listOf()),
                        nowPlayingMovieData = nowPlayingMovieResult.getOrDefault(listOf())
                    )
                }
            } else {
                _uiState.update { uiState ->
                    uiState.copy(isLoading = false, error = "Hata!")
                }
            }
        }
    }
}
