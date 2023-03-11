package ru.toxyxd.common.feature.clients

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.pullrefresh.PullRefreshIndicator
import androidx.compose.material.pullrefresh.pullRefresh
import androidx.compose.material.pullrefresh.rememberPullRefreshState
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.unit.dp
import org.koin.core.parameter.parametersOf
import ru.toxyxd.common.extension.getViewModel
import ru.toxyxd.common.feature.clients.component.AutoClient
import ru.toxyxd.common.feature.clients.component.Client
import ru.toxyxd.common.uikit.PageLayout

@OptIn(ExperimentalMaterial3Api::class, ExperimentalMaterialApi::class)
@Composable
internal fun ClientsScreen() {
    val viewModel: ClientsViewModel = getViewModel<ClientsViewModel> { parametersOf() }
    val tas = TopAppBarDefaults.exitUntilCollapsedScrollBehavior()
    val swipeRefreshState =
        rememberPullRefreshState(viewModel.isRefreshing, onRefresh = viewModel::swipeRefreshReload)

    Scaffold(
        topBar = {
            LargeTopAppBar(title = {
                Text(text = "Clients")
            }, scrollBehavior = tas)
        }, modifier = Modifier
            .nestedScroll(tas.nestedScrollConnection)
            .fillMaxSize(),
        contentWindowInsets = WindowInsets(0.dp)
    ) { paddings ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .pullRefresh(swipeRefreshState)
                .padding(paddings)
        ) {
            PageLayout(state = viewModel.state, onReload = viewModel::reload) { clientsPage ->
                Column(Modifier.fillMaxSize()) {
                    LazyColumn(
                        verticalArrangement = Arrangement.spacedBy(12.dp),
                        contentPadding = PaddingValues(16.dp),
                        modifier = Modifier
                            .nestedScroll(tas.nestedScrollConnection)
                    ) {
                        clientsPage.clients.clients?.let {
                            items(it) { client ->
                                Client(client = client)
                            }
                        }
                        item {
                            Text(
                                style = MaterialTheme.typography.headlineMedium,
                                text = "Auto clients",
                            )
                        }
                        items(clientsPage.clients.autoClients) { client ->
                            AutoClient(client = client)
                        }
                    }
                }
            }

            PullRefreshIndicator(
                refreshing = viewModel.isRefreshing,
                state = swipeRefreshState,
                modifier = Modifier.align(
                    Alignment.TopCenter
                )
            )
        }
    }
}