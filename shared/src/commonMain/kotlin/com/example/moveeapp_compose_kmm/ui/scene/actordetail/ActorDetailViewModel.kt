package com.example.moveeapp_compose_kmm.ui.scene.actordetail

import cafe.adriel.voyager.core.model.coroutineScope
import com.example.moveeapp_compose_kmm.core.ViewModel
import com.example.moveeapp_compose_kmm.data.repository.PersonRepository
import com.example.moveeapp_compose_kmm.data.uimodel.ActorDetailUiModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.update

class ActorDetailViewModel(
    private val repository: PersonRepository
) : ViewModel {

    private val _uiState = MutableStateFlow(ActorDetailUiState())
    val uiState: StateFlow<ActorDetailUiState> = _uiState

    fun fetchData(actorId: Int) {
        combine(
            repository.getPersonDetail(actorId),
            repository.getPersonCredits(actorId)
        ) { personDetailResult, personCreditResult ->

            if (personDetailResult.isSuccess) {
                _uiState.update { uiState ->
                    uiState.copy(
                        isLoading = false,
                        actorDetailData = personDetailResult.getOrNull()
                            ?.toUiModel(credit = personCreditResult.getOrNull()?.cast ?: listOf())
                            ?: ActorDetailUiModel()
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