package com.example.moveeapp_compose_kmm.core

import com.seiko.imageloader.Image
import com.seiko.imageloader.model.ImageResult

actual fun ImageResult.Image.toImage(): Image = this.image