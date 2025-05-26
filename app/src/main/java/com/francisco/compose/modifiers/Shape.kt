package com.francisco.compose.modifiers

import androidx.compose.foundation.Image
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Outline
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.LayoutDirection
import com.francisco.compose.R
import com.francisco.compose.ui.theme.ComposeTheme

data object TriangleShape: Shape {
    override fun createOutline(
        size: Size,
        layoutDirection: LayoutDirection,
        density: Density
    ): Outline {
        return Outline.Generic(
            path = Path().apply {
                moveTo(
                    x = size.width / 2f,
                    y = 0f
                )
                lineTo(
                    x = 0f,
                    y = size.height
                )
                lineTo(
                    x = size.width,
                    y = size.height
                )
                close()
            }
        )
    }
}

@Composable
fun ShapeModifiersDemo(modifier: Modifier = Modifier) {
    Image(
        painter = painterResource(R.drawable.living_room),
        contentDescription = null,
        contentScale = ContentScale.Crop,
        modifier = Modifier
//            .clip(CircleShape)
//            .clip(RoundedCornerShape(
//                percent = 50
//                topStartPercent = 50
//            ))
            .clip(TriangleShape)
    )
}

@Preview(
    showBackground = true
)
@Composable
private fun ShapeModifiersDemoPreview() {
    ComposeTheme {
        ShapeModifiersDemo()
    }
}