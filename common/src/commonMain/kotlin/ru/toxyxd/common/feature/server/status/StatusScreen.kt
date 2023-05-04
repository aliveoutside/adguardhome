package ru.toxyxd.common.feature.server.status

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.unit.dp
import org.koin.core.parameter.parametersOf
import ru.toxyxd.common.extension.getViewModel
import ru.toxyxd.common.feature.server.status.component.card.StatusCardsGrid
import ru.toxyxd.common.feature.server.status.component.chart.StatusCharts
import ru.toxyxd.common.feature.server.status.component.top.TopCards
import ru.toxyxd.common.ui.component.VerticalScrollbar
import ru.toxyxd.common.uikit.PageLayout

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun StatusScreen() {
    val viewModel = getViewModel<StatusViewModel> { parametersOf() }

    val scrollState = rememberScrollState()
    val tas = TopAppBarDefaults.exitUntilCollapsedScrollBehavior()
    val appBarTitle = remember { mutableStateOf("") }

    Scaffold(
        topBar = {
            LargeTopAppBar(
                title = {
                    Text(text = appBarTitle.value)
                }, scrollBehavior = tas
            )
        }, modifier = Modifier
            .nestedScroll(tas.nestedScrollConnection)
            .fillMaxSize(),
        contentWindowInsets = WindowInsets(0.dp)
    ) { paddings ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddings)
        ) {
            PageLayout(state = viewModel.state, onReload = viewModel::reload) { homePage ->
                appBarTitle.value = homePage.status.dnsAddresses[0]
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .verticalScroll(scrollState)
                        .padding(16.dp)
                ) {
                    StatusCardsGrid(
                        homePage = homePage
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    StatusCharts(
                        homePage
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    TopCards(
                        homePage
                    )
                }
                VerticalScrollbar(
                    modifier = Modifier.align(Alignment.CenterEnd).fillMaxHeight(),
                    scrollState = scrollState,
                )
            }
        }
    }
}