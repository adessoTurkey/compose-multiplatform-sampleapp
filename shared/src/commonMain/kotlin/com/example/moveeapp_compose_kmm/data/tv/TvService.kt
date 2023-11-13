package com.example.moveeapp_compose_kmm.data.tv

import com.example.moveeapp_compose_kmm.data.account.favorite.AccountStateResponseModel
import com.example.moveeapp_compose_kmm.data.artist.CreditsModel

interface TvService {

    suspend fun popularTv(): PopularTvModel

    suspend fun topRatedTv(): TopRatedTvModel

    suspend fun tvDetail(tvId: Int): TvDetailModel

    suspend fun tvCredit(tvId: Int): CreditsModel

    suspend fun getTvState(sessionId: String, tvId: Int): AccountStateResponseModel
}
