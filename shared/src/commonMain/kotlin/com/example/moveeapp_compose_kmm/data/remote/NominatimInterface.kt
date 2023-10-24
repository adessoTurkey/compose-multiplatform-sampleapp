package com.example.moveeapp_compose_kmm.data.remote

import com.example.moveeapp_compose_kmm.domain.location.OSMObject

interface NominatimInterface {
    suspend fun getCinemas(latitude: Double, longitude: Double) : List<OSMObject>
}
