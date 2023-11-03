package com.example.moveeapp_compose_kmm.domain.movie

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
)
