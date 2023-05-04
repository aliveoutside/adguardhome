package ru.toxyxd.common.feature.server.status.component.top

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import ru.toxyxd.common.domain.model.stats.AdGuardStats


@Composable
fun GeneralStats(stats: AdGuardStats) {
    Card {
        Text(
            text = "General statistics",
            modifier = Modifier.padding(start = 16.dp, top = 16.dp, end = 16.dp),
            style = MaterialTheme.typography.headlineSmall,
        )
        Text(
            text = "For the last ${stats.dnsQueries.size} ${stats.timeUnits}",
            modifier = Modifier.padding(start = 16.dp, end = 16.dp, bottom = 8.dp),
            style = MaterialTheme.typography.bodyMedium,
        )
        Column(modifier = Modifier.padding(16.dp)) {
            TableLine(
                leftText = "DNS queries",
                rightText = "${stats.numDnsQueries}",
            )
            TableLine(
                leftText = "Blocked by Filters",
                rightText = "${stats.numBlockedFiltering}",
            )
            TableLine(
                leftText = "Blocked malware/phishing",
                rightText = "${stats.numReplacedSafebrowsing}",
            )
            TableLine(
                leftText = "Blocked adult websites",
                rightText = "${stats.numReplacedParental}",
            )
            TableLine(
                leftText = "Enforced safe search",
                rightText = "${stats.numReplacedSafesearch}",
            )
            TableLine(
                leftText = "Average processing time",
                rightText = "${(stats.avgProcessingTime * 1000).toInt()} ms",
            )
        }
    }
}