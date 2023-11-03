package com.example.moveeapp_compose_kmm.map

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
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

    val location = CLLocationCoordinate2DMake(
        uiState.lastLocation?.latitude ?: 0.0,
        uiState.lastLocation?.longitude ?: 0.0
    )
    val annotation = remember {
        MKPointAnnotation(
            location,
            title = null,
            subtitle = null
        )
    }

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
        MKDelegate { annotation ->
            if (annotation != null) {
                val deviceLocation = annotation.coordinate.useContents {
                    DeviceLocation(latitude, longitude)
                }
                onMarkerClick.invoke(
                    Cinema(
                        annotation.title ?: "",
                        annotation.subtitle ?: "",
                        deviceLocation
                    )
                )
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
                val annotationView = mkMapView.dequeueReusableAnnotationViewWithIdentifier("custom")
                    ?: MKAnnotationView(pin, "custom")

                annotationView.setAnnotation(pin)
                annotationView.setImage(MR.images.ic_heart_filled.toUIImage())
                val coordinates = item.location

                pin.setCoordinate(
                    CLLocationCoordinate2DMake(
                        coordinates.latitude ?: 0.0,
                        coordinates.longitude ?: 0.0
                    )
                )

                pin.setTitle(item.name)
                pin.setSubtitle(item.description)
                pin
            }
            mkMapView.addAnnotations(pins)
        }
    )
}

private class MKDelegate(
    private val onAnnotationClicked: (MKPointAnnotation?) -> Unit
) : NSObject(), MKMapViewDelegateProtocol {
    override fun mapView(mapView: MKMapView, didSelectAnnotationView: MKAnnotationView) {
        val annotation = didSelectAnnotationView.annotation as MKPointAnnotation
        onAnnotationClicked(annotation)
    }
}
