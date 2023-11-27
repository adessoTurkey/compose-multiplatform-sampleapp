package com.example.moveeapp_compose_kmm.data.artist

import com.example.moveeapp_compose_kmm.domain.artist.ArtistCredit
import com.example.moveeapp_compose_kmm.domain.artist.ArtistDetail
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ArtistDetailModel(
    @SerialName("also_known_as") val alsoKnownAs: List<String>,
    @SerialName("adult") val adult: Boolean,
    @SerialName("biography") val biography: String,
    @SerialName("birthday") val birthday: String?,
    @SerialName("gender") val gender: Int,
    @SerialName("id") val id: Int,
    @SerialName("imdb_id") val imdbId: String,
    @SerialName("known_for_department") val knownForDepartment: String,
    @SerialName("name") val name: String,
    @SerialName("place_of_birth") val placeOfBirth: String,
    @SerialName("popularity") val popularity: Double,
    @SerialName("profile_path") val profilePath: String
) {
    fun toDomain() = ArtistDetail(
        name = name,
        biography = biography,
        birthday = birthday.orEmpty(),
        placeOfBirth = placeOfBirth,
        profilePath = profilePath
    )
}

@Serializable
data class ArtistCreditsModel(
    @SerialName("cast") val cast: List<Cast>?=null,
    @SerialName("crew") val crew: List<Crew>?=null,
    @SerialName("id") val id: Int?=null
) {

    @Serializable
    data class Cast(
        @SerialName("adult") val adult: Boolean? = null,
        @SerialName("backdrop_path") val backdropPath: String? = null,
        @SerialName("character") val character: String? = null,
        @SerialName("credit_id") val creditId: String? = null,
        @SerialName("genre_ids") val genreIds: List<Int>? = null,
        @SerialName("id") val id: Int? = null,
        @SerialName("media_type") val mediaType: String? = null,
        @SerialName("order") val order: Int? = null,
        @SerialName("original_language") val originalLanguage: String?= null,
        @SerialName("original_title") val originalTitle: String? = null,
        @SerialName("overview") val overview: String?= null,
        @SerialName("popularity") val popularity: Double? = null,
        @SerialName("poster_path") val posterPath: String? = null,
        @SerialName("release_date") val releaseDate: String?=null,
        @SerialName("title") val title: String?=null,
        @SerialName("video") val video: Boolean?=null,
        @SerialName("vote_average") val voteAverage: Double?=null,
        @SerialName("vote_count") val voteCount: Int?=null
    ) {
        fun toDomain() =
            ArtistCredit(
                id = id ,
                name = originalTitle,
                imagePath = posterPath,
                voteAverage = voteAverage,
                releaseDate = releaseDate,
                mediaType = mediaType
            )
    }

    @Serializable
    data class Crew(
        @SerialName("adult") val adult: Boolean?=null,
        @SerialName("backdrop_path") val backdropPath: String?=null,
        @SerialName("credit_id") val creditId: String?=null,
        @SerialName("department") val department: String?=null,
        @SerialName("genre_ids") val genreIds: List<Int>?=null,
        @SerialName("id") val id: Int?=null,
        @SerialName("job") val job: String?=null,
        @SerialName("media_type") val mediaType: String?=null,
        @SerialName("original_language") val originalLanguage: String?=null,
        @SerialName("original_title") val originalTitle: String?=null,
        @SerialName("overview") val overview: String?=null,
        @SerialName("popularity") val popularity: Double?=null,
        @SerialName("poster_path") val posterPath: String?=null,
        @SerialName("release_date") val releaseDate: String?=null,
        @SerialName("title") val title: String?=null,
        @SerialName("video") val video: Boolean?=null,
        @SerialName("vote_average") val voteAverage: Double?=null,
        @SerialName("vote_count") val voteCount: Int?=null
    )
}
