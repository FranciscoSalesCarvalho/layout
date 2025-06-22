package com.francisco.compose.modifiers

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.francisco.compose.ui.theme.ComposeTheme

@Composable
fun IntrinsicSizeDemo(
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .width(IntrinsicSize.Max)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Text(text = "Option 1")
            Checkbox(
                checked = true,
                onCheckedChange = {}
            )
        }
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Text(text = "Option 1, but in way longer")
            Checkbox(
                checked = true,
                onCheckedChange = {}
            )
        }
    }
}

@Preview(showSystemUi = true)
@Composable
private fun Preview() {
    ComposeTheme {
        Scaffold { padding ->
            IntrinsicSizeDemo(modifier = Modifier.padding(padding))
        }
    }
}