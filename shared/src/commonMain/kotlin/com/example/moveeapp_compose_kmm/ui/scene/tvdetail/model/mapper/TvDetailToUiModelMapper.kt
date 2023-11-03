package com.example.moveeapp_compose_kmm.ui.scene.tvdetail.model.mapper

import com.example.moveeapp_compose_kmm.domain.artist.Credits
import com.example.moveeapp_compose_kmm.domain.tv.TvDetail
import com.example.moveeapp_compose_kmm.ui.scene.tvdetail.model.TvDetailUiModel

class TvDetailToUiModelMapper {

    fun map(from: TvDetail, credit: List<Credits>): TvDetailUiModel {
        with(from) {
            return TvDetailUiModel(
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
    }
}