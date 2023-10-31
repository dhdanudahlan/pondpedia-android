package com.pondpedia.compose.pondpedia.presentation.screens.home.ponds.components

import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Outline
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.LayoutDirection
import java.lang.Math.min
import java.lang.Math.sqrt

class HexagonShape : Shape {
    override fun createOutline(
        size: Size,
        layoutDirection: LayoutDirection,
        density: Density
    ): Outline {
        return Outline.Generic(
            path = com.pondpedia.compose.pondpedia.presentation_copy.screens.home.ponds.components.drawCustomHexagonPath(
                size
            )
        )
    }
}

fun drawCustomHexagonPath(size: Size): Path {
    return Path().apply {
        val radius = min(size.width / 2f, size.height / 2f)
        com.pondpedia.compose.pondpedia.presentation_copy.screens.home.ponds.components.customHexagon(
            radius,
            size
        )
    }
}

fun Path.customHexagon(radius: Float, size: Size) {
    val triangleHeight = (sqrt(3.0) * radius / 2)
    val centerX = size.width / 2
    val centerY = size.height / 2

    moveTo(centerX, centerY + radius)
    lineTo((centerX - triangleHeight).toFloat(), centerY + radius/2)
    lineTo((centerX - triangleHeight).toFloat(), centerY - radius/2)
    lineTo(centerX, centerY - radius)
    lineTo((centerX + triangleHeight).toFloat(), centerY - radius/2)
    lineTo((centerX + triangleHeight).toFloat(), centerY + radius/2)

    close()
}