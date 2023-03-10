package ru.toxyxd.common.feature.clients.component

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.*
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ru.toxyxd.common.domain.model.clients.Client
import ru.toxyxd.common.ui.ext.DarkGreen
import java.util.*

@Composable
fun Client(
    client: Client,
    modifier: Modifier = Modifier,
) {
    ElevatedCard(
        modifier = modifier.fillMaxWidth(),
    ) {
        Column(
            modifier = Modifier.padding(12.dp),
        ) {
            Row {
                Text(
                    text = client.name,
                    fontSize = 18.sp,
                )
                ClientIcon(
                    deviceTag = client.tags.first(), modifier = Modifier.padding(start = 4.dp),
                )
            }
            Text(text = client.ids.first())

            Row(
                modifier = Modifier.padding(top = 4.dp),
                horizontalArrangement = Arrangement.spacedBy(6.dp),
            ) {
                Icon(
                    if (client.filteringEnabled) Icons.Rounded.FilterAlt else Icons.Rounded.FilterAltOff,
                    contentDescription = "Rule filtering",
                    tint = if (client.filteringEnabled) Color.DarkGreen() else Color.Red,
                )
                Icon(
                    Icons.Rounded.VpnLock,
                    contentDescription = "Safe browsing",
                    tint = if (client.safebrowsingEnabled) Color.DarkGreen() else Color.Red,
                )
                Icon(
                    if (client.parentalEnabled) Icons.Rounded.ChildFriendly else Icons.Rounded.ChildCare,
                    contentDescription = "Parental filtering",
                    tint = if (client.parentalEnabled) Color.DarkGreen() else Color.Red,
                )
                Icon(
                    if (client.safesearchEnabled) Icons.Rounded.Search else Icons.Rounded.SearchOff,
                    contentDescription = "Safe search",
                    tint = if (client.safesearchEnabled) Color.DarkGreen() else Color.Red,
                )
                if (client.useGlobalSettings) {
                    val usesGlobalSettings =
                        "uses" + " " + "global settings".lowercase(
                            Locale.getDefault()
                        )
                    Icon(
                        Icons.Rounded.Settings,
                        contentDescription = usesGlobalSettings,
                        tint = Color.DarkGreen(),
                    )
                    Text(
                        text = usesGlobalSettings,
                        style = MaterialTheme.typography.labelMedium,
                        modifier = Modifier.align(
                            Alignment.CenterVertically
                        )
                    )
                }
            }
        }
    }
}