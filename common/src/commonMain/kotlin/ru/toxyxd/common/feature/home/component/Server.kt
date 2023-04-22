package ru.toxyxd.common.feature.home.component

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.animation.expandVertically
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.unit.dp

const val ANIMATION_DURATION = 300

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Server(
    address: String,
    name: String,
    onConnectClicked: () -> Unit = {},
    onRemoveClicked: () -> Unit,
) {
    var expandedState by remember { mutableStateOf(false) }
    val rotationState by animateFloatAsState(targetValue = if (expandedState) 180f else 0f)

    Card(
        onClick = { expandedState = !expandedState },
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth()
    ) {
        Column(
            Modifier
                .padding(12.dp)
                .fillMaxWidth()
        ) {
            Row {
                Column {
                    Text(text = name)
                    Text(text = address)
                }
                Spacer(modifier = Modifier.weight(1f))
                IconButton(
                    onClick = { expandedState = !expandedState },
                ) {
                    Icon(
                        imageVector = Icons.Filled.ArrowDropDown,
                        contentDescription = "Expand",
                        modifier = Modifier.rotate(rotationState)
                    )
                }
            }

            AnimatedVisibility(
                visible = expandedState,
                enter = expandVertically(
                    expandFrom = Alignment.Top,
                    animationSpec = tween(ANIMATION_DURATION)
                ),
                exit = shrinkVertically(
                    shrinkTowards = Alignment.Top,
                    animationSpec = tween(ANIMATION_DURATION)
                )
            ) {
                Row {
                    Spacer(modifier = Modifier.weight(1f))
                    Button(
                        onClick = onRemoveClicked,
                        modifier = Modifier
                            .padding(8.dp)
                            .align(Alignment.CenterVertically)
                    ) {
                        Text(text = "Remove")
                    }
                    Button(
                        onClick = onConnectClicked,
                        modifier = Modifier
                            .padding(8.dp)
                            .align(Alignment.CenterVertically)
                    ) {
                        Text(text = "Connect")
                    }
                }
            }
        }
    }
}
