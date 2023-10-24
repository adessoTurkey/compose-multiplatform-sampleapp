package com.example.moveeapp_compose_kmm.ui.scene.map

import com.example.moveeapp_compose_kmm.domain.location.DeviceLocation
import com.example.moveeapp_compose_kmm.domain.location.OSMObject

data class MapUiState(
    val lastLocation: DeviceLocation? = null,
    val cinemaList: List<OSMObject>? = null,
    val selectedCinema: OSMObject? = null,
    val dialogVisibility: Boolean = false,
    val searchVisibility: Boolean = false,
)
