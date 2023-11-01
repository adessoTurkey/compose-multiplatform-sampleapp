package com.example.moveeapp_compose_kmm.data.remote.model

import com.example.moveeapp_compose_kmm.domain.movie.Credits
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CreditsModel(
    @SerialName("cast") val cast: List<Cast>,
    @SerialName("crew") val crew: List<Crew>,
    @SerialName("id") val creditID: Int
) {

    @Serializable
    data class Cast(
        @SerialName("adult") val adult: Boolean,
        @SerialName("character") val character: String,
        @SerialName("credit_id") val creditId: String,
        @SerialName("gender") val gender: Int,
        @SerialName("id") val castId: Int,
        @SerialName("known_for_department") val knownForDepartment: String,
        @SerialName("name") val name: String,
        @SerialName("order") val order: Int,
        @SerialName("original_name") val originalName: String,
        @SerialName("popularity") val popularity: Double,
        @SerialName("profile_path") val profilePath: String?
    ) {
        fun toDomain() = Credits(
            castId = castId,
            originalName = originalName,
            profilePath = profilePath ?: ""
        )
    }
    @Serializable
    data class Crew(
        @SerialName("adult") val adult: Boolean,
        @SerialName("credit_id") val creditId: String,
        @SerialName("department") val department: String,
        @SerialName("gender") val gender: Int,
        @SerialName("id") val id: Int,
        @SerialName("job") val job: String,
        @SerialName("known_for_department") val knownForDepartment: String,
        @SerialName("name") val name: String,
        @SerialName("original_name") val originalName: String,
        @SerialName("popularity") val popularity: Double,
        @SerialName("profile_path") val profilePath: String?
    )
}