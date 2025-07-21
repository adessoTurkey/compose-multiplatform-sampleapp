package com.example.moveeapp_compose_kmm.map

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.viewinterop.UIKitInteropProperties
import androidx.compose.ui.viewinterop.UIKitView
import com.example.moveeapp_compose_kmm.domain.location.DeviceLocation
import com.example.moveeapp_compose_kmm.ui.scene.map.Cinema
import com.example.moveeapp_compose_kmm.ui.scene.map.MapUiState
import com.example.moveeapp_compose_kmm.utils.asUiImage
import kotlinx.cinterop.ExperimentalForeignApi
import kotlinx.cinterop.ObjCSignatureOverride
import kotlinx.cinterop.useContents
import movee.shared.generated.resources.Res
import movee.shared.generated.resources.ic_maps_marker
import movee.shared.generated.resources.ic_maps_marker_user
import platform.CoreLocation.CLLocationCoordinate2DMake
import platform.MapKit.MKAnnotationProtocol
import platform.MapKit.MKAnnotationView
import platform.MapKit.MKCoordinateRegionMakeWithDistance
import platform.MapKit.MKMapView
import platform.MapKit.MKMapViewDelegateProtocol
import platform.MapKit.MKPointAnnotation
import platform.MapKit.MKUserLocation
import platform.UIKit.UIImage
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

    val userLocation = Res.drawable.ic_maps_marker_user.asUiImage()
    val marker = Res.drawable.ic_maps_marker.asUiImage()

    val delegate = remember {
        MKDelegate(
            userLocation,
            marker,
            onMove = { onMove ->
                isMoved.value = onMove
                mkMapView.centerCoordinate.useContents {
                    onPositionChange.invoke(DeviceLocation(latitude, longitude))
                }
            }, onAnnotationClicked = { annotation ->
                if (annotation != null) {
                    val deviceLocation = annotation.coordinate.useContents {
                        DeviceLocation(latitude, longitude)
                    }
                    onMarkerClick.invoke(
                        Cinema(
                            annotation.title.orEmpty(),
                            annotation.subtitle.orEmpty(),
                            deviceLocation
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
        modifier = modifier,
        properties = UIKitInteropProperties(
            isInteractive = true,
            isNativeAccessibilityEnabled = true
        ),
        factory = { mkMapView },
        update = { view ->
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
                pin
            }
            mkMapView.addAnnotations(pins)
        }
    )
}

private class MKDelegate(
    private val userLocationImage: UIImage?,
    private val markerImage: UIImage?,
    private val onAnnotationClicked: (MKAnnotationProtocol?) -> Unit,
    private val onMove: (Boolean) -> Unit
) : NSObject(), MKMapViewDelegateProtocol {

    override fun mapView(mapView: MKMapView, regionDidChangeAnimated: Boolean) {
        onMove(regionDidChangeAnimated)
    }

    override fun mapView(
        mapView: MKMapView,
        viewForAnnotation: MKAnnotationProtocol
    ): MKAnnotationView {
        return if (viewForAnnotation is MKUserLocation) {
            mapView.getOrCreateAnnotation(viewForAnnotation, "user").apply {
                image = userLocationImage
            }
        } else {
            mapView.getOrCreateAnnotation(viewForAnnotation, "custom").apply {
                image = markerImage
                canShowCallout = false
            }
        }
    }

    @ObjCSignatureOverride
    override fun mapView(mapView: MKMapView, didSelectAnnotationView: MKAnnotationView) {
        if (didSelectAnnotationView.annotation !is MKUserLocation)
            onAnnotationClicked(didSelectAnnotationView.annotation)
    }

    @ObjCSignatureOverride
    override fun mapView(mapView: MKMapView, didDeselectAnnotationView: MKAnnotationView) {
        onAnnotationClicked(null)
    }

    private fun MKMapView.getOrCreateAnnotation(
        viewForAnnotation: MKAnnotationProtocol,
        identifier: String
    ): MKAnnotationView {
        return dequeueReusableAnnotationViewWithIdentifier(identifier)
            ?: MKAnnotationView(viewForAnnotation, identifier)
    }
}
