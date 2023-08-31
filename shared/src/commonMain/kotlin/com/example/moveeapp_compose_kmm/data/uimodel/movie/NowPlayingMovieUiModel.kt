package com.example.moveeapp_compose_kmm.data.uimodel.movie

data class NowPlayingMovieUiModel(val movieId: Int,
                                  val releaseDate: String,
                                  val voteAverage: Double,
                                  val title: String,
                                  val posterPath: String
)