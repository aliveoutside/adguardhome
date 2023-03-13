package ru.toxyxd.common.ui

import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

enum class WindowSizeClass {
    Compact, Medium, Expanded;

    companion object {
        fun fromWidth(width: Dp): WindowSizeClass {
            return when {
                width < 600.dp -> Compact
                width < 840.dp -> Medium
                else -> Expanded
            }
        }
    }
}

