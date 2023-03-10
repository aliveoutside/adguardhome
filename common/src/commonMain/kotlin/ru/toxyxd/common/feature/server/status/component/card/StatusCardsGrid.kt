package ru.toxyxd.common.feature.server.status.component.card

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Block
import androidx.compose.material.icons.rounded.Home
import androidx.compose.material.icons.rounded.Search
import androidx.compose.material.icons.rounded.VpnLock
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import ru.toxyxd.common.domain.usecase.server.GetServerHome

@Composable
fun StatusCardsGrid(
    modifier: Modifier = Modifier,
    homePage: GetServerHome.ServerHomePage
) {
    Column(
        modifier = modifier.height(170.dp)
    ) {
        Row(
            modifier = Modifier.weight(0.5f)
        ) {
            StatusCard(
                modifier = Modifier.weight(0.5f),
                icon = {
                    Icon(
                        Icons.Rounded.Home,
                        contentDescription = "Rule filtering"
                    )
                },
                title = "Rule filtering",
                enabled = homePage.filteringStatus.enabled
            )
            Spacer(modifier = Modifier.width(8.dp))
            StatusCard(
                modifier = Modifier.weight(0.5f),
                icon = {
                    Icon(
                        Icons.Rounded.Search,
                        contentDescription = "Safe search"
                    )
                },
                title = "Safe search",
                enabled = homePage.safeSearchStatus.enabled
            )
        }
        Spacer(modifier = Modifier.height(8.dp))
        Row(
            modifier = Modifier.weight(0.5f)
        ) {
            StatusCard(
                modifier = Modifier.weight(0.5f),
                icon = {
                    Icon(
                        Icons.Rounded.Block,
                        contentDescription = "Parental filtering"
                    )
                },
                title = "Parental filtering",
                enabled = homePage.parentalStatus.enabled
            )
            Spacer(modifier = Modifier.width(8.dp))
            StatusCard(
                modifier = Modifier.weight(0.5f),
                icon = {
                    Icon(
                        Icons.Rounded.VpnLock,
                        contentDescription = "Safe browsing"
                    )
                },
                title = "Safe browsing",
                enabled = homePage.safeBrowsingStatus.enabled
            )
        }
    }
}