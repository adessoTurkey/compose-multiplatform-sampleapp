package com.example.moveeapp_compose_kmm.map

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.moveeapp_compose_kmm.ui.scene.map.Cinema
import com.example.moveeapp_compose_kmm.ui.scene.map.MapUiState

@Composable
expect fun Map(modifier: Modifier, uiState: MapUiState, onMarkerClick : (Cinema?) -> Unit)
