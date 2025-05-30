package com.example.moveeapp_compose_kmm.map

import androidx.compose.foundation.layout.Box
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.example.moveeapp_compose_kmm.domain.location.DeviceLocation
import com.example.moveeapp_compose_kmm.ui.scene.map.Cinema
import com.example.moveeapp_compose_kmm.ui.scene.map.MapUiState

@Composable
actual fun Map(
    modifier: Modifier,
    uiState: MapUiState,
    onMarkerClick: (Cinema?) -> Unit,
    onPositionChange: (DeviceLocation) -> Unit
) {
    Box(modifier) {
        Text("Maps Content", Modifier.align(Alignment.Center))
    }
}
