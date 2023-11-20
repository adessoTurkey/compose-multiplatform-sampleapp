package com.example.moveeapp_compose_kmm.core

import androidx.compose.runtime.Composable
import com.example.moveeapp_compose_kmm.MR
import dev.icerock.moko.resources.StringResource
import dev.icerock.moko.resources.desc.Resource
import dev.icerock.moko.resources.desc.StringDesc
import dev.icerock.moko.resources.format
import dev.icerock.moko.resources.utils.loadableBundle
import platform.Foundation.NSBundle

@Composable
actual fun getTextOrNull(key: String, vararg args: Any): String? {
    return try {
        val stringResource = StringResource(
            key,
            NSBundle.loadableBundle(MR::class.qualifiedName!!)
        )

        if (args.isEmpty()) {
            StringDesc.Resource(stringResource).localized()
        } else {
            stringResource.format(*args.toList().toTypedArray()).localized()
        }
    } catch (e: Exception) {
        null
    }
}