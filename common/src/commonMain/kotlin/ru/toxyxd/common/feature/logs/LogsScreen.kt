package ru.toxyxd.common.feature.logs

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material.pullrefresh.PullRefreshIndicator
import androidx.compose.material.pullrefresh.pullRefresh
import androidx.compose.material.pullrefresh.rememberPullRefreshState
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.unit.dp
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.items
import kotlinx.coroutines.launch
import ru.toxyxd.common.extension.getViewModel
import ru.toxyxd.common.feature.logs.component.LogComponent
import ru.toxyxd.common.uikit.PageLayout

@OptIn(ExperimentalMaterial3Api::class, ExperimentalMaterialApi::class)
@Composable
internal fun LogsScreen() {
    val viewModel = getViewModel<LogsViewModel>()

    val coroutineScope = rememberCoroutineScope()

    val tas = TopAppBarDefaults.exitUntilCollapsedScrollBehavior()

    val lazyListState = rememberLazyListState()
    val scrollButton = remember(lazyListState) {
        derivedStateOf {
            lazyListState.firstVisibleItemIndex > 0
        }
    }

    Scaffold(
        topBar = {
            LargeTopAppBar(
                title = {
                    Text(text = "Logs")
                }, scrollBehavior = tas
            )
        },
        modifier = Modifier
            .nestedScroll(tas.nestedScrollConnection)
            .fillMaxSize(),
        contentWindowInsets = WindowInsets(0.dp)
    ) { paddings ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddings)
        ) {
            PageLayout(state = viewModel.state, onReload = viewModel::reload) { flow ->
                val logs = flow.collectAsLazyPagingItems()
                val swipeRefreshState = rememberPullRefreshState(
                    refreshing = logs.loadState.refresh is LoadState.Loading,
                    onRefresh = { logs.refresh() })

                LazyColumn(
                    verticalArrangement = Arrangement.spacedBy(12.dp),
                    contentPadding = PaddingValues(16.dp),
                    state = lazyListState,
                    modifier = Modifier
                        .fillMaxSize()
                        .pullRefresh(swipeRefreshState)
                        .nestedScroll(tas.nestedScrollConnection)
                ) {
                    items(items = logs) {
                        if (it != null) {
                            LogComponent(log = it)
                        }
                    }
                    when (val state = logs.loadState.append) {
                        is LoadState.NotLoading -> Unit
                        is LoadState.Loading -> item {
                            CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
                        }

                        is LoadState.Error -> item { throw state.error }
                    }
                }
                AnimatedVisibility(
                    visible = scrollButton.value,
                    modifier = Modifier.align(Alignment.BottomEnd),
                    enter = slideInVertically(initialOffsetY = { it }),
                    exit = slideOutVertically(targetOffsetY = { it })
                ) {
                    ScrollToTopButton {
                        coroutineScope.launch {
                            lazyListState.animateScrollToItem(0)
                        }
                    }
                }

                PullRefreshIndicator(
                    refreshing = logs.loadState.refresh is LoadState.Loading,
                    state = swipeRefreshState,
                    modifier = Modifier.align(
                        Alignment.TopCenter
                    )
                )
            }
        }
    }
}

@Composable
private fun ScrollToTopButton(modifier: Modifier = Modifier, onClick: () -> Unit) {
    FloatingActionButton(
        onClick = { onClick() },
        modifier = modifier
            .padding(16.dp)
    ) {
        Icon(
            imageVector = Icons.Default.KeyboardArrowUp, contentDescription = "Scroll to top"
        )
    }
}