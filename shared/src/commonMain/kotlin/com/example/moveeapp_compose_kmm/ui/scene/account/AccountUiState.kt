package com.example.moveeapp_compose_kmm.ui.scene.account

import com.example.moveeapp_compose_kmm.data.uimodel.account.AccountUiModel
import com.example.moveeapp_compose_kmm.data.uimodel.account.favorite.FavoriteMovieUiModel
import com.example.moveeapp_compose_kmm.data.uimodel.account.favorite.FavoriteTvUiModel

data class AccountUiState(
    val isLoading: Boolean = true,
    val error: String? = null,
    val accountData: AccountUiModel = AccountUiModel()
)

data class FavoriteMovieUiState(
    val isLoading: Boolean = true,
    val error: String? = null,
    val favoriteMovieData: List<FavoriteMovieUiModel> = emptyList()
)

data class FavoriteTvUiState(
    val isLoading: Boolean = true,
    val error: String? = null,
    val favoriteTvData: List<FavoriteTvUiModel> = emptyList()
)

