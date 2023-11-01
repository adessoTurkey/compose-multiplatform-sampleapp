package com.example.moveeapp_compose_kmm.ui.scene.tvdetailscreen

import com.example.moveeapp_compose_kmm.core.ViewModel
import com.example.moveeapp_compose_kmm.core.viewModelScope
import com.example.moveeapp_compose_kmm.data.tv.TvRepositoryImpl
import com.example.moveeapp_compose_kmm.domain.account.SessionSettings
import com.example.moveeapp_compose_kmm.domain.tv.TvDetail
import com.example.moveeapp_compose_kmm.domain.usecase.accountusecase.AddFavoriteUseCase
import com.example.moveeapp_compose_kmm.domain.usecase.accountusecase.GetTvStateUseCase
import com.example.moveeapp_compose_kmm.domain.usecase.accountusecase.rating.RateTvShowUseCase
import com.example.moveeapp_compose_kmm.domain.usecase.accountusecase.rating.RemoveTvShowRatingUseCase
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlin.math.roundToInt

class TvDetailViewModel(
    private val repository: TvRepositoryImpl,
    private val sessionSettings: SessionSettings,
    private val addFavoriteUseCase: AddFavoriteUseCase,
    private val getTvStateUseCase: GetTvStateUseCase,
    private val rateTvShowUseCase: RateTvShowUseCase,
    private val removeTvShowRatingUseCase: RemoveTvShowRatingUseCase,
) : ViewModel {

    private val _uiState = MutableStateFlow(TvDetailUiState())
    val uiState: StateFlow<TvDetailUiState> = _uiState

    private val _isFavorite = MutableStateFlow(false)
    val isFavorite: StateFlow<Boolean> = _isFavorite

    private val _uiRating = MutableStateFlow<Int?>(null)
    private val _actualRating = MutableStateFlow<Int?>(null)
    val rating = _uiRating.asStateFlow()

    fun fetchData(tvId: Int) {
        combine(
            repository.getTvDetail(tvId),
            repository.getTvCredits(tvId)
        ) { tvDetailResult, tvCreditResult ->

            if (tvDetailResult.isSuccess && tvCreditResult.isSuccess) {
                _uiState.update { uiState ->
                    uiState.copy(
                        isLoading = false,
                        tvDetailData = tvDetailResult.getOrDefault(TvDetail())
                            .toUiModel(tvCreditResult.getOrDefault(listOf()))
                    )
                }
            } else {
                _uiState.update { uiState ->
                    uiState.copy(isLoading = false, error = "Hata!")
                }
            }
        }.launchIn(viewModelScope)
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

    fun getTvState(tvId: Int) {
        viewModelScope.launch {
            val result = getTvStateUseCase.execute(tvId)

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
    fun rateTvShow(rating: Int, tvShowId: Int) {
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
                removeTvShowRatingUseCase.execute(tvShowId).onSuccess {
                    _actualRating.value = null
                }
            } else {
                val isRateSuccess = rateTvShowUseCase.execute(rating, tvShowId)

                if (isRateSuccess.value) {
                    _actualRating.value = rating
                }
            }

            _uiRating.value = _actualRating.value
        }
    }
}
