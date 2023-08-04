package com.example.moveeapp_compose_kmm.data.remote

import com.example.moveeapp_compose_kmm.data.model.PopularMovieModel
import io.ktor.client.call.body
import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.request.get
import io.ktor.http.encodedPath

class ApiImpl : ApiInterface {

    private fun HttpRequestBuilder.popularMovie(
        page: Int
    ){
        url {
            encodedPath = "3/movie/popular"
            parameters.append("page", page.toString())
        }
    }
    override suspend fun popularMovieList(page: Int): PopularMovieModel {
        return client.get{
            popularMovie(page)
        }.body()
    }
}