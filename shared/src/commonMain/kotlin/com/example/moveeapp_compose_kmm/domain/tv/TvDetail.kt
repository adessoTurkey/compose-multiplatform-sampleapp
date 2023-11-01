package com.example.moveeapp_compose_kmm.domain.tv

import com.example.moveeapp_compose_kmm.domain.movie.Credits
import com.example.moveeapp_compose_kmm.ui.scene.tvdetailscreen.model.TvDetailUiModel

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
) {
    fun toUiModel(credit: List<Credits>) = TvDetailUiModel(
        tvSeriesId = tvSeriesId,
        voteAverage = voteAverage,
        title = title,
        posterPath = posterPath,
        numberOfSeasons = numberOfSeasons,
        numberOfEpisodes = numberOfEpisodes,
        overview = overview,
        genre = genre,
        originalLanguage = originalLanguage,
        voteCount = voteCount,
        backdropPath = backdropPath,
        credit = credit,
        homepage = homepage
    )
}