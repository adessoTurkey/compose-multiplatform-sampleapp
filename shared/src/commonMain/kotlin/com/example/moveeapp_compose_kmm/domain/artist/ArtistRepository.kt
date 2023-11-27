package com.example.moveeapp_compose_kmm.domain.artist

interface ArtistRepository {

    suspend fun getArtistDetail(personId: Int): Result<ArtistDetail>

    suspend fun getArtistCredits(personId: Int): Result<List<ArtistCredit>>
}
