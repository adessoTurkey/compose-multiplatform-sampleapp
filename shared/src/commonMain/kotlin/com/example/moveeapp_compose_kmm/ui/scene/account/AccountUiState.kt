package com.example.moveeapp_compose_kmm.ui.scene.account

import com.example.moveeapp_compose_kmm.domain.favorite.FavoriteMovie
import com.example.moveeapp_compose_kmm.domain.favorite.FavoriteTv
import com.example.moveeapp_compose_kmm.domain.account.AccountDetail

data class AccountUiState(
    val isLoading: Boolean = true,
    val error: String? = null,
    val accountData: AccountDetail = AccountDetail()
)

data class FavoriteMovieUiState(
    val isLoading: Boolean = true,
    val error: String? = null,
    val favoriteMovieData: List<FavoriteMovie> = emptyList()
)

data class FavoriteTvUiState(
    val isLoading: Boolean = true,
    val error: String? = null,
    val favoriteTvData: List<FavoriteTv> = emptyList()
)

fun AccountDetail(): AccountDetail = AccountDetail(0, "", "", "")

