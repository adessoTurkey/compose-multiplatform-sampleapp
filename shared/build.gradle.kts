import com.codingfeline.buildkonfig.compiler.FieldSpec
import com.example.moveeapp_compose_kmm.util.requireStringProperty

plugins {
    alias(libs.plugins.multiplatform)
    alias(libs.plugins.compose)
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.cocoapods)
    alias(libs.plugins.kotlinx.serialization)
    alias(libs.plugins.moko.resources)
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

@OptIn(org.jetbrains.kotlin.gradle.ExperimentalKotlinGradlePluginApi::class)
kotlin {
    androidTarget()
    iosX64()
    iosArm64()
    iosSimulatorArm64()

    cocoapods {
        version = "1.0.0"
        summary = "Some description for the Shared Module"
        homepage = "Link to the Shared Module homepage"
        ios.deploymentTarget = "14.1"
        podfile = project.file("../iosApp/Podfile")
        framework {
            baseName = "shared"
            isStatic = true
        }
        extraSpecAttributes["resources"] =
            "['src/commonMain/resources/**', 'src/iosMain/resources/**']"
    }

    jvmToolchain(11)

    sourceSets {
        val commonMain by getting {
            dependencies {

                // Compose
                with(compose) {
                    api(runtime)
                    api(foundation)
                    api(material)
                    api(material3)
                    api(materialIconsExtended)
                    api(animation)
                }

                implementation(libs.logger)

                // Coroutines
                api(libs.kotlinx.coroutines.core)

                // KotlinX Serialization Json
                api(libs.kotlinx.serialization.json)

                // Ktor
                with(libs.ktor) {
                    api(core)
                    api(json)
                    api(contentNegotiation)
                    api(logging)
                    api(logging.logback)
                }

                // Koin
                with(libs.koin) {
                    api(core)
                    api(test)
                    api(compose)
                }

                //Navigation
                with(libs.voyager) {
                    api(navigator)
                    api(koin)
                    api(tabs)
                    api(transitions)
                }

                //Image loader
                api(libs.image.loader)

                //KVault
                api(libs.settings)

                //Moko
                api(libs.moko.resources)
                api(libs.moko.resources.compose)
            }
        }

        val commonTest by getting {
            dependencies {
                implementation(kotlin("test"))
            }
        }

        val androidMain by getting {
            dependsOn(commonMain)

            dependencies {
                // Ktor
                api(libs.ktor.client.android)

                // Koin
                api(libs.koin.android)

                with(libs.androidx) {
                    api(core)
                    api(appcompat)
                    api(activity.compose)
                }

                with(libs.androidx.compose.ui) {
                    api(util)
                    api(tooling)
                    api(preview)
                }
            }
        }
        val iosX64Main by getting
        val iosArm64Main by getting
        val iosSimulatorArm64Main by getting
        val iosMain by creating {
            dependsOn(commonMain)
            iosX64Main.dependsOn(this)
            iosArm64Main.dependsOn(this)
            iosSimulatorArm64Main.dependsOn(this)
            dependencies {
                implementation(libs.ktor.client.darwin)
            }
        }
    }
}

android {
    namespace = "com.example.moveeapp_compose_kmm"
    compileSdk = libs.versions.targetSdk.get().toInt()

    defaultConfig {
        minSdk = libs.versions.minSdk.get().toInt()
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = libs.versions.compose.get()
    }
}

multiplatformResources {
    multiplatformResourcesPackage = "com.example.moveeapp_compose_kmm"
}
