package ru.toxyxd.common

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.rounded.Devices
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import moe.tlaster.precompose.navigation.NavOptions
import moe.tlaster.precompose.navigation.Navigator

@OptIn(ExperimentalMaterial3Api::class)
@Composable
actual fun NavigationBar(navigator: Navigator, content: @Composable () -> Unit) {
    val currentRoute = navigator.currentEntry.collectAsState(null).value?.route?.route

    Row {
        if (currentRoute != "/home") {
            PermanentNavigationDrawer(
                modifier = Modifier.width(200.dp),
                drawerContent = {
                    PermanentDrawerSheet {
                        NavigationDrawerItem(
                            icon = { Icon(Icons.Default.Info, "") },
                            onClick = {
                                navigator.navigate(
                                    "/status", NavOptions(
                                        launchSingleTop = true
                                    )
                                )
                            },
                            selected = currentRoute == "/status",
                            label = {
                                Text("Status")
                            },
                            modifier = Modifier.padding(horizontal = 12.dp)
                        )
                        NavigationDrawerItem(
                            icon = { Icon(Icons.Default.List, "") },
                            onClick = { navigator.navigate("/logs") },
                            selected = currentRoute == "/logs",
                            label = { Text("Logs") },
                            modifier = Modifier.padding(horizontal = 12.dp)
                        )
                        NavigationDrawerItem(
                            icon = { Icon(Icons.Rounded.Devices, "") },
                            onClick = { navigator.navigate("/clients") },
                            selected = currentRoute == "/clients",
                            label = { Text("Clients") },
                            modifier = Modifier.padding(horizontal = 12.dp)
                        )
                    }
                }
            ) { }
        }
        content()
    }
}