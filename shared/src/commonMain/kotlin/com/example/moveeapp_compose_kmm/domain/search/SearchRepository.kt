package com.example.moveeapp_compose_kmm.domain.search

interface SearchRepository {
    suspend fun getSearch(query: String): Result<List<SearchItem>>
}