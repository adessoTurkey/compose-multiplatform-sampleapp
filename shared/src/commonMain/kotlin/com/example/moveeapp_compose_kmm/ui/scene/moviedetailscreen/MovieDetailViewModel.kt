package com.example.moveeapp_compose_kmm.ui.scene.moviedetailscreen

import cafe.adriel.voyager.core.model.coroutineScope
import com.example.moveeapp_compose_kmm.core.SessionSettings
import com.example.moveeapp_compose_kmm.core.ViewModel
import com.example.moveeapp_compose_kmm.data.repository.MovieRepository
import com.example.moveeapp_compose_kmm.data.uimodel.MovieDetailUiModel
import com.example.moveeapp_compose_kmm.domain.usecase.accountusecase.AddFavoriteUseCase
import com.example.moveeapp_compose_kmm.domain.usecase.accountusecase.GetMovieStateUseCase
import com.example.moveeapp_compose_kmm.domain.usecase.accountusecase.rating.RateMovieUseCase
import com.example.moveeapp_compose_kmm.domain.usecase.accountusecase.rating.RemoveMovieRatingUseCase
import com.example.moveeapp_compose_kmm.utils.ShadredPrefConstants
import kotlin.math.roundToInt
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

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

            if (result.isSuccess) _isFavorite.value = isFavorite
        }
    }

    fun getMovieState(mediaId: Int) {
        coroutineScope.launch {
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

        _rateJob.value = coroutineScope.launch {
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
