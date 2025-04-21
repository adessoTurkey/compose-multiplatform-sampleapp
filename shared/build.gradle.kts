import com.codingfeline.buildkonfig.compiler.FieldSpec
import movee.util.requireStringProperty

plugins {
    alias(libs.plugins.multiplatform)
    alias(libs.plugins.compose)
    alias(libs.plugins.compose.compiler)
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlinx.serialization)
    alias(libs.plugins.buildKonfig)
}

buildkonfig {
    packageName = "com.example.moveeapp_compose_kmm"

    defaultConfigs {
        buildConfigField(
            FieldSpec.Type.STRING, "API_KEY_TMDB", System.getenv("API_KEY_TMDB"),
            const = true
        )

        buildConfigField(
            FieldSpec.Type.STRING,
            "BASE_URL",
            rootProject.requireStringProperty("BASE_URL"),
            const = true
        )
        buildConfigField(
            FieldSpec.Type.STRING,
            "IMAGE_BASE_URL",
            rootProject.requireStringProperty("IMAGE_BASE_URL"),
            const = true
        )
        buildConfigField(
            FieldSpec.Type.STRING,
            "REGISTER_URL",
            rootProject.requireStringProperty("REGISTER_URL"),
            const = true
        )

        buildConfigField(
            FieldSpec.Type.STRING,
            "RESET_PASSWORD_URL",
            rootProject.requireStringProperty("RESET_PASSWORD_URL"),
            const = true
        )
    }
}

kotlin {
    androidTarget()
    iosArm64 { binaries.framework { baseName = "shared" } }
    iosSimulatorArm64 { binaries.framework { baseName = "shared" } }

    sourceSets {
        all {
            languageSettings.optIn("org.jetbrains.compose.resources.ExperimentalResourceApi")
        }

        commonMain.dependencies {
            // Compose
            api(compose.runtime)
            api(compose.foundation)
            api(compose.material)
            api(compose.material3)
            api(compose.materialIconsExtended)
            api(compose.animation)
            implementation(compose.components.resources)

            implementation(libs.logger)

            // Coroutines
            api(libs.kotlinx.coroutines.core)

            // KotlinX Serialization Json
            api(libs.kotlinx.serialization.json)

            // Ktor
            api(libs.ktor.core)
            api(libs.ktor.json)
            api(libs.ktor.contentNegotiation)
            api(libs.ktor.logging)

            // Koin
            api(libs.koin.core)
            api(libs.koin.test)
            api(libs.koin.compose)

            //Navigation
            api(libs.voyager.navigator)
            api(libs.voyager.koin)
            api(libs.voyager.tabs)
            api(libs.voyager.transitions)

            //Image loader
            api(libs.image.loader)

            //KVault
            api(libs.settings)
        }

        commonTest.dependencies {
            implementation(kotlin("test"))
            implementation(libs.kotlinx.coroutines.test)
        }

        androidMain.dependencies {
            // Ktor
            api(libs.ktor.client.android)

            // Koin
            api(libs.koin.android)

            api(libs.androidx.core)
            api(libs.androidx.appcompat)
            api(libs.androidx.activity.compose)

            api(libs.maps.compose)

            //Location
            api(libs.play.services.location)
        }

        iosMain.dependencies {
            implementation(libs.ktor.client.darwin)
            implementation(libs.nsexceptionKt.core)
        }
    }
}

android {
    namespace = "com.example.moveeapp_compose_kmm"
    compileSdk = libs.versions.compileSdk.get().toInt()

    defaultConfig {
        minSdk = libs.versions.minSdk.get().toInt()
    }
    buildFeatures {
        compose = true
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
}
