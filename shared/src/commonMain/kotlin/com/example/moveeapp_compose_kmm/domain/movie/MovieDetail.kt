package com.example.moveeapp_compose_kmm.domain.movie

import com.example.moveeapp_compose_kmm.ui.scene.moviedetailscreen.model.MovieDetailUiModel

data class MovieDetail(
    val movieId: Int = 0,
    val runtime: Int = 0,
    val releaseDate: String = "",
    val voteAverage: Double = 0.0,
    val title: String = "",
    val overview: String = "",
    val posterPath: String = "",
    val genre: String = "",
    val voteCount: Int = 0,
    val backdropPath: String = "",
    val homepage: String = ""
){
    fun toUiModel(credit: List<Credits>) = MovieDetailUiModel(
        movieId = movieId,
        runtime = runtime,
        releaseDate = releaseDate,
        voteAverage = voteAverage,
        title = title,
        overview = overview,
        posterPath = posterPath,
        genre = genre,
        voteCount = voteCount,
        backdropPath = backdropPath,
        credit = credit
    )
}
