package com.example.moveeapp_compose_kmm.data.tv

import com.example.moveeapp_compose_kmm.data.artist.CreditsModel
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get

class TvServiceImpl(private val client: HttpClient) : TvService {

    //Tv
    override suspend fun popularTv(): PopularTvModel {
        return client.get(POPULAR_TV).body()
    }

    override suspend fun topRatedTv(): TopRatedTvModel {
        return client.get(TOP_RATED_TV).body()
    }

    override suspend fun tvDetail(tvId: Int): TvDetailModel {
        return client.get("tv/$tvId").body()
    }

    override suspend fun tvCredit(tvId: Int): CreditsModel {
        return client.get("tv/$tvId/credits").body()
    }

    companion object {
        const val POPULAR_TV = "tv/popular"
        const val TOP_RATED_TV = "tv/top_rated"
    }
}