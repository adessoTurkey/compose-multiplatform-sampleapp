package com.example.moveeapp_compose_kmm.ui.scene.search

import com.example.moveeapp_compose_kmm.core.ViewModel
import com.example.moveeapp_compose_kmm.core.viewModelScope
import com.example.moveeapp_compose_kmm.domain.search.SearchRepository
import com.example.moveeapp_compose_kmm.ui.scene.search.model.mapper.SearchItemToUiModelMapper
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.mapLatest
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class SearchViewModel(
    private val repository: SearchRepository
) : ViewModel {

    private val _uiState = MutableStateFlow(SearchUiState())
    val uiState: StateFlow<SearchUiState> = _uiState

    private val _query = MutableStateFlow("")
    val query = _query.asStateFlow()


    private val mapper = SearchItemToUiModelMapper()

    @OptIn(FlowPreview::class, ExperimentalCoroutinesApi::class)
    fun makeSearch() {
        viewModelScope.launch {
            _query
                .debounce(500)
                .filter { str ->
                    if (str.isEmpty() || str.length < 3) {
                        _uiState.update {
                            it.removeList()
                        }
                        return@filter false
                    } else {
                        return@filter true
                    }
                }.mapLatest { str ->
                    _uiState.update {
                        it.showLoading()
                    }
                    repository.getSearch(str)
                }.catch { err ->
                    _uiState.update {
                        it.showError(err.message ?: "Something went wrong")
                    }
                }.collect { result ->
                    result.onSuccess { list ->
                        _uiState.update { uiState ->
                            uiState.updateData(list = list.map { mapper.map(it) })
                        }
                    }
                }
        }
    }

    fun handleQueryChange(newQuery: String) {
        _query.value = newQuery
    }
}
