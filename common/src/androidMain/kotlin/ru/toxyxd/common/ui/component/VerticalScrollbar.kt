package ru.toxyxd.common.ui.component

import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
actual fun VerticalScrollbar(
    modifier: Modifier,
    scrollState: ScrollState
) = Unit

@Composable
actual fun VerticalScrollbar(
    modifier: Modifier,
    lazyListState: LazyListState
) = Unit