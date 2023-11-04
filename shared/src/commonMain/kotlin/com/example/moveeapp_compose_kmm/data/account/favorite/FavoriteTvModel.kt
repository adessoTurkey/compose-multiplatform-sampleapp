package com.example.moveeapp_compose_kmm.data.account.favorite

import com.example.moveeapp_compose_kmm.domain.account.favorite.FavoriteTv
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class FavoriteTvModel(
    @SerialName("page") val page: Int,
    @SerialName("results") val favTv: List<Result>,
    @SerialName("total_pages") val totalPages: Int,
    @SerialName("total_results") val totalResults: Int
) {
    @Serializable
    data class Result(
        @SerialName("adult") val adult: Boolean,
        @SerialName("backdrop_path") val backdropPath: String,
        @SerialName("first_air_date") val firstAirDate: String,
        @SerialName("genre_ids") val genreIds: List<Int>,
        @SerialName("id") val id: Int,
        @SerialName("name") val name: String,
        @SerialName("origin_country") val originCountry: List<String>,
        @SerialName("original_language") val originalLanguage: String,
        @SerialName("original_name") val originalName: String,
        @SerialName("overview") val overview: String,
        @SerialName("popularity") val popularity: Double,
        @SerialName("poster_path") val posterPath: String,
        @SerialName("vote_average") val voteAverage: Double,
        @SerialName("vote_count") val voteCount: Int
    ) {
        fun toDomain() = FavoriteTv(
            tvId = id,
            voteAverage = voteAverage,
            title = name,
            posterPath = posterPath
        )
    }
}