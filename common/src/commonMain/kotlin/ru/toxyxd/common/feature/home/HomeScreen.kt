package ru.toxyxd.common.feature.home

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import org.koin.core.parameter.parametersOf
import ru.toxyxd.common.extension.getViewModel
import ru.toxyxd.common.feature.home.component.Server
import ru.toxyxd.common.uikit.PageLayout

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    onAddClicked: () -> Unit, onServerClicked: () -> Unit
) {
    val viewModel = getViewModel<HomeViewModel> {
        parametersOf()
    }

    Scaffold(topBar = {
        TopAppBar(title = { Text("Servers") }, navigationIcon = {
            IconButton(onClick = { viewModel.reload() }) {
                Icon(
                    imageVector = Icons.Default.Refresh, contentDescription = "Reload"
                )
            }
        })
    }) { paddings ->
        PageLayout(
            state = viewModel.state, onReload = viewModel::reload
        ) { servers ->
            Box(Modifier.padding(paddings).fillMaxSize()) {
                LazyColumn {
                    servers?.forEach { serverModel ->
                        item {
                            Server(name = serverModel.name,
                                address = "${serverModel.host}:${serverModel.port}",
                                onConnectClicked = {
                                    viewModel.setServer(serverModel)
                                    onServerClicked()
                                },
                                onRemoveClicked = { viewModel.removeServer(serverModel); viewModel.reload() })
                        }
                    }
                }
                AddServerButton(modifier = Modifier.align(Alignment.BottomEnd), onClick = onAddClicked)
            }
        }
    }
}

@Composable
fun AddServerButton(modifier: Modifier = Modifier, onClick: () -> Unit) {
    FloatingActionButton(
        onClick = { onClick() }, modifier = modifier.padding(16.dp)
    ) {
        Icon(
            imageVector = Icons.Default.Add, contentDescription = "Add a server"
        )
    }
}