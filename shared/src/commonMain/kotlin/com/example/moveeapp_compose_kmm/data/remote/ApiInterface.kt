package com.example.moveeapp_compose_kmm.data.remote

import com.example.moveeapp_compose_kmm.data.model.PopularMovieModel

interface ApiInterface {

    suspend fun popularMovieList(
        page: Int
    ): PopularMovieModel
}