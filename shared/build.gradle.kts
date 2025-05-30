import com.codingfeline.buildkonfig.compiler.FieldSpec
import movee.util.requireStringProperty
import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    alias(libs.plugins.kotlin.multiplatform)
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
    androidTarget {
        compilerOptions {
            jvmTarget.set(JvmTarget.JVM_11)
        }
    }

    iosArm64 { binaries.framework { baseName = "shared" } }
    iosSimulatorArm64 { binaries.framework { baseName = "shared" } }

    jvm()

    sourceSets {
        commonMain.dependencies {
            // Compose
            api(compose.runtime)
            api(compose.foundation)
            api(compose.material3)
            api(compose.materialIconsExtended)
            api(compose.animation)
            api(libs.ui.backhandler)
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

            //Settings
            implementation(libs.settings)
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

            implementation(libs.encryptedprefs)
        }

        iosMain.dependencies {
            implementation(libs.ktor.client.darwin)
        }

        jvmMain.dependencies {
            val osSuffix = getOsSuffix()
            implementation(dependencies.variantOf(libs.javafx.base) { classifier(osSuffix) })
            implementation(dependencies.variantOf(libs.javafx.graphics) { classifier(osSuffix) })
            implementation(dependencies.variantOf(libs.javafx.swing) { classifier(osSuffix) })
            implementation(dependencies.variantOf(libs.javafx.web) { classifier(osSuffix) })
        }
    }
}

android {
    namespace = "com.example.moveeapp_compose_kmm"
    compileSdk = libs.versions.compileSdk.get().toInt()

    defaultConfig {
        minSdk = libs.versions.minSdk.get().toInt()
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
}

private fun getOsSuffix(): String {
    // https://stackoverflow.com/questions/73187027/use-javafx-in-kotlin-multiplatform
    // As JavaFX have platform-specific dependencies, we need to add them manually
    val os = org.gradle.internal.os.OperatingSystem.current()
    val arch = System.getProperty("os.arch")

    return when {
        os.isWindows -> "win"
        os.isMacOsX && arch == "aarch64" -> "mac-aarch64"
        os.isMacOsX -> "mac"
        os.isLinux && arch == "aarch64" -> "linux-aarch64"
        os.isLinux -> "linux"
        else -> throw IllegalStateException("Unknown OS: ${os.name}")
    }
}
