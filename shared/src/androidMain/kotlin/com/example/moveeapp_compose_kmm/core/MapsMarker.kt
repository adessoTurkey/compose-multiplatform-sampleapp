package com.example.moveeapp_compose_kmm.core

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.moveeapp_compose_kmm.utils.asAndroidBitmap
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.Marker
import com.google.maps.android.compose.MarkerInfoWindow
import com.google.maps.android.compose.MarkerState
import org.jetbrains.compose.resources.DrawableResource

@Composable
fun MapsMarker(
    position: LatLng,
    iconRes: DrawableResource,
    title: String,
    onClick: (Marker) -> Unit
) {
    val context = LocalContext.current
    val markerState = MarkerState(position = position)
    val icon = BitmapDescriptorFactory.fromBitmap(iconRes.asAndroidBitmap())

    MarkerInfoWindow(
        state = markerState,
        anchor = Offset(0.5f, 0.5f),
        icon = icon,
        title = title,
        snippet = title,
        tag = title,
        onClick = {
            it.showInfoWindow()
            onClick(it)
            true
        },

        ) {
        val color = MaterialTheme.colorScheme.primary

        Column(
            modifier = Modifier.offset(y = (20).dp)
        ) {
            Box(
                modifier = Modifier
                    .wrapContentSize()
                    .background(
                        color = color,
                        shape = RoundedCornerShape(4.dp)
                    )
                    .padding(horizontal = 8.dp, vertical = 5.dp)
            ) {
                Text(
                    color = Color.White,
                    fontSize = 13.sp,
                    fontWeight = FontWeight.Medium,
                    text = it.title.toString(),
                )
            }
            Icon(
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .size(30.dp)
                    .offset(y = (-16).dp),
                imageVector = Icons.Default.ArrowDropDown,
                tint = color,
                contentDescription = null
            )
        }
    }
}

@Composable
fun CurrentLocationMarker(
    position: LatLng,
    iconRes: DrawableResource,
    onClick: (Marker) -> Unit
) {
    val context = LocalContext.current
    val markerState = MarkerState(position = position)
    val icon = BitmapDescriptorFactory.fromBitmap(iconRes.asAndroidBitmap())

    MarkerInfoWindow(
        state = markerState,
        anchor = Offset(0.5f, 0.5f),
        icon = icon,
        onClick = {
            onClick(it)
            true
        },
    )
}
