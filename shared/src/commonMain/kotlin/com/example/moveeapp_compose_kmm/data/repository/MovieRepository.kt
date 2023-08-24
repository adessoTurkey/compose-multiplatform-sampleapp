package com.example.moveeapp_compose_kmm.data.repository

import com.example.moveeapp_compose_kmm.data.remote.ApiInterface
import com.example.moveeapp_compose_kmm.utils.resultOf
import kotlinx.coroutines.flow.flow

class MovieRepository(private val api: ApiInterface) {

    fun getPopularMovie() = flow {
        emit(
            resultOf {
                api.popularMovie()
            }
        )
    }

    fun getNowPlayingMovie() = flow {
        emit(
            resultOf {
                api.nowPlayingMovie()
            }
        )
    }
}