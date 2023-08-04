package com.example.moveeapp_compose_kmm.data.model

import kotlinx.serialization.Serializable

@Serializable
data class PopularMovieModel(
    val page: Int,
    val results: List<PopularMovies>,
    val total_pages: Int,
    val total_results: Int
){
    @Serializable
    data class PopularMovies(
        val adult: Boolean,
        val backdrop_path: String,
        val genre_ids: List<Int>,
        val id: Int,
        val original_language: String,
        val original_title: String,
        val overview: String,
        val popularity: Double,
        val poster_path: String?,
        val release_date: String?,
        val title: String,
        val vote_count: Int,
        val video: Boolean,
        val vote_average: Double?)
//    ) {
//        fun toUiModel() = PopularMovieUiModel(
//            movieId = movieId,
//            title = title,
//            posterPath = posterPath,
//            releaseDate = releaseDate,
//            voteAverage = voteAverage
//        )
//    }
}