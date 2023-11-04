package com.example.moveeapp_compose_kmm.domain.search

import kotlinx.coroutines.flow.Flow

interface SearchRepository {
    suspend fun getSearch(query: String): Flow<Result<List<Search>>>
}