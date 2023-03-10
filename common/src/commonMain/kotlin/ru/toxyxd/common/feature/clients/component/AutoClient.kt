package ru.toxyxd.common.feature.clients.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ru.toxyxd.common.domain.model.clients.AutoClient

@Composable
fun AutoClient(
    client: AutoClient,
) {
    ElevatedCard(
        modifier = Modifier.fillMaxWidth()
    ) {
        Column(
            modifier = Modifier.padding(12.dp),
        ) {
            Row {
                Text(
                    text = client.ip,
                    fontSize = 18.sp
                )
                if (client.name.isNotBlank()) {
                    Text(
                        text = " (${client.name})",
                    )
                }
            }
            Text(text = client.source)
        }
    }
}
