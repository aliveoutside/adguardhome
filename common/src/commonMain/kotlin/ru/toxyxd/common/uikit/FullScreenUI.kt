package ru.toxyxd.common.uikit

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Error
import androidx.compose.material.icons.rounded.BugReport
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalClipboardManager
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun FullscreenLoading() {
    Box(Modifier.fillMaxSize()) {
        CircularProgressIndicator(
            modifier = Modifier
                .align(Alignment.Center)
                .size(56.dp)
        )
    }
}

@Composable
fun FullscreenError(
    onReload: () -> Unit,
    exception: Throwable
) {
    val clipboard = LocalClipboardManager.current

    Box(Modifier.fillMaxSize()) {
        Column(
            Modifier
                .align(Alignment.Center),
            verticalArrangement = Arrangement.spacedBy(12.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Icon(
                Icons.Default.Error, contentDescription = null, modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .size(56.dp)
            )

            Text(
                "An error occurred:"
            )

            TextButton(
                onClick = { onReload() }) {
                Text("Reload")
            }
        }

        Row(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(bottom = 16.dp)
        ) {
            TextButton(
                onClick = {
                    clipboard.setText(AnnotatedString("Message: ${exception.message}\n\n" + exception.stackTraceToString()))
                }) {
                Icon(imageVector = Icons.Rounded.BugReport, contentDescription = null)
                Spacer(modifier = Modifier.width(8.dp))
                Text("Copy")
            }
        }
    }
}

@Composable
fun FullscreenPlaceholder(
    icon: ImageVector,
    title: String,
    text: String
) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Icon(
            icon, contentDescription = null, modifier = Modifier
                .size(56.dp)
                .padding(bottom = 12.dp)
        )

        Spacer(modifier = Modifier.height(8.dp))

        Text(title, fontSize = 21.sp, color = MaterialTheme.colorScheme.onSurface)

        Spacer(modifier = Modifier.height(4.dp))

        Text(text, color = MaterialTheme.colorScheme.onSurfaceVariant)
    }
}