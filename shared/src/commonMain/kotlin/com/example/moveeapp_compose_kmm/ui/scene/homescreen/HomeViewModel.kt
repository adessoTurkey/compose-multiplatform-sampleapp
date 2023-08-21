package com.example.moveeapp_compose_kmm.ui.scene.homescreen

import cafe.adriel.voyager.core.model.ScreenModel
import cafe.adriel.voyager.core.model.coroutineScope
import com.example.moveeapp_compose_kmm.data.remote.model.PopularMovieModel
import com.example.moveeapp_compose_kmm.data.repository.MovieRepository
import com.example.moveeapp_compose_kmm.utils.DataState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class HomeViewModel(private val repository: MovieRepository) : ScreenModel {
    val popularMovieResponse =
        MutableStateFlow<DataState<List<PopularMovieModel.PopularMovies>>?>(DataState.Loading)

    fun popularMovies(page: Int) {
        coroutineScope.launch(Dispatchers.Main) {
            if (popularMovieResponse.value is DataState.Success) {
                return@launch
            }
            coroutineScope.launch(Dispatchers.Main) {
                repository.popularMovie(page).collectLatest {
                    popularMovieResponse.value = it
                }
            }
        }
    }
}