package com.example.moveeapp_compose_kmm.data.repository

import com.example.moveeapp_compose_kmm.data.remote.ApiInterface
import com.example.moveeapp_compose_kmm.utils.DataState
import kotlinx.coroutines.flow.flow

class MovieRepository(private val api: ApiInterface) {

    fun popularMovie(page: Int) = flow {
        emit(DataState.Loading)
        try {
            val result = api.popularMovieList(page)
            emit(DataState.Success(result.movies))
        } catch (e: Exception) {
            emit(DataState.Error(e))
        }
    }
}