package ru.toxyxd.common.feature.logs.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.RemoveModerator
import androidx.compose.material.icons.rounded.VerifiedUser
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color.Companion.Green
import androidx.compose.ui.graphics.Color.Companion.Red
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import ru.toxyxd.common.domain.model.logs.Log
import ru.toxyxd.common.domain.model.logs.LogReason

@Composable
fun LogComponent(
    log: Log,
    modifier: Modifier = Modifier,
) {
    ElevatedCard(
        modifier = modifier.fillMaxWidth()
    ) {
        Row(
            modifier = Modifier.padding(12.dp)
        ) {
            Column(
                modifier = Modifier.weight(3f)
            ) {
                Row {
                    Text(
                        text = log.question.unicodeName ?: log.question.name,
                        overflow = TextOverflow.Ellipsis,
                        maxLines = 1,
                    )
                }
                Row {
                    Text(log.client)
                }
            }
            Column(
                modifier = Modifier.weight(1f),
                horizontalAlignment = Alignment.End
            ) {
                ReasonIcon(logReason = log.reason)
            }
        }
    }
}

@Composable
private fun ReasonIcon(logReason: LogReason) {
    val Green = Green.copy(alpha = 0.5f)
    val Red = Red.copy(alpha = 0.5f)

    val icon = when (logReason) {
        LogReason.NotFilteredNotFound -> Icons.Rounded.VerifiedUser
        LogReason.NotFilteredWhiteList -> Icons.Rounded.VerifiedUser
        LogReason.NotFilteredError -> Icons.Rounded.VerifiedUser
        LogReason.FilteredBlackList -> Icons.Rounded.RemoveModerator
        LogReason.FilteredSafeBrowsing -> Icons.Rounded.RemoveModerator
        LogReason.FilteredParental -> Icons.Rounded.RemoveModerator
        LogReason.FilteredInvalid -> Icons.Rounded.RemoveModerator
        LogReason.FilteredSafeSearch -> Icons.Rounded.RemoveModerator
        LogReason.FilteredBlockedService -> Icons.Rounded.RemoveModerator
        LogReason.Rewrite -> Icons.Rounded.RemoveModerator
        LogReason.RewriteEtcHosts -> Icons.Rounded.RemoveModerator
        LogReason.RewriteRule -> Icons.Rounded.RemoveModerator
    }
    val tint = when (logReason) {
        LogReason.NotFilteredNotFound -> Green
        LogReason.NotFilteredWhiteList -> Green
        LogReason.NotFilteredError -> Green
        LogReason.FilteredBlackList -> Red
        LogReason.FilteredSafeBrowsing -> Red
        LogReason.FilteredParental -> Red
        LogReason.FilteredInvalid -> Red
        LogReason.FilteredSafeSearch -> Red
        LogReason.FilteredBlockedService -> Red
        LogReason.Rewrite -> Red
        LogReason.RewriteEtcHosts -> Red
        LogReason.RewriteRule -> Red
    }

    Icon(
        icon,
        contentDescription = null,
        tint = tint
    )
}