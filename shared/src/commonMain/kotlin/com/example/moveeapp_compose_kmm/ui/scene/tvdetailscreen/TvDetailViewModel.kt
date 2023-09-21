package com.example.moveeapp_compose_kmm.ui.scene.tvdetailscreen

import cafe.adriel.voyager.core.model.coroutineScope
import com.example.moveeapp_compose_kmm.core.SessionSettings
import com.example.moveeapp_compose_kmm.core.ViewModel
import com.example.moveeapp_compose_kmm.data.repository.TvRepository
import com.example.moveeapp_compose_kmm.data.uimodel.tv.TvDetailUiModel
import com.example.moveeapp_compose_kmm.domain.usecase.accountusecase.AddFavoriteUseCase
import com.example.moveeapp_compose_kmm.domain.usecase.accountusecase.GetTvStateUseCase
import com.example.moveeapp_compose_kmm.utils.ShadredPrefConstants
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class TvDetailViewModel(
    private val repository: TvRepository,
    private val sessionSettings: SessionSettings,
    private val addFavoriteUseCase: AddFavoriteUseCase,
    private val getTvStateUseCase: GetTvStateUseCase
) : ViewModel {

    private val _uiState = MutableStateFlow(TvDetailUiState())
    val uiState: StateFlow<TvDetailUiState> = _uiState

    private val _isFavorite = MutableStateFlow(false)
    val isFavorite: StateFlow<Boolean> = _isFavorite

    fun fetchData(tvId: Int) {
        combine(
            repository.getTvDetail(tvId),
            repository.getTvCredit(tvId)
        ) { tvDetailResult, tvCreditResult ->

            if (tvDetailResult.isSuccess && tvCreditResult.isSuccess) {
                _uiState.update { uiState ->
                    uiState.copy(
                        isLoading = false,
                        tvDetailData = tvDetailResult.getOrNull()
                            ?.toUiModel(tvCreditResult.getOrNull()?.cast ?: listOf())
                            ?: TvDetailUiModel()
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

    fun getTvState(tvId: Int) {
        coroutineScope.launch {
            val result = getTvStateUseCase.execute(tvId)
            if (result.isSuccess) {
                _isFavorite.value = result.getOrNull()?.value ?: false
            }
        }
    }
}