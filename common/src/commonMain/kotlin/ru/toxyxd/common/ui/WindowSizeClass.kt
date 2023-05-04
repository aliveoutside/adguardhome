package ru.toxyxd.common.ui

import androidx.compose.runtime.Immutable
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Immutable
enum class WindowSizeClass {
    Compact, Medium, Expanded;

    companion object {
        fun fromWidth(width: Dp): WindowSizeClass {
            require(width > 0.dp) { "Width must be positive" }
            return when {
                width < 600.dp -> Compact
                width < 840.dp -> Medium
                else -> Expanded
            }
        }
    }
}

val LocalSizeClassProvider = compositionLocalOf<WindowSizeClass> { error("No size class provided") }
