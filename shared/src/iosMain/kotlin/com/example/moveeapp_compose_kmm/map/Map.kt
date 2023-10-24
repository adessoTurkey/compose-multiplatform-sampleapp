package com.example.moveeapp_compose_kmm.map

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.interop.UIKitView
import com.example.moveeapp_compose_kmm.domain.location.OSMObject
import com.example.moveeapp_compose_kmm.ui.scene.map.MapUiState
import kotlinx.cinterop.ExperimentalForeignApi
import platform.CoreLocation.CLLocationCoordinate2DMake
import platform.MapKit.MKMapCamera
import platform.MapKit.MKMapView
import platform.MapKit.MKMapViewDelegateProtocol
import platform.darwin.NSObject

@OptIn(ExperimentalForeignApi::class)
@Composable
actual fun Map(
    modifier: Modifier,
    uiState: MapUiState,
    onMarkerClick: (OSMObject?) -> Unit
) {
    val mkMapView = remember { MKMapView().apply {
        setUserInteractionEnabled(true)
    } }

    val mkMapCamera = remember { MKMapCamera().apply {
        setCenterCoordinate(CLLocationCoordinate2DMake(18.982579225106615, -99.09380710785197))
        setAltitude(50000.0)
    } }

    UIKitView(
        modifier = modifier.fillMaxSize(),
        interactive = true,
        factory = {
            mkMapView.delegate = object : NSObject(), MKMapViewDelegateProtocol{

            }
            mkMapView
        }
    )

}
