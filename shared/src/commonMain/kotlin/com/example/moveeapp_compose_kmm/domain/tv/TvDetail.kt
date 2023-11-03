package com.example.moveeapp_compose_kmm.domain.tv

data class TvDetail(
    val tvSeriesId: Int = 0,
    val voteAverage: Double = 0.0,
    val title: String = "",
    val posterPath: String = "",
    val numberOfSeasons: Int = 0,
    val numberOfEpisodes: Int = 0,
    val overview: String = "",
    val genre: String = "",
    val originalLanguage: String = "",
    val voteCount: Int = 0,
    val backdropPath: String = "",
    val homepage: String = ""
)