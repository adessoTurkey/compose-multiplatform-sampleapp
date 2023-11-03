package com.example.moveeapp_compose_kmm.domain.tv

import com.example.moveeapp_compose_kmm.domain.artist.Credits

interface TvRepository {

    suspend fun getPopularTv(): Result<List<PopularTv>>

    suspend fun getTopRatedTv(): Result<List<TopRatedTv>>

    suspend fun getTvDetail(tvId: Int): Result<TvDetail>

    suspend fun getTvCredits(tvId: Int): Result<List<Credits>>
}
