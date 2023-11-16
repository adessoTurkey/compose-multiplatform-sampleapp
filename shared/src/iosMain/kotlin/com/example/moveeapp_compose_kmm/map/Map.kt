package com.example.moveeapp_compose_kmm.map

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.interop.UIKitView
import com.example.moveeapp_compose_kmm.MR
import com.example.moveeapp_compose_kmm.domain.location.DeviceLocation
import com.example.moveeapp_compose_kmm.ui.scene.map.Cinema
import com.example.moveeapp_compose_kmm.ui.scene.map.MapUiState
import kotlinx.cinterop.ExperimentalForeignApi
import kotlinx.cinterop.useContents
import platform.CoreLocation.CLLocationCoordinate2DMake
import platform.MapKit.MKAnnotationView
import platform.MapKit.MKCoordinateRegionMakeWithDistance
import platform.MapKit.MKMapView
import platform.MapKit.MKMapViewDelegateProtocol
import platform.MapKit.MKPointAnnotation
import platform.darwin.NSObject

@OptIn(ExperimentalForeignApi::class)
@Composable
actual fun Map(
    modifier: Modifier,
    uiState: MapUiState,
    onMarkerClick: (Cinema?) -> Unit,
    onPositionChange: (DeviceLocation) -> Unit
) {

    var location by remember {
        mutableStateOf(
            CLLocationCoordinate2DMake(
                uiState.lastLocation?.latitude ?: 0.0,
                uiState.lastLocation?.longitude ?: 0.0
            )
        )
    }

    LaunchedEffect(uiState.lastLocation) {
        uiState.lastLocation?.let {
            location = CLLocationCoordinate2DMake(
                uiState.lastLocation.latitude,
                uiState.lastLocation.longitude
            )
        }
    }

    val annotation = remember {
        MKPointAnnotation(
            location,
            title = null,
            subtitle = null
        )
    }

    val isMoved = remember { mutableStateOf(true) }

    val mkMapView = remember {
        MKMapView().apply {
            addAnnotation(annotation)
            setUserInteractionEnabled(true)
            showsUserLocation = true
            pitchEnabled = true
            scrollEnabled = true
            zoomEnabled = true
        }
    }

    val delegate = remember {
        MKDelegate(onMove = { onMove ->
            isMoved.value = onMove
            mkMapView.centerCoordinate.useContents {
                onPositionChange.invoke(DeviceLocation(latitude, longitude))
            }
        }, onAnnotationClicked = { annotationView ->
            if (annotationView != null) {
                val deviceLocation = annotationView.annotation?.coordinate?.useContents {
                    DeviceLocation(latitude, longitude)
                }
                onMarkerClick.invoke(
                    Cinema(
                        annotationView.annotation?.title ?: "",
                        annotationView.annotation?.subtitle ?: "",
                        deviceLocation ?: DeviceLocation(0.0,0.0)
                    )
                )
            } else {
                onMarkerClick(null)
            }
        })
    }

    LaunchedEffect(isMoved) {
        if (isMoved.value) {
            mkMapView.centerCoordinate.useContents {
                onPositionChange.invoke(DeviceLocation(latitude, longitude))
            }
        }
    }

    UIKitView(
        modifier = modifier.fillMaxSize(),
        interactive = true,
        factory = {
            mkMapView
        }, update = { view ->
            mkMapView.setRegion(
                MKCoordinateRegionMakeWithDistance(
                    centerCoordinate = location,
                    10_000.0, 10_000.0
                ),
                animated = true
            )
            mkMapView.setDelegate(delegate)

            val pins = uiState.cinemaList.map { item ->
                val pin = MKPointAnnotation()
                val coordinates = item.location

                pin.setCoordinate(
                    CLLocationCoordinate2DMake(
                        coordinates.latitude,
                        coordinates.longitude
                    )
                )
                pin.setTitle(item.name)
                pin.setSubtitle(item.description)

                var annotationView = mkMapView.dequeueReusableAnnotationViewWithIdentifier("custom")
                if (annotationView == null) {
                    annotationView = MKAnnotationView(pin, "custom")
                }
                annotationView.setImage(MR.images.ic_map.toUIImage())
                annotationView.canShowCallout = true
                annotationView
            }
            mkMapView.addAnnotations(pins)
        }
    )
}
@Suppress("CONFLICTING_OVERLOADS", "PARAMETER_NAME_CHANGED_ON_OVERRIDE")
private class MKDelegate(
    private val onAnnotationClicked: (MKAnnotationView?) -> Unit,
    private val onMove: (Boolean) -> Unit
) : NSObject(), MKMapViewDelegateProtocol {

    override fun mapView(mapView: MKMapView, regionDidChangeAnimated: Boolean) {
        onMove(regionDidChangeAnimated)
    }

    override fun mapView(mapView: MKMapView, didSelectAnnotationView: MKAnnotationView) {
        val annotationView = didSelectAnnotationView.annotation as MKAnnotationView
        onAnnotationClicked(annotationView)
    }

    override fun mapView(mapView: MKMapView, didDeselectAnnotationView: MKAnnotationView) {
        onAnnotationClicked(null)
    }
}
