package com.example.moveeapp_compose_kmm.data.repository

import com.example.moveeapp_compose_kmm.data.remote.ApiImpl
import com.example.moveeapp_compose_kmm.utils.DataState
import kotlinx.coroutines.flow.flow

class MovieRepository {
    private val api = ApiImpl()

    fun popularMovie(page: Int) = flow {
        emit(DataState.Loading)
        try {
            val result = api.popularMovieList(page)
            emit(DataState.Success(result.results))
        } catch (e: Exception) {
            emit(DataState.Error(e))
        }
    }
}