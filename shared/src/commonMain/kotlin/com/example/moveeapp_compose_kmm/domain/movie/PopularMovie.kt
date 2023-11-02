package com.example.moveeapp_compose_kmm.domain.movie

data class PopularMovie(val movieId: Int,
                        val releaseDate: String?,
                        val voteAverage: Double?,
                        val title: String,
                        val posterPath: String?
)
