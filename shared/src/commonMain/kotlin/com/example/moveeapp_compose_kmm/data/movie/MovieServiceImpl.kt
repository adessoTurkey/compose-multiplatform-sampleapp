package com.example.moveeapp_compose_kmm.data.movie

import com.example.moveeapp_compose_kmm.data.remote.model.CreditsModel
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get

class MovieServiceImpl(
    private val client: HttpClient,
) : MovieService {

    override suspend fun popularMovie(): PopularMovieModel {
        return client.get(POPULAR_MOVIE).body()
    }

    override suspend fun nowPlayingMovie(): NowPlayingMovieModel {
        return client.get(NOW_PLAYING_MOVIE).body()
    }

    override suspend fun movieDetail(movieId: Int): MovieDetailModel {
        return client.get("movie/$movieId").body()
    }

    override suspend fun movieCredit(movieId: Int): CreditsModel {
        return client.get("movie/$movieId/credits").body()
    }

    companion object {
        const val POPULAR_MOVIE = "movie/popular"
        const val NOW_PLAYING_MOVIE = "movie/now_playing"
    }
}
