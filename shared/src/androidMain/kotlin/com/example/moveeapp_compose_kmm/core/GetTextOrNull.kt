package com.example.moveeapp_compose_kmm.core

import android.annotation.SuppressLint
import androidx.compose.runtime.Composable
import dev.icerock.moko.resources.StringResource
import dev.icerock.moko.resources.desc.Resource
import dev.icerock.moko.resources.desc.StringDesc
import dev.icerock.moko.resources.format

@SuppressLint("DiscouragedApi")
@Composable
actual fun getTextOrNull(key: String, vararg args: Any): String? {
    if (AppContext.context == null) return null
    val context = AppContext.context!!

    return try {
        val resourceId = context.resources.getIdentifier(key, "string", context.packageName)
        val stringResource = StringResource(resourceId)

        if (args.isEmpty()) {
            StringDesc.Resource(stringResource).toString(context = context)
        } else {
            stringResource.format(*args).toString(context)
        }
    } catch (e: Exception) {
        null
    }
}