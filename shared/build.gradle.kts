import com.example.moveeapp_compose_kmm.Deps

plugins {
    kotlin("multiplatform")
    kotlin("native.cocoapods")
    kotlin("plugin.serialization")
    id("com.android.library")
    id("org.jetbrains.compose")
    id("kotlin-parcelize")
    id("dev.icerock.mobile.multiplatform-resources")
}

@OptIn(org.jetbrains.kotlin.gradle.ExperimentalKotlinGradlePluginApi::class)
kotlin {
    android()
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
            export("dev.icerock.moko:resources:0.23.0") // for moko
            export("dev.icerock.moko:graphics:0.9.0") // for moko
        }
        extraSpecAttributes["resources"] = "['src/commonMain/resources/**', 'src/iosMain/resources/**']"
    }

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

                // Ktor
                with(Deps.Io.Ktor) {
                    api(ktorClientCore)
                    api(ktorSerializationKotlinxJson)
                    api(ktorClientContentNegotiation)
                    api(ktorClientLogging)
                    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.5.1")

                }

                // Logback for ktor logging
                implementation(Deps.Logback.logbackClassic)


                // Koin
                with(Deps.Koin) {
                    api(core)
                    api(test)
                }
                api("io.insert-koin:koin-compose:1.0.4")

                // KotlinX Serialization Json
                implementation(Deps.Org.JetBrains.Kotlinx.kotlinxSerializationJson)

                // Coroutines
                implementation(Deps.Org.JetBrains.Kotlinx.coroutinesCore)

                //Navigation
                with(Deps.Navigation) {
                    api(precompose)
                    api(precomposeViewmodel)
                }

                //image loader
                api("io.github.qdsfdhvh:image-loader:1.5.1")

                //Moko
                api("dev.icerock.moko:resources:0.23.0")
                api("dev.icerock.moko:resources-compose:0.22.0")

                @OptIn(org.jetbrains.compose.ExperimentalComposeLibrary::class)
                implementation(compose.components.resources)
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
                implementation(Deps.Io.Ktor.ktorClientAndroid)

                // Koin
                implementation(Deps.Koin.android)
                api("androidx.activity:activity-compose:1.7.2")
                api("androidx.appcompat:appcompat:1.6.1")
                api("androidx.core:core-ktx:1.10.1")
                implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.5.1")


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
                implementation("io.ktor:ktor-client-darwin:2.3.2")
                implementation("io.ktor:ktor-client-ios:2.3.1")

            }
        }
    }
}

android {
    namespace = "com.example.moveeapp_compose_kmm"
    compileSdk = 33
    defaultConfig {
        minSdk = 24
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    sourceSets["main"].resources.srcDirs("src/commonMain/resources")
}

multiplatformResources {
    multiplatformResourcesPackage = "com.example.moveeapp_compose_kmm"
    multiplatformResourcesClassName = "SharedRes"
}

//dependencies {
//    implementation("androidx.core:core:1.10.1")
//    commonMainApi("dev.icerock.moko:mvvm-core:0.16.1")
//    commonMainApi("dev.icerock.moko:mvvm-compose:0.16.1")
//    commonMainApi("dev.icerock.moko:mvvm-flow:0.16.1")
//    commonMainApi("dev.icerock.moko:mvvm-flow-compose:0.16.1")
//}