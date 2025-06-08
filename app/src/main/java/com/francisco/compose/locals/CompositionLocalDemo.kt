package com.francisco.compose.locals

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.francisco.compose.ui.theme.ComposeTheme

@Composable
private fun CompositionLocalDemo() {
    val contentColor = LocalContentColor.current
    val textStyle = LocalTextStyle.current
    Button(
        onClick = {},
        colors = ButtonDefaults.buttonColors(
            contentColor = Color.Red
        )
    ) {
        CompositionLocalProvider(
            LocalContentColor provides Color.Green
        ) {
            Icon(
                imageVector = Icons.Default.Check,
                contentDescription = null
            )
            Text("Hello world")
        }
    }
}

@Preview
@Composable
private fun ShapeModifiersDemoPreview() {
    ComposeTheme {
        CompositionLocalDemo()
    }
}