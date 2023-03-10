package ru.toxyxd.common.feature.server.status.component.chart

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.PointMode
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.ExperimentalTextApi
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.drawText
import androidx.compose.ui.text.rememberTextMeasurer
import androidx.compose.ui.unit.dp
import kotlin.math.max
import kotlin.math.min
import kotlin.math.pow
import kotlin.math.sqrt

@OptIn(ExperimentalTextApi::class)
@Composable
fun LineChart(
    modifier: Modifier = Modifier, data: List<Int>, graphColor: Color, timeUnits: String
) {
    val textMeasurement = rememberTextMeasurer()

    val (min, max) = remember(data) { data.minBy { it } to data.maxBy { it } }
    val transparentGraphColor = remember(graphColor) {
        graphColor.copy(alpha = 0.5f)
    }
    val points = remember { mutableListOf<Offset>() }
    val selectedPoint = remember { mutableStateOf<Offset?>(null) }
    val currentTheme = MaterialTheme.colorScheme

    Canvas(modifier = modifier.pointerInput(Unit) {
        detectDragGestures(
            onDragEnd = { selectedPoint.value = null },
            onDragCancel = { selectedPoint.value = null },
            onDrag = { change, _ ->
                val closestPoint = points.find {
                    val distance = sqrt((change.position.x - it.x).pow(2))
                    val pointSize = 15.dp.toPx()
                    distance <= pointSize
                }
                closestPoint?.let { selectedPoint.value = closestPoint }
            })
    }) {
        val ySpacing = size.height / (max - min)
        points.clear()

        for (i in data.indices) {
            val x = i * (size.width / data.size)
            val y = size.height - (data[i] - min) * ySpacing
            points.add(Offset(x, y))
        }

        val path = Path().apply {
            moveTo(points.first().x, size.height)
            points.forEach { lineTo(it.x, it.y) }
            lineTo(points.last().x, size.height)
        }
        drawPath(
            path = path, brush = Brush.verticalGradient(
                colors = listOf(transparentGraphColor, Color.Transparent),
                startY = 0f,
                endY = size.height
            )
        )
        drawPoints(
            points = points,
            color = graphColor,
            pointMode = PointMode.Polygon,
            strokeWidth = 2.dp.toPx(),
        )

        selectedPoint.value?.let {
            val measuredText = textMeasurement.measure(
                text = AnnotatedString(
                    "${data[points.indexOf(it)]}\n${
                        points.reversed().indexOf(it)
                    } $timeUnits ago"
                ), style = TextStyle(
                    color = currentTheme.onSurfaceVariant
                )
            )
            val textSize = measuredText.size
            val rectWidth = 20.dp.toPx() + textSize.width

            // clamp rectX to be in bounds of canvas
            val rectX = max(0f, min(it.x - rectWidth / 2, size.width - rectWidth))

            drawRoundRect(
                color = currentTheme.surfaceVariant, topLeft = Offset(
                    x = rectX, y = it.y - textSize.height - 30.dp.toPx()
                ), size = Size(
                    rectWidth, textSize.height + 20.dp.toPx()
                ), cornerRadius = CornerRadius(10.dp.toPx())
            )

            drawText(
                measuredText,
                topLeft = Offset(
                    x = rectX + (rectWidth - textSize.width) / 2,
                    y = it.y - textSize.height - 20.dp.toPx()
                ),
            )

            drawCircle(
                color = graphColor, radius = 8.dp.toPx(), center = it
            )
        }
    }
}