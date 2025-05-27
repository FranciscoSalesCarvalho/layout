package com.francisco.compose.animation

import androidx.compose.animation.animateColor
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color

@Composable
fun AnimatedCircle(
    modifier: Modifier = Modifier
) {
    val transition = rememberInfiniteTransition()
    val color by transition.animateColor(
        initialValue = Color.Red,
        targetValue = Color.Green,
        animationSpec = infiniteRepeatable(
            animation = tween(durationMillis = 1000),
            repeatMode = RepeatMode.Reverse
        )
    )
    Box(
        modifier = modifier
            .drawBehind {
                drawOval(
                    color = color,
                    topLeft = Offset(
                        x = size.width * 0.25f,
                        y = size.width * 0.25f
                    ),
                    size = Size(
                        width = size.width * 0.5f,
                        height = size.width * 0.5f
                    )
                )
            }
    )
}