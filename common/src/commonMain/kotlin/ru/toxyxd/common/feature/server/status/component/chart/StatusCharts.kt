package ru.toxyxd.common.feature.server.status.component.chart

import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import ru.toxyxd.common.domain.usecase.server.GetServerHome
import ru.toxyxd.common.ui.LocalSizeClassProvider
import ru.toxyxd.common.ui.WindowSizeClass
import ru.toxyxd.common.ui.component.GrayLineHorizontalSpacer

@Composable
fun StatusCharts(
    homePage: GetServerHome.ServerHomePage,
) {
    when (LocalSizeClassProvider.current) {
        WindowSizeClass.Compact -> ListCharts(homePage)
        WindowSizeClass.Medium -> TwoByTwoCharts(homePage)
        WindowSizeClass.Expanded -> HorizontalListCharts(homePage)
    }
}

@Composable
private fun ListCharts(homePage: GetServerHome.ServerHomePage) {
    Column(
        verticalArrangement = Arrangement.spacedBy(16.dp),
    ) {
        GrayLineHorizontalSpacer()
        StatusChart(
            title = "Dns Queries",
            total = homePage.stats.numDnsQueries,
            color = Color.Blue,
            chartData = homePage.stats.dnsQueries,
            timeUnits = homePage.stats.timeUnits
        )
        GrayLineHorizontalSpacer()
        StatusChart(
            title = "Blocked by filters",
            total = homePage.stats.numBlockedFiltering,
            color = Color.Red,
            chartData = homePage.stats.blockedFiltering,
            timeUnits = homePage.stats.timeUnits
        )
        GrayLineHorizontalSpacer()
        StatusChart(
            title = "Blocked by parental control",
            total = homePage.stats.numReplacedParental,
            color = Color.Green,
            chartData = homePage.stats.replacedParental,
            timeUnits = homePage.stats.timeUnits
        )
        GrayLineHorizontalSpacer()
        StatusChart(
            title = "Blocked by safebrowsing",
            total = homePage.stats.numReplacedSafebrowsing,
            color = Color.Yellow.copy(green = 0.5f),
            chartData = homePage.stats.replacedSafebrowsing,
            timeUnits = homePage.stats.timeUnits
        )
    }
}

@Composable
private fun TwoByTwoCharts(homePage: GetServerHome.ServerHomePage) {
    Row(
        horizontalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(16.dp),
            modifier = Modifier.weight(1f)
        ) {
            GrayLineHorizontalSpacer()
            StatusChart(
                title = "Dns Queries",
                total = homePage.stats.numDnsQueries,
                color = Color.Blue,
                chartData = homePage.stats.dnsQueries,
                timeUnits = homePage.stats.timeUnits
            )
            GrayLineHorizontalSpacer()
            StatusChart(
                title = "Blocked by filters",
                total = homePage.stats.numBlockedFiltering,
                color = Color.Red,
                chartData = homePage.stats.blockedFiltering,
                timeUnits = homePage.stats.timeUnits
            )
        }
        Column(
            verticalArrangement = Arrangement.spacedBy(16.dp),
            modifier = Modifier.weight(1f)
        ) {
            GrayLineHorizontalSpacer()
            StatusChart(
                title = "Blocked by parental control",
                total = homePage.stats.numReplacedParental,
                color = Color.Green,
                chartData = homePage.stats.replacedParental,
                timeUnits = homePage.stats.timeUnits
            )
            GrayLineHorizontalSpacer()
            StatusChart(
                title = "Blocked by safebrowsing",
                total = homePage.stats.numReplacedSafebrowsing,
                color = Color.Yellow.copy(green = 0.5f),
                chartData = homePage.stats.replacedSafebrowsing,
                timeUnits = homePage.stats.timeUnits
            )
        }
    }
}

// TODO: Fix spacers
@Composable
private fun HorizontalListCharts(homePage: GetServerHome.ServerHomePage) {
    Column {
        GrayLineHorizontalSpacer()
        Row(
            horizontalArrangement = Arrangement.spacedBy(16.dp),
        ) {
            StatusChart(
                title = "Dns Queries",
                total = homePage.stats.numDnsQueries,
                color = Color.Blue,
                chartData = homePage.stats.dnsQueries,
                timeUnits = homePage.stats.timeUnits,
                modifier = Modifier.weight(1f)
            )
            StatusChart(
                title = "Blocked by filters",
                total = homePage.stats.numBlockedFiltering,
                color = Color.Red,
                chartData = homePage.stats.blockedFiltering,
                timeUnits = homePage.stats.timeUnits,
                modifier = Modifier.weight(1f)
            )
            StatusChart(
                title = "Blocked by parental control",
                total = homePage.stats.numReplacedParental,
                color = Color.Green,
                chartData = homePage.stats.replacedParental,
                timeUnits = homePage.stats.timeUnits,
                modifier = Modifier.weight(1f)
            )
            StatusChart(
                title = "Blocked by safebrowsing",
                total = homePage.stats.numReplacedSafebrowsing,
                color = Color.Yellow.copy(green = 0.5f),
                chartData = homePage.stats.replacedSafebrowsing,
                timeUnits = homePage.stats.timeUnits,
                modifier = Modifier.weight(1f)
            )
        }
    }
}

@Composable
private fun StatusChart(
    title: String,
    total: Int? = null,
    color: Color,
    chartData: List<Int>,
    timeUnits: String,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.height(200.dp)
    ) {
        Row {
            Text(title, style = MaterialTheme.typography.bodyLarge)
            total?.let {
                Spacer(modifier = Modifier.weight(1f))
                Text(it.toString(), color = color, style = MaterialTheme.typography.bodyLarge)
            }
        }
        Spacer(modifier = Modifier.height(16.dp))
        Box(contentAlignment = Alignment.BottomStart) {
            LineChart(
                data = chartData,
                graphColor = color,
                timeUnits = timeUnits,
                modifier = Modifier.fillMaxSize()
            )
        }
    }
}