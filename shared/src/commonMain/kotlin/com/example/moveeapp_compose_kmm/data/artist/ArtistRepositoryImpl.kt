package com.example.moveeapp_compose_kmm.data.artist

import com.example.moveeapp_compose_kmm.domain.artist.ArtistCredit
import com.example.moveeapp_compose_kmm.domain.artist.ArtistDetail
import com.example.moveeapp_compose_kmm.domain.artist.ArtistRepository
import com.example.moveeapp_compose_kmm.utils.resultOf

class ArtistRepositoryImpl(
    private val service: ArtistService
) : ArtistRepository {

    override suspend fun getArtistDetail(personId: Int): Result<ArtistDetail> {
        return resultOf {
            service.artistDetail(personId).toDomain()
        }
    }

    override suspend fun getArtistCredits(personId: Int): Result<List<ArtistCredit>> {
        return resultOf {
            service.artistCredit(personId).cast?.map { it.toDomain() } ?: listOf()
        }
    }
}
