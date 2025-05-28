package com.francisco.compose.modifiers

import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.gestures.draggable
import androidx.compose.foundation.gestures.draggable2D
import androidx.compose.foundation.gestures.rememberDraggable2DState
import androidx.compose.foundation.gestures.rememberDraggableState
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.round
import kotlin.math.roundToInt

@Composable
fun DraggableBox(
    modifier: Modifier = Modifier
) {
    var offset by remember { mutableStateOf(Offset.Zero) }

    Box(
        modifier = modifier
            .offset { offset.round() }
            .size(100.dp)
//            .draggable2D(
//                state = rememberDraggable2DState {
//                    offset += it
//                }
//            )
//            .draggable(
//                state = rememberDraggableState {
//                    offset += Offset(x = it, y = 0f)
//                },
//                orientation = Orientation.Horizontal
//            )
//            .draggable(
//                state = rememberDraggableState {
//                    offset += Offset(x = 0f, y = it)
//                },
//                orientation = Orientation.Vertical
//            )
            .background(Color.Green),

        contentAlignment = Alignment.Center
    ) {
        Text("Drag Me", color = Color.White)
    }
}

@Composable
fun DraggableBoxV2(
    modifier: Modifier = Modifier
) {
    var offsetX by remember { mutableFloatStateOf(0f) }
    var offsetY by remember { mutableFloatStateOf(0f) }

    Box(
        modifier = modifier
            .offset { IntOffset(offsetX.roundToInt(), offsetY.roundToInt()) }
            .size(100.dp)
            .background(Color.Green)
            .pointerInput(Unit) {
                detectDragGestures { change, dragAmount ->
                    change.consume()
                    offsetX += dragAmount.x
                    offsetY += dragAmount.y
                }
            },
        contentAlignment = Alignment.Center
    ) {
        Text("Drag Me", color = Color.White)
    }
}

@Preview(showBackground = true)
@Composable
fun DraggableBoxPreview() {
    DraggableBox()
}