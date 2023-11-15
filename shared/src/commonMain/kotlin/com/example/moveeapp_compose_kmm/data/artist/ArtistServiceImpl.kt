package com.example.moveeapp_compose_kmm.data.artist

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get

class ArtistServiceImpl(private val client: HttpClient): ArtistService {

    override suspend fun artistDetail(personId: Int): ArtistDetailModel {
        return client.get("person/$personId").body()
    }

    override suspend fun artistCredit(personId: Int): ArtistCreditsModel {
        return client.get("person/$personId/combined_credits").body()
    }
}
