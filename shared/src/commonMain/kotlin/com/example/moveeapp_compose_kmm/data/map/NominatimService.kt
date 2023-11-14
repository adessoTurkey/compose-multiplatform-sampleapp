package com.example.moveeapp_compose_kmm.data.map

interface NominatimService {
    suspend fun getCinemas(latitude: Double, longitude: Double) : List<OSMObject>
}
