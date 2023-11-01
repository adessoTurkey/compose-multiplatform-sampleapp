package com.example.moveeapp_compose_kmm.data.tv

import com.example.moveeapp_compose_kmm.data.remote.model.CreditsModel

interface TvService {
    suspend fun popularTv(): PopularTvModel

    suspend fun topRatedTv(): TopRatedTvModel

    suspend fun tvDetail(tvId: Int): TvDetailModel

    suspend fun tvCredit(tvId: Int): CreditsModel
}