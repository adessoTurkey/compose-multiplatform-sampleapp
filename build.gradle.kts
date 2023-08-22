
plugins {
    id("com.android.application").version("8.0.2").apply(false)
    id("com.android.library").version("8.0.2").apply(false)
    kotlin("android").version("1.9.0").apply(false)
    kotlin("multiplatform").version("1.9.0").apply(false)
    id("org.jetbrains.compose") version "1.4.3" apply false
    id("org.jetbrains.kotlin.plugin.serialization") version "1.8.20"
    id("dev.icerock.mobile.multiplatform-resources") version "0.23.0" apply false
}

tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}

allprojects {
    repositories {
        google()
        mavenCentral()
        maven("https://maven.pkg.jetbrains.space/public/p/compose/dev")
        maven(  "https://kotlin.bintray.com/kotlinx")
    }
}
