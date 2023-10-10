package com.example.moveeapp_compose_kmm.util

import org.gradle.api.Project

fun Project.requireStringProperty(key: String): String {
    return (properties[key] as? String)!!
}