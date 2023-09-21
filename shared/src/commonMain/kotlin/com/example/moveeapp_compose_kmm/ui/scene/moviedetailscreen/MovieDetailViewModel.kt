package com.example.moveeapp_compose_kmm.ui.scene.moviedetailscreen

import cafe.adriel.voyager.core.model.coroutineScope
import com.example.moveeapp_compose_kmm.core.SessionSettings
import com.example.moveeapp_compose_kmm.core.ViewModel
import com.example.moveeapp_compose_kmm.data.repository.MovieRepository
import com.example.moveeapp_compose_kmm.data.uimodel.MovieDetailUiModel
import com.example.moveeapp_compose_kmm.domain.usecase.accountusecase.AddFavoriteUseCase
import com.example.moveeapp_compose_kmm.domain.usecase.accountusecase.GetMovieStateUseCase
import com.example.moveeapp_compose_kmm.utils.ShadredPrefConstants
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class MovieDetailViewModel(
    private val repository: MovieRepository,
    private val addFavoriteUseCase: AddFavoriteUseCase,
    private val getMovieStateUseCase: GetMovieStateUseCase,
    private val sessionSettings: SessionSettings
) : ViewModel {

    private val _uiState = MutableStateFlow(MovieDetailUiState())
    val uiState: StateFlow<MovieDetailUiState> = _uiState

    private val _isFavorite = MutableStateFlow(false)
    val isFavorite: StateFlow<Boolean> get() = _isFavorite

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

    fun addFavorite(mediaId: Int, mediaType: String, isFavorite: Boolean) {
        coroutineScope.launch {
            val accountId = sessionSettings.getInt(ShadredPrefConstants.KEY_ACCOUNT_ID)
            val result = addFavoriteUseCase.execute(
                accountId = accountId ?: 0,
                mediaId = mediaId,
                mediaType = mediaType,
                isFavorite = isFavorite
            )
            if (result.isSuccess) {
                _isFavorite.value = result.getOrNull()?.value ?: false
            }
        }
    }

    fun getMovieState(mediaId: Int) {
        coroutineScope.launch {
            val result = getMovieStateUseCase.execute(mediaId)
            if (result.isSuccess) {
                _isFavorite.value = result.getOrNull()?.value ?: false
            }
        }
    }
}
