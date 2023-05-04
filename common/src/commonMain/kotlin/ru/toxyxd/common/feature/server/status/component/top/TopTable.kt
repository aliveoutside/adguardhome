package ru.toxyxd.common.feature.server.status.component.top

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Card
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import java.text.DecimalFormat

@Composable
fun TopTable(
    title: String,
    subTitle: String,
    elements: List<Map<String, Int>>,
    total: Int,
) {
    Card {
        Text(
            text = title,
            modifier = Modifier.padding(start = 16.dp, top = 16.dp, end = 16.dp),
            style = MaterialTheme.typography.headlineSmall
        )
        Text(
            text = subTitle,
            modifier = Modifier.padding(start = 16.dp, end = 16.dp, bottom = 8.dp),
            style = MaterialTheme.typography.bodyMedium
        )
        TopTableList(elements = elements, total = total, modifier = Modifier.padding(16.dp))
    }
}

@Composable
fun TopTableElement(
    leftText: String,
    rightText: Int,
    total: Int,
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
    ) {
        Column(modifier = Modifier.weight(2f)) {
            Text(
                text = leftText,
                overflow = TextOverflow.Ellipsis,
                maxLines = 1,
            )
        }
        Spacer(modifier = Modifier.width(16.dp))
        Column(modifier = Modifier.weight(1f, true).requiredWidthIn(Dp.Unspecified, 160.dp)) {
            Row {
                Text(
                    text = rightText.toString(),
                    modifier = Modifier.padding(end = 16.dp),
                )
                Text(
                    text = "${DecimalFormat("#.##").format((rightText / total.toFloat()) * 100)}%",
                    style = MaterialTheme.typography.bodySmall,
                    modifier = Modifier.align(Alignment.CenterVertically),
                )
            }
            Spacer(modifier = Modifier.height(4.dp))
            LinearProgressIndicator(
                progress = (rightText / total.toFloat()),
                trackColor = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.2f),
                modifier = Modifier.height(4.dp).align(Alignment.End)
            )
        }
    }
}

@Composable
expect fun TopTableList(
    elements: List<Map<String, Int>>,
    total: Int,
    modifier: Modifier = Modifier,
)