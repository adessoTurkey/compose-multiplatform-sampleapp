package com.example.moveeapp_compose_kmm.core

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import com.example.moveeapp_compose_kmm.R
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MapStyleOptions
import com.google.maps.android.compose.CameraPositionState
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.GoogleMapComposable
import com.google.maps.android.compose.MapProperties
import com.google.maps.android.compose.MapUiSettings

@Composable
fun GoogleMapsComponent(
    modifier: Modifier = Modifier,
    cameraPositionState: CameraPositionState,
    onMapClick: (LatLng) -> Unit,
    content: (@Composable @GoogleMapComposable () -> Unit)?,
) {
    val context = LocalContext.current
    val mapProperties by remember {
        mutableStateOf(
            MapProperties(
                maxZoomPreference = 18f,
                minZoomPreference = 5f,
                isMyLocationEnabled = true,
                mapStyleOptions = MapStyleOptions.loadRawResourceStyle(
                    context,
                    R.raw.google_map_style
                )
            )
        )
    }
    val mapUiSettings by remember {
        mutableStateOf(
            MapUiSettings(
                mapToolbarEnabled = true,
                zoomControlsEnabled = true,
                myLocationButtonEnabled = true
            )
        )
    }

    GoogleMap(
        modifier = modifier,
        properties = mapProperties,
        uiSettings = mapUiSettings,
        cameraPositionState = cameraPositionState,
        onMapClick = onMapClick,
        content = content
    )
}