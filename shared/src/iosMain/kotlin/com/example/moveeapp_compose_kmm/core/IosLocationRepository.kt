package com.example.moveeapp_compose_kmm.core

import com.example.moveeapp_compose_kmm.domain.location.DeviceLocation
import com.example.moveeapp_compose_kmm.domain.location.LocationRepository
import kotlinx.cinterop.ExperimentalForeignApi
import kotlinx.cinterop.useContents
import platform.CoreLocation.CLLocation
import platform.CoreLocation.CLLocationManager
import platform.CoreLocation.CLLocationManagerDelegateProtocol
import platform.CoreLocation.kCLDistanceFilterNone
import platform.CoreLocation.kCLLocationAccuracyBest
import platform.Foundation.NSError
import platform.darwin.NSObject
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException
import kotlin.coroutines.suspendCoroutine

internal class IosLocationRepository : LocationRepository {

    private val locationManager = CLLocationManager()

    @OptIn(ExperimentalForeignApi::class)
    private class LocationDelegate : NSObject(), CLLocationManagerDelegateProtocol {
        var onLocationUpdate: ((DeviceLocation?) -> Unit)? = null

        override fun locationManager(manager: CLLocationManager, didUpdateLocations: List<*>) {
            didUpdateLocations.firstOrNull()?.let {
                val location = it as CLLocation
                location.coordinate.useContents {
                    onLocationUpdate?.invoke(
                        DeviceLocation(
                            latitude = latitude,
                            longitude = longitude,
                        )
                    )
                }
            }
        }

        override fun locationManager(manager: CLLocationManager, didFailWithError: NSError) {
            onLocationUpdate?.invoke(null)
        }
    }

    override suspend fun getCurrentLocation(): DeviceLocation = suspendCoroutine { continuation ->
        locationManager.requestWhenInUseAuthorization()
        locationManager.desiredAccuracy = kCLLocationAccuracyBest
        locationManager.distanceFilter = kCLDistanceFilterNone
        locationManager.startUpdatingLocation()

        val locationDelegate = LocationDelegate()
        locationDelegate.onLocationUpdate = { location ->
            locationManager.stopUpdatingLocation()
            if (location != null) {
                continuation.resume(location)
            } else {
                continuation.resumeWithException(Exception("Unable to get current location"))
            }
        }
        locationManager.delegate = locationDelegate
    }
}
