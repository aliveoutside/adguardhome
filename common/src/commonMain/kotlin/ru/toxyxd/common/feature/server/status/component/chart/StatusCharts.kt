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
import ru.toxyxd.common.ui.component.GrayLineVerticalSpacer

@Composable
fun StatusCharts(
    homePage: GetServerHome.ServerHomePage,
) {
    BoxWithConstraints {
        when {
            maxWidth <= 800.dp -> ListCharts(homePage)
            else -> TwoByTwoCharts(homePage)
        }
    }
}

@Composable
private fun ListCharts(homePage: GetServerHome.ServerHomePage) {
    Column(
        verticalArrangement = Arrangement.spacedBy(16.dp),
    ) {
        GrayLineVerticalSpacer()
        StatusChart(
            title = "Dns Queries",
            total = homePage.stats.numDnsQueries,
            color = Color.Blue,
            chartData = homePage.stats.dnsQueries,
            timeUnits = homePage.stats.timeUnits
        )
        GrayLineVerticalSpacer()
        StatusChart(
            title = "Blocked by filters",
            total = homePage.stats.numBlockedFiltering,
            color = Color.Red,
            chartData = homePage.stats.blockedFiltering,
            timeUnits = homePage.stats.timeUnits
        )
        GrayLineVerticalSpacer()
        StatusChart(
            title = "Blocked by parental control",
            total = homePage.stats.numReplacedParental,
            color = Color.Green,
            chartData = homePage.stats.replacedParental,
            timeUnits = homePage.stats.timeUnits
        )
        GrayLineVerticalSpacer()
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
            GrayLineVerticalSpacer()
            StatusChart(
                title = "Dns Queries",
                total = homePage.stats.numDnsQueries,
                color = Color.Blue,
                chartData = homePage.stats.dnsQueries,
                timeUnits = homePage.stats.timeUnits
            )
            GrayLineVerticalSpacer()
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
            GrayLineVerticalSpacer()
            StatusChart(
                title = "Blocked by parental control",
                total = homePage.stats.numReplacedParental,
                color = Color.Green,
                chartData = homePage.stats.replacedParental,
                timeUnits = homePage.stats.timeUnits
            )
            GrayLineVerticalSpacer()
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

@Composable
private fun StatusChart(
    title: String,
    total: Int? = null,
    color: Color,
    chartData: List<Int>,
    timeUnits: String
) {
    val shouldShowChart = true
    Column(
        modifier = Modifier.height(
            if (shouldShowChart) 200.dp else 20.dp
        ).fillMaxWidth()
    ) {
        Row {
            Text(title, style = MaterialTheme.typography.bodyLarge)
            total?.let {
                Spacer(modifier = Modifier.weight(1f))
                Text(it.toString(), color = color, style = MaterialTheme.typography.bodyLarge)
            }
        }
        if (shouldShowChart) {
            Spacer(modifier = Modifier.height(16.dp))
            Box(contentAlignment = Alignment.BottomStart, modifier = Modifier.weight(1f)) {
                LineChart(
                    data = chartData,
                    graphColor = color,
                    timeUnits = timeUnits,
                    modifier = Modifier.fillMaxSize()
                )
            }
        }
    }
}