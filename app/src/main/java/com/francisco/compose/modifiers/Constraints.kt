package com.francisco.compose.modifiers

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@SuppressLint("UnusedBoxWithConstraintsScope")
@Composable
fun BoxWithConstraintsDemo() {
    BoxWithConstraints(
        modifier = Modifier
            .width(200.dp)
    ) {
        if (constraints.hasFixedWidth) {
            Text("Fix width")
        } else {
            Text("No fix width")
        }
    }
}
