package com.example.moveeapp_compose_kmm.ui.scene.tv

import com.example.moveeapp_compose_kmm.core.ViewModel
import com.example.moveeapp_compose_kmm.core.viewModelScope
import com.example.moveeapp_compose_kmm.data.tv.TvRepositoryImpl
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.update

class TvViewModel(private val repository: TvRepositoryImpl) : ViewModel {

    private val _uiState = MutableStateFlow(TvUiState())

    val uiState: StateFlow<TvUiState> = _uiState

    init {
        fetchData()
    }

    private fun fetchData() {
        combine(
            repository.getPopularTv(),
            repository.getTopRatedTv()
        ) { popularTvResult, topRatedTvResult ->
            if (popularTvResult.isSuccess && topRatedTvResult.isSuccess) {
                _uiState.update { uiState ->
                    uiState.copy(
                        isLoading = false,
                        popularTvData = popularTvResult.getOrDefault(listOf()),
                        topRatedTvData = topRatedTvResult.getOrDefault(listOf())
                    )
                }
            } else {
                _uiState.update { uiState ->
                    uiState.copy(
                        isLoading = false,
                        error = "Hata"
                    )
                }
            }
        }.launchIn(viewModelScope)
    }
}
