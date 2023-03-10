package ru.toxyxd.common

import androidx.compose.ui.window.application
import androidx.compose.ui.window.rememberWindowState
import moe.tlaster.precompose.PreComposeWindow
import org.koin.core.context.startKoin
import org.koin.core.context.stopKoin
import ru.toxyxd.common.di.setupModules

fun runDesktopApp() {
    startKoin {
        setupModules()
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