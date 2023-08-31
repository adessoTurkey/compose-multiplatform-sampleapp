package com.example.moveeapp_compose_kmm.ui.scene.moviedetailscreen

import cafe.adriel.voyager.core.model.ScreenModel
import cafe.adriel.voyager.core.model.coroutineScope
import com.example.moveeapp_compose_kmm.data.repository.MovieRepository
import com.example.moveeapp_compose_kmm.data.uimodel.MovieDetailUiModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.update

class MovieDetailViewModel(
    private val repository: MovieRepository
) : ScreenModel {

    private val _uiState = MutableStateFlow(MovieDetailUiState())
    val uiState: StateFlow<MovieDetailUiState> = _uiState


    fun fetchData(movieId: Int) {
        combine(
            repository.getMovieDetail(movieId),
            repository.getMovieCredits(movieId)
        ) { movieDetailResult, movieCreditResult ->

            if (movieDetailResult.isSuccess && movieCreditResult.isSuccess) {
                _uiState.update { uiState ->
                    uiState.copy(
                        isLoading = false,
                        movieDetailData = movieDetailResult.getOrNull()
                            ?.toUiModel(movieCreditResult.getOrNull()?.cast ?: listOf())
                            ?: MovieDetailUiModel()
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