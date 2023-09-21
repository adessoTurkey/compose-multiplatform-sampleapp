package com.example.moveeapp_compose_kmm.ui.scene.account.favoritescreen

import cafe.adriel.voyager.core.model.coroutineScope
import com.example.moveeapp_compose_kmm.core.SessionSettings
import com.example.moveeapp_compose_kmm.core.ViewModel
import com.example.moveeapp_compose_kmm.data.repository.AccountRepository
import com.example.moveeapp_compose_kmm.ui.scene.account.FavoriteMovieUiState
import com.example.moveeapp_compose_kmm.ui.scene.account.FavoriteTvUiState
import com.example.moveeapp_compose_kmm.utils.ShadredPrefConstants
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class FavoriteViewModel(
    private val repository: AccountRepository,
    private val sessionSettings: SessionSettings
) : ViewModel {

    private val _favoriteMovieUiState = MutableStateFlow(FavoriteMovieUiState())
    val favoriteMovieUiState: StateFlow<FavoriteMovieUiState> = _favoriteMovieUiState

    private val _favoriteTvUiState = MutableStateFlow(FavoriteTvUiState())
    val favoriteTvUiState: StateFlow<FavoriteTvUiState> = _favoriteTvUiState


    fun getPopularMovie() {
        coroutineScope.launch {
            val result = repository.getFavoriteMovie(
                accountId = sessionSettings.getInt(ShadredPrefConstants.KEY_ACCOUNT_ID) ?: 0,
                sessionId = sessionSettings.getSessionId() ?: ""
            )
            if (result.isSuccess) {
                _favoriteMovieUiState.update { favoriteMovie ->
                    favoriteMovie.copy(
                        isLoading = false,
                        favoriteMovieData = result.getOrNull()?.favMovies?.map { it.toUiModel() }
                            ?: listOf()
                    )
                }
            }
        }
    }

    fun getPopularTv() {
        coroutineScope.launch {
            val result = repository.getFavoriteTv(
                accountId = sessionSettings.getInt(ShadredPrefConstants.KEY_ACCOUNT_ID) ?: 0,
                sessionId = sessionSettings.getSessionId() ?: ""
            )
            if (result.isSuccess) {
                _favoriteTvUiState.update { favoriteMovie ->
                    favoriteMovie.copy(
                        isLoading = false,
                        favoriteTvData = result.getOrNull()?.favTv?.map { it.toUiModel() }
                            ?: listOf()
                    )
                }
            }
        }
    }
}