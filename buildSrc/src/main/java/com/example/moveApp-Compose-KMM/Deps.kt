package com.example.moveeapp_compose_kmm

object Deps {
    object Org {
        object JetBrains {
            object Kotlinx {
                const val coroutinesCore = "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Versions.coroutines}"
                const val kotlinxSerializationJson = "org.jetbrains.kotlinx:kotlinx-serialization-json:${Versions.serialization}"
            }
        }
    }

    object Io {
        object Ktor {
            const val ktorClientCore = "io.ktor:ktor-client-core:${Versions.ktor}"
            const val ktorSerializationKotlinxJson = "io.ktor:ktor-serialization-kotlinx-json:${Versions.ktor}"
            const val ktorClientContentNegotiation = "io.ktor:ktor-client-content-negotiation:${Versions.ktor}"
            const val ktorClientLogging = "io.ktor:ktor-client-logging:${Versions.ktor}"
            const val ktorClientAndroid = "io.ktor:ktor-client-android:${Versions.ktor}"
        }
    }

    object Logback {
        const val logbackClassic = "ch.qos.logback:logback-classic:${Versions.logbackClassic}"
    }

    object Koin {
        const val core = "io.insert-koin:koin-core:${Versions.koin}"
        const val test = "io.insert-koin:koin-test:${Versions.koin}"
        const val android = "io.insert-koin:koin-android:${Versions.koin}"
        const val compose = "io.insert-koin:koin-compose:1.0.4"
    }

    object Navigation {
        object Voyager {
            const val navigation = "cafe.adriel.voyager:voyager-navigator:${Versions.voyager}"
            const val viewModel = "cafe.adriel.voyager:voyager-androidx:${Versions.voyager}"
            const val koin = "cafe.adriel.voyager:voyager-koin:${Versions.voyager}"
        }
    }

    object Resources {
        object Moko{
            const val moko = "dev.icerock.moko:resources:0.23.0"
            const val compose = "dev.icerock.moko:resources-compose:0.23.0"
        }
        const val sekio = "io.github.qdsfdhvh:image-loader:${Versions.imageLoader}"
    }

    object Kvault {
        const val Kvault = "com.liftric:kvault:1.10.0"
    }
}