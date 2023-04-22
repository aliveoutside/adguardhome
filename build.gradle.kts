group = "ru.toxyxd"
version = "1.0-SNAPSHOT"

allprojects {
    tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile> {
        kotlinOptions {
            jvmTarget = JavaVersion.VERSION_11.toString()

            allWarningsAsErrors = true
            freeCompilerArgs = freeCompilerArgs + listOf(
                "-Xcontext-receivers",
                "-Xskip-prerelease-check"
            )
        }
    }
}

plugins {
    kotlin("multiplatform") apply false
    kotlin("android") apply false
    id("com.android.application") apply false
    id("com.android.library") apply false
    id("org.jetbrains.compose") apply false

    id("com.github.ben-manes.versions") version "0.41.0"
    id("nl.littlerobots.version-catalog-update") version "0.7.0"
}
repositories {
    google()
}

versionCatalogUpdate {
    keep {
        // keep versions without any library or plugin reference
        keepUnusedVersions.set(true)
        // keep all libraries that aren't used in the project
        keepUnusedLibraries.set(true)
        // keep all plugins that aren't used in the project
        keepUnusedPlugins.set(true)
    }
}
