package com.example.moveeapp_compose_kmm.ui.scene.movie

import com.example.moveeapp_compose_kmm.Logger
import com.example.moveeapp_compose_kmm.MR
import com.example.moveeapp_compose_kmm.core.ViewModel
import com.example.moveeapp_compose_kmm.core.key
import com.example.moveeapp_compose_kmm.core.setErrorMessage
import com.example.moveeapp_compose_kmm.core.viewModelScope
import com.example.moveeapp_compose_kmm.domain.movie.MovieRepository
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class MovieViewModel(private val repository: MovieRepository) : ViewModel {
    private val _uiState: MutableStateFlow<MovieUiState> = MutableStateFlow(MovieUiState.Loading)
    val uiState = _uiState.asStateFlow()

    fun fetchData() {
        viewModelScope.launch {
            val deferredPopularMovieResult = async { repository.getPopularMovie() }
            val nowPlayingMovieResult = repository.getNowPlayingMovie()

            val popularMovieResult = deferredPopularMovieResult.await()

            if (popularMovieResult.isSuccess && nowPlayingMovieResult.isSuccess) {
                _uiState.value = MovieUiState.Movies(
                    popularMovies = popularMovieResult.getOrDefault(listOf()),
                    nowPlayingMovies = nowPlayingMovieResult.getOrDefault(listOf())
                )
            } else {
                popularMovieResult.onFailure {
                    Logger.d(message = it.stackTraceToString())
                }

                nowPlayingMovieResult.onFailure {
                    Logger.d(message = it.stackTraceToString())
                }

                setErrorMessage(MR.strings.generic_error_message.key)
            }
        }
    }
}
