package ru.toxyxd.common.feature.clients.component

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.*
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun ClientIcon(
    deviceTag: String,
    modifier: Modifier = Modifier
) {
    Icon(
        imageVector = when (deviceTag) {
            "device_audio" -> Icons.Rounded.Headphones
            "device_camera" -> Icons.Rounded.PhotoCamera
            "device_gameconsole" -> Icons.Rounded.VideogameAsset
            "device_laptop" -> Icons.Rounded.Laptop
            "device_nas" -> Icons.Rounded.Storage
            "device_other" -> Icons.Rounded.DeviceUnknown
            "device_pc" -> Icons.Rounded.Computer
            "device_phone" -> Icons.Rounded.Smartphone
            "device_printer" -> Icons.Rounded.Print
            "device_securityalarm" -> Icons.Rounded.Security
            "device_tablet" -> Icons.Rounded.Tablet
            "device_tv" -> Icons.Rounded.Tv
            "os_android" -> Icons.Rounded.Android
            "os_ios" -> Icons.Rounded.IosShare
            "os_linux" -> Icons.Rounded.Computer
            "os_macos" -> Icons.Rounded.LaptopMac
            "os_other" -> Icons.Rounded.DeviceUnknown
            "os_windows" -> Icons.Rounded.LaptopWindows
            "user_admin" -> Icons.Rounded.AdminPanelSettings
            "user_child" -> Icons.Rounded.ChildFriendly
            "user_regular" -> Icons.Rounded.Person
            else -> Icons.Rounded.DeviceUnknown
        },
        contentDescription = deviceTag,
        modifier = modifier
    )
}

