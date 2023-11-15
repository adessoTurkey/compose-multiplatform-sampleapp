package com.example.moveeapp_compose_kmm.ui.scene.artistdetail

import com.example.moveeapp_compose_kmm.core.ViewModel
import com.example.moveeapp_compose_kmm.core.viewModelScope
import com.example.moveeapp_compose_kmm.domain.artist.ArtistDetail
import com.example.moveeapp_compose_kmm.domain.artist.ArtistRepository
import com.example.moveeapp_compose_kmm.ui.scene.artistdetail.model.mapper.ArtistDetailToUiModelMapper
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class ArtistDetailViewModel(
    private val repository: ArtistRepository
) : ViewModel {

    private val _uiState = MutableStateFlow(ArtistDetailUiState())
    val uiState: StateFlow<ArtistDetailUiState> = _uiState

    private val mapper = ArtistDetailToUiModelMapper()

    fun fetchData(actorId: Int) {
        viewModelScope.launch {
            val artistDetailResult = repository.getArtistDetail(actorId)
            val artistCreditResult = repository.getArtistCredits(actorId)

            if (artistDetailResult.isSuccess && artistCreditResult.isSuccess) {
                _uiState.update { uiState ->
                    uiState.copy(
                        isLoading = false,
                        artistDetailData = mapper.map(
                            from = artistDetailResult.getOrDefault(ArtistDetail()),
                            credit = artistCreditResult.getOrDefault(listOf())
                        )
                    )
                }
            } else {
                _uiState.update { uiState ->
                    uiState.copy(isLoading = false, error = "Hata!")
                }
            }
        }
    }
}
