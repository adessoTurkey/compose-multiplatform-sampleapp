package com.example.moveeapp_compose_kmm.data.remote.model.tv

import com.example.moveeapp_compose_kmm.data.uimodel.tv.PopularTvUiModel
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PopularTvModel(
    @SerialName("page") val page: Int,
    @SerialName("results") val tvSeries: List<PopularTv>,
    @SerialName("total_pages") val totalPages: Int,
    @SerialName("total_results") val totalResults: Int
) {
    @Serializable
    data class PopularTv(
        @SerialName("backdrop_path") val backdropPath: String?,
        @SerialName("first_air_date") val firstAirDate: String,
        @SerialName("genre_ids") val genreIds: List<Int>,
        @SerialName("id") val tvSeriesId: Int,
        @SerialName("name") val title: String,
        @SerialName("origin_country") val originCountry: List<String>,
        @SerialName("original_language") val originalLanguage: String,
        @SerialName("original_name") val originalName: String,
        @SerialName("overview") val overview: String,
        @SerialName("popularity") val popularity: Double,
        @SerialName("poster_path") val posterPath: String?,
        @SerialName("vote_average") val voteAverage: Double,
        @SerialName("vote_count") val voteCount: Int
    ) {
        fun toUiModel() = PopularTvUiModel(
            tvSeriesId = tvSeriesId,
            title = title,
            posterPath = posterPath
        )
    }

}