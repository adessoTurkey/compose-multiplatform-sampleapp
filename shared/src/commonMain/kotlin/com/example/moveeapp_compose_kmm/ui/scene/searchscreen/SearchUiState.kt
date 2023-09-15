package com.example.moveeapp_compose_kmm.ui.scene.searchscreen

import com.example.moveeapp_compose_kmm.data.uimodel.SearchUiModel

data class SearchUiState(
    val isLoading: Boolean = false,
    val data: List<SearchUiModel> = emptyList(),
    val error: String? = null,
    val emptyState: Boolean = false
) {
    fun removeList() = copy(
        data = listOf(),
        isLoading = false,
        error = null,
        emptyState = false
    )

    fun updateData(list: List<SearchUiModel>) = copy(
        data = list,
        isLoading = false,
        emptyState = list.isEmpty()
    )

    fun showLoading() = copy(
        isLoading = true,
        error = null
    )

    fun showError(message: String) = copy(
        isLoading = false,
        data = emptyList(),
        error = message
    )

}