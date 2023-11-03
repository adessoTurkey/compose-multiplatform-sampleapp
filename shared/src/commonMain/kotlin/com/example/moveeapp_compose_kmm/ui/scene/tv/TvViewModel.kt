package com.example.moveeapp_compose_kmm.ui.scene.tv

import com.example.moveeapp_compose_kmm.core.ViewModel
import com.example.moveeapp_compose_kmm.core.viewModelScope
import com.example.moveeapp_compose_kmm.domain.tv.TvRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class TvViewModel(private val repository: TvRepository) : ViewModel {

    private val _uiState = MutableStateFlow(TvUiState())

    val uiState: StateFlow<TvUiState> = _uiState

    init {
        fetchData()
    }

    private fun fetchData() {
        viewModelScope.launch {
            val popularTvResult = repository.getPopularTv()
            val topRatedTvResult = repository.getTopRatedTv()
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
        }
    }
}
