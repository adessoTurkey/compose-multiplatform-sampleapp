package com.example.moveeapp_compose_kmm.ui.scene.moviedetail.model.mapper

import com.example.moveeapp_compose_kmm.domain.artist.Credits
import com.example.moveeapp_compose_kmm.domain.movie.MovieDetail
import com.example.moveeapp_compose_kmm.ui.scene.moviedetail.model.MovieDetailUiModel

class MovieDetailToUiModelMapper {
    fun map(from: MovieDetail, credit: List<Credits>): MovieDetailUiModel {
        with(from) {
            return MovieDetailUiModel(
                movieId = movieId,
                runtime = runtime,
                releaseDate = releaseDate,
                voteAverage = voteAverage,
                title = title,
                overview = overview,
                posterPath = posterPath,
                genre = genre,
                voteCount = voteCount,
                backdropPath = backdropPath,
                credit = credit
            )
        }
    }
}
