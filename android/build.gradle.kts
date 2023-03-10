plugins {
    id("org.jetbrains.compose")
    id("com.android.application")
    kotlin("android")
}

group = "ru.toxyxd"
version = "1.0-SNAPSHOT"

dependencies {
    implementation(project(":common"))
}

android {
    compileSdk = 33
    defaultConfig {
        applicationId = "ru.toxyxd.adguardhome"
        minSdk = 24
        targetSdk = 33
        versionCode = 1
        versionName = "1.0-SNAPSHOT"
    }
    buildTypes {
        release {
            isMinifyEnabled = true
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
}