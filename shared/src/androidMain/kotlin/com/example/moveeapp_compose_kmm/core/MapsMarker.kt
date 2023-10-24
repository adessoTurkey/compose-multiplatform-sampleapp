package com.example.moveeapp_compose_kmm.core

import android.content.Context
import android.graphics.Bitmap
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
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat
import com.google.android.gms.maps.model.BitmapDescriptor
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.Marker
import com.google.maps.android.compose.MarkerInfoWindow
import com.google.maps.android.compose.MarkerState

@Composable
fun MapsMarker(
    position: LatLng,
    iconRes: Int,
    title: String,
    onClick: (Marker) -> Unit
) {
    val context = LocalContext.current
    val markerState = MarkerState(position = position)
    val icon = bitmapDescriptor(context, iconRes)

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
        val color = colorResource(id = androidx.compose.ui.R.color.vector_tint_color)

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
    iconRes: Int,
    onClick: (Marker) -> Unit
) {
    val context = LocalContext.current
    val markerState = MarkerState(position = position)
    val icon = bitmapDescriptor(context, iconRes)

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

fun bitmapDescriptor(
    context: Context,
    vectorResId: Int
): BitmapDescriptor? {

    // retrieve the actual drawable
    val drawable = ContextCompat.getDrawable(context, vectorResId) ?: return null
    drawable.setBounds(0, 0, drawable.intrinsicWidth, drawable.intrinsicHeight)
    val bm = Bitmap.createBitmap(
        drawable.intrinsicWidth,
        drawable.intrinsicHeight,
        Bitmap.Config.ARGB_8888
    )

    // draw it onto the bitmap
    val canvas = android.graphics.Canvas(bm)
    drawable.draw(canvas)
    return BitmapDescriptorFactory.fromBitmap(bm)
}
