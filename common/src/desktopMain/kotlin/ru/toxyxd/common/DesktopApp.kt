package ru.toxyxd.common

import androidx.compose.ui.window.application
import androidx.compose.ui.window.rememberWindowState
import moe.tlaster.precompose.PreComposeWindow
import org.koin.core.context.startKoin
import org.koin.core.context.stopKoin
import ru.toxyxd.common.di.setupCommonModules

fun runDesktopApp() {
    startKoin {
        setupCommonModules()
    }

    application {
        val state = rememberWindowState()
        PreComposeWindow(
            onCloseRequest = {
                stopKoin()
                exitApplication()
            },
            state = state
        ) {
            App()
        }
    }
}