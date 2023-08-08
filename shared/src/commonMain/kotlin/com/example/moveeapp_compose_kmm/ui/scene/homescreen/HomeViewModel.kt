package com.example.moveeapp_compose_kmm.ui.scene.homescreen

import com.example.moveeapp_compose_kmm.data.model.PopularMovieModel
import com.example.moveeapp_compose_kmm.data.repository.MovieRepository
import com.example.moveeapp_compose_kmm.utils.DataState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import moe.tlaster.precompose.viewmodel.ViewModel
import moe.tlaster.precompose.viewmodel.viewModelScope

class HomeViewModel (private val repository: MovieRepository) : ViewModel() {
    val popularMovieResponse =
        MutableStateFlow<DataState<List<PopularMovieModel.PopularMovies>>?>(DataState.Loading)

    fun popularMovies(page: Int) {
        viewModelScope.launch(Dispatchers.Main) {
            repository.popularMovie(page).collectLatest {
                popularMovieResponse.value = it
            }
        }
    }
}