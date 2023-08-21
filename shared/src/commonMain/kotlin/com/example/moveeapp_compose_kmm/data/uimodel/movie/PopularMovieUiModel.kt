package com.example.moveeapp_compose_kmm.data.uimodel.movie

data class PopularMovieUiModel(val movieId: Int,
                               val releaseDate: String?,
                               val voteAverage: Double?,
                               val title: String,
                               val posterPath: String?
)
