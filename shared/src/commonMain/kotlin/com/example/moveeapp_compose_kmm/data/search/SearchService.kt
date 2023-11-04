package com.example.moveeapp_compose_kmm.data.search

interface SearchService {
    suspend fun search(query: String): SearchModel
}
