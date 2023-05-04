package ru.toxyxd.common.feature.server.status.component.top

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import ru.toxyxd.common.ui.component.VerticalScrollbar

@Composable
actual fun TopTableList(elements: List<Map<String, Int>>, total: Int, modifier: Modifier) {
    val scrollState = rememberLazyListState()

    Box(modifier = modifier.height(240.dp)) {
        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(8.dp),
            state = scrollState,
        ) {
            items(elements) {
                TopTableElement(
                    leftText = it.keys.first(),
                    rightText = it.values.first(),
                    total = total,
                )
            }
        }

        VerticalScrollbar(
            modifier = Modifier.align(Alignment.CenterEnd).fillMaxHeight(),
            lazyListState = scrollState,
        )
    }
}