package com.example.moveeapp_compose_kmm.domain.tv

import com.example.moveeapp_compose_kmm.domain.movie.Credits
import kotlinx.coroutines.flow.Flow

interface TvRepository {
    fun getPopularTv(): Flow<Result<List<PopularTv>>>

    fun getTopRatedTv(): Flow<Result<List<TopRatedTv>>>

    fun getTvDetail(tvId: Int): Flow<Result<TvDetail>>

    fun getTvCredits(tvId: Int): Flow<Result<List<Credits>>>
}