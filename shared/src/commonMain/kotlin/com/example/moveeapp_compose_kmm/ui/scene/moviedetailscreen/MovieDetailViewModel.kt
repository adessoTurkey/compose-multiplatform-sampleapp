package com.example.moveeapp_compose_kmm.ui.scene.moviedetailscreen

import com.example.moveeapp_compose_kmm.core.ViewModel
import com.example.moveeapp_compose_kmm.core.viewModelScope
import com.example.moveeapp_compose_kmm.domain.account.SessionSettings
import com.example.moveeapp_compose_kmm.domain.movie.MovieDetail
import com.example.moveeapp_compose_kmm.domain.movie.MovieRepository
import com.example.moveeapp_compose_kmm.domain.usecase.accountusecase.AddFavoriteUseCase
import com.example.moveeapp_compose_kmm.domain.usecase.accountusecase.GetMovieStateUseCase
import com.example.moveeapp_compose_kmm.domain.usecase.accountusecase.rating.RateMovieUseCase
import com.example.moveeapp_compose_kmm.domain.usecase.accountusecase.rating.RemoveMovieRatingUseCase
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlin.math.roundToInt

class MovieDetailViewModel(
    private val repository: MovieRepository,
    private val addFavoriteUseCase: AddFavoriteUseCase,
    private val getMovieStateUseCase: GetMovieStateUseCase,
    private val rateMovieUseCase: RateMovieUseCase,
    private val sessionSettings: SessionSettings,
    private val removeMovieRatingUseCase: RemoveMovieRatingUseCase,
) : ViewModel {

    private val _uiState = MutableStateFlow(MovieDetailUiState())
    val uiState: StateFlow<MovieDetailUiState> = _uiState

    private val _isFavorite = MutableStateFlow(false)
    val isFavorite: StateFlow<Boolean> get() = _isFavorite

    private val _uiRating = MutableStateFlow<Int?>(null)
    private val _actualRating = MutableStateFlow<Int?>(null)
    val rating = _uiRating.asStateFlow()

    fun fetchData(movieId: Int) {
        viewModelScope.launch {
            val movieDetailResult = repository.getMovieDetail(movieId)
            val movieCreditResult = repository.getMovieCredits(movieId)

            if (movieDetailResult.isSuccess && movieCreditResult.isSuccess) {
                _uiState.update { uiState ->
                    uiState.copy(
                        isLoading = false,
                        movieDetailData = movieDetailResult.getOrDefault(MovieDetail())
                            .toUiModel(movieCreditResult.getOrDefault(listOf()))
                    )
                }
            } else {
                _uiState.update { uiState ->
                    uiState.copy(isLoading = false, error = "Hata!")
                }
            }
        }
    }

    fun addFavorite(mediaId: Int, mediaType: String, isFavorite: Boolean) {
        viewModelScope.launch {
            val accountId = sessionSettings.getAccountId()

            val result = addFavoriteUseCase.execute(
                accountId = accountId ?: 0,
                mediaId = mediaId,
                mediaType = mediaType,
                isFavorite = isFavorite
            )

            if (result.isSuccess) _isFavorite.value = isFavorite
        }
    }

    fun getMovieState(mediaId: Int) {
        viewModelScope.launch {
            val result = getMovieStateUseCase.execute(mediaId)
            if (result.isSuccess) {
                _isFavorite.value = result.getOrNull()?.isFavorite ?: false
                result.getOrNull()?.rating?.roundToInt().also {
                    _uiRating.value = it
                    _actualRating.value = it
                }
            }
        }
    }

    private val _rateJob = MutableStateFlow<Job?>(null)
    fun rateMovie(rating: Int, movieId: Int) {
        if (_uiRating.value == rating) {
            _uiRating.value = null
        } else {
            _uiRating.value = rating
        }

        _rateJob.value?.cancel()

        if (_rateJob.value?.isActive == true) return

        _rateJob.value = viewModelScope.launch {
            delay(500)

            if (_actualRating.value == rating) {
                removeMovieRatingUseCase.execute(movieId).onSuccess {
                    _actualRating.value = null
                }
            } else {
                val isRateSuccess = rateMovieUseCase.execute(rating, movieId)

                if (isRateSuccess.value) {
                    _actualRating.value = rating
                }
            }

            _uiRating.value = _actualRating.value
        }
    }
}
