package com.example.moveeapp_compose_kmm.ui.scene.tvdetailscreen

import cafe.adriel.voyager.core.model.coroutineScope
import com.example.moveeapp_compose_kmm.core.ViewModel
import com.example.moveeapp_compose_kmm.data.repository.TvRepository
import com.example.moveeapp_compose_kmm.data.uimodel.tv.TvDetailUiModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.update

class TvDetailViewModel(
    private val repository: TvRepository
) : ViewModel {

    private val _uiState = MutableStateFlow(TvDetailUiState())
    val uiState: StateFlow<TvDetailUiState> = _uiState

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
}