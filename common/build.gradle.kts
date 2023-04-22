@Suppress("DSL_SCOPE_VIOLATION")
plugins {
    kotlin("multiplatform")
    id("org.jetbrains.compose")
    id("com.android.library")
    alias(libs.plugins.kotlin.serialization)
    alias(libs.plugins.ksp)
    alias(libs.plugins.sqldelight)
}

group = "ru.toxyxd"
version = "1.0-SNAPSHOT"

@OptIn(org.jetbrains.compose.ExperimentalComposeLibrary::class)
kotlin {
    android()
    jvmToolchain(11)
    jvm("desktop") {
        compilations.all {
            kotlinOptions.jvmTarget = "11"
        }
    }
    sourceSets {
        val commonMain by getting {
            kotlin.srcDir("src/commonMain/third")
            dependencies {
                api(compose.runtime)
                api(compose.foundation)
                api(compose.material)
                api(compose.material3)
                api(compose.materialIconsExtended)
                api(compose.preview)

                api(libs.bundles.precompose)
                implementation(libs.bundles.kotlinx)
                implementation(libs.bundles.androidx)
                implementation(libs.androidx.paging)
                api(libs.koin.core)

                implementation(libs.bundles.ktor)

                implementation(libs.sqldelight.coroutines)
            }
        }
        val androidMain by getting {
            dependencies {
                implementation(libs.kotlinx.coroutines.android)
                implementation(libs.koin.android)
                implementation(libs.sqldelight.android)
                api(libs.accompanistSystemUi)
            }
        }

        val desktopMain by getting {
            dependencies {
                api(libs.kotlinx.coroutines.swing)
                implementation(libs.sqldelight.jvm)
            }
        }
    }
}

dependencies {
    add("kspCommonMainMetadata", libs.compose.precompose.ksp)
    add("kspAndroid", libs.compose.precompose.ksp)
    add("kspDesktop", libs.compose.precompose.ksp)
}

sqldelight {
    databases {
        create("Database") {
            packageName.set("ru.toxyxd")
        }
    }
}

android {
    compileSdk = 33
    sourceSets["main"].manifest.srcFile("src/androidMain/AndroidManifest.xml")
    defaultConfig {
        minSdk = 24
        targetSdk = 33
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    packagingOptions {
        resources {
            excludes.addAll(
                listOf(
                    "META-INF/*",
                    "DebugProbesKt.bin",
                )
            )
        }
    }
}
