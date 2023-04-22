import org.jetbrains.compose.desktop.application.dsl.TargetFormat

plugins {
    kotlin("multiplatform")
    id("org.jetbrains.compose")
}

group = "ru.toxyxd"
version = "1.0-SNAPSHOT"


kotlin {
    jvm {
        jvmToolchain(11)
        withJava()
    }
    sourceSets {
        val jvmMain by getting {
            dependencies {
                implementation(project(":common"))
                implementation(compose.desktop.currentOs)
            }
        }
        configurations.all {
            exclude("org.jetbrains.kotlinx", "kotlinx-coroutines-android")
        }
    }
}

compose.desktop {
    application {
        mainClass = "MainKt"
        nativeDistributions {
            targetFormats(TargetFormat.Deb, TargetFormat.AppImage)
            modules("java.sql")
            packageName = "adguardhome"
            packageVersion = "1.0.0"
        }
        buildTypes.release {
            proguard {
               // configurationFiles.from("compose-desktop.pro")
            }
        }
    }
}
