package com.example.moveeapp_compose_kmm.data.uimodel.account.favorite

data class FavoriteMovieUiModel(
    val movieId: Int = 0,
    val releaseDate: String = "",
    val voteAverage: Double = 0.0,
    val title: String = "",
    val posterPath: String = ""
)