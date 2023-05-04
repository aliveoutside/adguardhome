package ru.toxyxd.common.feature.server.status.component.top

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import ru.toxyxd.common.domain.usecase.server.GetServerHome
import ru.toxyxd.common.ui.LocalSizeClassProvider
import ru.toxyxd.common.ui.WindowSizeClass

@Composable
fun TopCards(homePage: GetServerHome.ServerHomePage) {
    when (LocalSizeClassProvider.current) {
        WindowSizeClass.Compact -> ListCards(homePage)
        else -> TwoByTwoCards(homePage)
    }
}

@Composable
private fun ListCards(homePage: GetServerHome.ServerHomePage) {
    Column(
        verticalArrangement = Arrangement.spacedBy(16.dp),
    ) {
        GeneralStats(homePage.stats)
        TopTable(
            title = "Top clients",
            elements = homePage.stats.topClients,
            total = homePage.stats.numDnsQueries,
            subTitle = "for the last ${homePage.stats.dnsQueries.size} ${homePage.stats.timeUnits}",
        )
        TopTable(
            title = "Top queried domains",
            elements = homePage.stats.topQueriedDomains,
            total = homePage.stats.numDnsQueries,
            subTitle = "for the last ${homePage.stats.dnsQueries.size} ${homePage.stats.timeUnits}",
        )
        TopTable(
            title = "Top blocked domains",
            elements = homePage.stats.topBlockedDomains,
            total = homePage.stats.numDnsQueries,
            subTitle = "for the last ${homePage.stats.dnsQueries.size} ${homePage.stats.timeUnits}",)
    }
}

@Composable
private fun TwoByTwoCards(homePage: GetServerHome.ServerHomePage) {
    Row(
        horizontalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(16.dp),
            modifier = Modifier.weight(1f)
        ) {
            GeneralStats(homePage.stats)
            TopTable(
                title = "Top queried domains",
                elements = homePage.stats.topQueriedDomains,
                total = homePage.stats.numDnsQueries,
                subTitle = "for the last ${homePage.stats.dnsQueries.size} ${homePage.stats.timeUnits}",
            )
        }
        Column(
            verticalArrangement = Arrangement.spacedBy(16.dp),
            modifier = Modifier.weight(1f)
        ) {
            TopTable(
                title = "Top clients",
                subTitle = "for the last ${homePage.stats.dnsQueries.size} ${homePage.stats.timeUnits}",
                elements = homePage.stats.topClients,
                total = homePage.stats.numDnsQueries,
            )
            TopTable(
                title = "Top blocked domains",
                subTitle = "for the last ${homePage.stats.dnsQueries.size} ${homePage.stats.timeUnits}",
                elements = homePage.stats.topBlockedDomains,
                total = homePage.stats.numDnsQueries,
            )
        }
    }
}