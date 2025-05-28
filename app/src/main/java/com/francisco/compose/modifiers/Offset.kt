package com.francisco.compose.modifiers

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
private fun OffsetModifierDemo(
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .size(100.dp)
            .background(Color.Red)
            .offset(
                x = 50.dp,
                y = 20.dp
            )
    ) {
        Text(
            text = "Hello World",
            modifier = Modifier
                .background(Color.Green)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun OffsetModifierDemoPreview() {
    OffsetModifierDemo()
}
