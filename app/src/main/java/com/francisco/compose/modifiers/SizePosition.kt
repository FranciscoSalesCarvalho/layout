package com.francisco.compose.modifiers

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.layout.positionInWindow
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp

@Composable
private fun SizePositionModifierDemo() {
    var screenSize by remember {
        mutableStateOf(IntSize.Zero)
    }

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(100.dp)
            .onSizeChanged {
                screenSize = it
            }
            .onGloballyPositioned { coords ->
                println(coords.positionInWindow())
            }
    ) {
        Text(
            text = if (screenSize.width > 1500) {
                ""
            } else {
                ""
            }
        )
    }

}