package ru.toxyxd.common

import androidx.compose.runtime.Composable
import moe.tlaster.precompose.navigation.NavHost
import moe.tlaster.precompose.navigation.rememberNavigator
import ru.toxyxd.common.feature.clients.ClientsScreen
import ru.toxyxd.common.feature.home.HomeScreen
import ru.toxyxd.common.feature.logs.LogsScreen
import ru.toxyxd.common.feature.server.status.StatusScreen
import ru.toxyxd.common.ui.AdGuardTheme

@Composable
fun App() {
    val navigator = rememberNavigator()
    AdGuardTheme {
        NavigationBar(navigator) {
            NavHost(navigator = navigator, initialRoute = "/home") {
                scene("/home") {
                    HomeScreen {
                        navigator.navigate("/status")
                    }
                }
                scene("/status") {
                    StatusScreen()
                }
                scene("/logs") {
                    LogsScreen()
                }
                scene("/clients") {
                    ClientsScreen()
                }
            }
        }
    }
}
