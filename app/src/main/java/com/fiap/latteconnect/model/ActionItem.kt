package com.fiap.latteconnect.model

import androidx.compose.ui.graphics.vector.ImageVector

data class ActionItem(
    val title: String,
    val description: String,
    val icon: ImageVector,
    val route: String
)
