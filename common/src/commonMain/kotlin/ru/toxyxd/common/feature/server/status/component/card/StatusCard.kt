package ru.toxyxd.common.feature.server.status.component.card

import androidx.compose.foundation.layout.*
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import ru.toxyxd.common.ui.ext.DarkGreen

@Composable
fun StatusCard(
    icon: @Composable () -> Unit,
    title: String,
    enabled: Boolean,
    modifier: Modifier = Modifier
) {
    val color = if (enabled) {
        StatusColor.Green
    } else {
        StatusColor.Red
    }

    Surface(
        shape = CardDefaults.shape,
        color = color,
        contentColor = Color.White,
        modifier = modifier.fillMaxSize()
    ) {
        Row(
            modifier = Modifier.padding(16.dp),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(modifier = Modifier.padding(end = 8.dp)) {
                icon()
            }
            Column {
                Text(text = title, overflow = TextOverflow.Ellipsis)
            }
        }
    }
}

private object StatusColor {
    val Green = Color.DarkGreen()
    val Red = Color.Red.copy(alpha = 0.8f)
}