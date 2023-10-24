package com.example.moveeapp_compose_kmm.core

import android.annotation.SuppressLint
import android.content.Context
import com.example.moveeapp_compose_kmm.domain.location.DeviceLocation
import com.example.moveeapp_compose_kmm.domain.location.LocationService
import com.google.android.gms.location.LocationServices
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException
import kotlin.coroutines.suspendCoroutine

internal class AndroidLocationService(
    private val context: Context,
) : LocationService {

    private val fusedLocationClient by lazy {
        LocationServices.getFusedLocationProviderClient(context)
    }

    @SuppressLint("MissingPermission")
    override suspend fun getCurrentLocation(): DeviceLocation = suspendCoroutine { continuation ->
        fusedLocationClient.lastLocation
            .addOnSuccessListener { location ->
                if (location == null) return@addOnSuccessListener
                continuation.resume(
                    DeviceLocation(
                        latitude = location.latitude,
                        longitude = location.longitude,
                    ),
                )
            }.addOnFailureListener { exception ->
                continuation.resumeWithException(exception)
            }
    }
}
