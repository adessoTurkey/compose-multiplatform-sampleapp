package com.example.moveeapp_compose_kmm.ui.scene.moviedetailscreen.model

import com.example.moveeapp_compose_kmm.domain.movie.Credits

data class MovieDetailUiModel(
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
    val credit: List<Credits> = listOf(),
    val homepage: String = ""
)
