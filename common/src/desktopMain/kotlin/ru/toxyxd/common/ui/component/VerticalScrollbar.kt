package ru.toxyxd.common.ui.component

import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.ScrollbarStyle
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.rememberScrollbarAdapter
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
actual fun VerticalScrollbar(
    modifier: Modifier,
    scrollState: ScrollState
) = androidx.compose.foundation.VerticalScrollbar(
    modifier = modifier,
    adapter = rememberScrollbarAdapter(scrollState),
    style = ScrollbarStyle(
        minimalHeight = 8.dp,
        thickness = 8.dp,
        shape = MaterialTheme.shapes.small,
        unhoverColor = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.12f),
        hoverColor = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.25f),
        hoverDurationMillis = 400,
    )
)

@Composable
actual fun VerticalScrollbar(
    modifier: Modifier,
    lazyListState: LazyListState
) = androidx.compose.foundation.VerticalScrollbar(
    modifier = modifier,
    adapter = rememberScrollbarAdapter(lazyListState),
    style = ScrollbarStyle(
        minimalHeight = 8.dp,
        thickness = 8.dp,
        shape = MaterialTheme.shapes.small,
        unhoverColor = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.12f),
        hoverColor = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.25f),
        hoverDurationMillis = 400,
    )
)