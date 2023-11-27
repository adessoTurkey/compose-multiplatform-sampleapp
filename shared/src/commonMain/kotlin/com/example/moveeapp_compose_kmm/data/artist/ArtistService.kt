package com.example.moveeapp_compose_kmm.data.artist

interface ArtistService {

    suspend fun artistDetail(personId: Int): ArtistDetailModel

    suspend fun artistCredit(personId: Int): ArtistCreditsModel
}
