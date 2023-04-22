package ru.toxyxd.common.feature.server.status.component.top

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

@Composable
fun TopLine(
    leftText: String,
    leftSubText: String? = null,
    rightText: String,
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Column {
            Text(text = leftText)
            if (leftSubText != null) {
                Text(text = leftSubText, style = MaterialTheme.typography.bodySmall)
            }
        }
        Spacer(modifier = Modifier.weight(1f))
        Column {
            Text(text = rightText)
        }
    }
}