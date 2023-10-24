package com.example.moveeapp_compose_kmm.map

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.expandVertically
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.moveeapp_compose_kmm.R
import com.example.moveeapp_compose_kmm.core.CurrentLocationMarker
import com.example.moveeapp_compose_kmm.core.GoogleMapsComponent
import com.example.moveeapp_compose_kmm.core.MapsMarker
import com.example.moveeapp_compose_kmm.domain.location.OSMObject
import com.example.moveeapp_compose_kmm.ui.components.MapsMarkerDialog
import com.example.moveeapp_compose_kmm.ui.scene.map.MapUiState
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.rememberCameraPositionState
import kotlinx.coroutines.launch

@Composable
actual fun Map(modifier: Modifier, uiState: MapUiState, onMarkerClick: (OSMObject?) -> Unit) {
    val coroutineScope = rememberCoroutineScope()
    val cameraPositionState = rememberCameraPositionState()

    LaunchedEffect(uiState.lastLocation) {
        uiState.lastLocation?.let {
            cameraPositionState.position =
                CameraPosition.fromLatLngZoom(LatLng(it.latitude, it.longitude), 10f)
        }
    }

    Box(modifier = modifier) {
        GoogleMapsComponent(
            modifier = Modifier.fillMaxSize(),
            cameraPositionState = cameraPositionState,
            onMapClick = {
                onMarkerClick.invoke(null)
            }) {

            uiState.lastLocation?.let { deviceLocation ->
                CurrentLocationMarker(
                    position = LatLng(deviceLocation.latitude, deviceLocation.longitude),
                    iconRes = R.drawable.ic_maps_marker_user
                ) {
                    coroutineScope.launch {
                        cameraPositionState.animate(
                            update = CameraUpdateFactory.newCameraPosition(
                                CameraPosition(it.position, 12f, 0f, 0f)
                            ),
                            durationMs = 400
                        )
                    }
                }
            }

            uiState.cinemaList?.forEach { cinema ->
                MapsMarker(
                    position = LatLng(cinema.lat, cinema.lon),
                    title = cinema.name,
                    iconRes = R.drawable.ic_maps_marker
                ) {
                    coroutineScope.launch {
                        cameraPositionState.animate(
                            update = CameraUpdateFactory.newCameraPosition(
                                CameraPosition(
                                    it.position,
                                    12f,
                                    0f,
                                    0f
                                )
                            ), durationMs = 400
                        )
                    }
                    onMarkerClick.invoke(cinema)
                }

            }
        }

        AnimatedVisibility(
            visible = uiState.selectedCinema != null,
            enter = expandVertically(),
            exit = shrinkVertically(),
        ) {
            MapsMarkerDialog(
                modifier = Modifier
                    .padding(horizontal = 20.dp, vertical = 18.dp)
                    .align(Alignment.TopCenter),
                title = uiState.selectedCinema?.name ?: "",
                subTitle = uiState.selectedCinema?.displayName ?: ""
            )
        }
    }
}

