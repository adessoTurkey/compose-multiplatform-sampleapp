package com.example.moveeapp_compose_kmm.domain.tv

data class TopRatedTv(
    val tvId: Int,
    val voteAverage: Double,
    val title: String,
    val posterPath: String,
)