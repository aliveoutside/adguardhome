package ru.toxyxd.common

import androidx.compose.runtime.Composable
import moe.tlaster.precompose.navigation.Navigator

@Composable
expect fun NavigationBar(navigator: Navigator, content: @Composable () -> Unit)