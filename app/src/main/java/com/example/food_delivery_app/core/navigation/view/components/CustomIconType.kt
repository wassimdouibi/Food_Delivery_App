package com.example.food_delivery_app.core.navigation.view.components

import androidx.compose.ui.graphics.vector.ImageVector

sealed class CustomIconType {
    data class VectorIcon(
        val imageVector: ImageVector,
        val iconDescription: String
    ) : CustomIconType()

    data class PainterIcon(
        val resourceId: Int,
        val iconDescription: String
    ) : CustomIconType()
}