package ru.toxyxd.common.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun GrayLineHorizontalSpacer(modifier: Modifier = Modifier) {
    Spacer(modifier = modifier
        .height(1.dp)
        .background(color = Color.DarkGray)
        .fillMaxWidth())
}

@Composable
fun GrayLineVerticalSpacer(modifier: Modifier = Modifier) {
    Spacer(modifier = modifier
        .fillMaxHeight()
        .width(1.dp)
        .background(color = Color.DarkGray))
}