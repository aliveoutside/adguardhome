package ru.toxyxd.common.feature.home

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import org.koin.core.parameter.parametersOf
import ru.toxyxd.common.extension.getViewModel
import ru.toxyxd.common.feature.home.component.Server
import ru.toxyxd.common.uikit.PageLayout

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    onServerClicked: () -> Unit
) {
    val viewModel = getViewModel<HomeViewModel> {
        parametersOf()
    }
    Scaffold { paddings ->
        PageLayout(
            state = viewModel.state,
            onReload = viewModel::reload
        ) { servers ->
            Box(Modifier.padding(paddings))
            LazyColumn {
                servers.forEach { serverModel ->
                    item {
                        Server(
                            name = serverModel.name,
                            address = "${serverModel.host}:${serverModel.port}",
                            onConnectClicked = {
                                viewModel.setServer()
                                onServerClicked()
                            }
                        )
                    }
                }
            }

        }
    }
}