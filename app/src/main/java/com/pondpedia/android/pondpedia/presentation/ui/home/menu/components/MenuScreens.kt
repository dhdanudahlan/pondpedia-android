package com.pondpedia.android.pondpedia.presentation.ui.home.menu.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Chat
import androidx.compose.ui.graphics.vector.ImageVector
import com.pondpedia.android.pondpedia.R

sealed class MenuScreens(
    val route: String,
    val labelTextId: Int,
    val titleTextId: Int,
    val icon: ImageVector,
    val hasUnread: Boolean,
    val badgeCount: Int?,
) {
    data object AiChat : MenuScreens(
        route = "MENU_AICHAT",
        labelTextId = R.string.menu_aichat_label,
        titleTextId = R.string.menu_aichat_title,
        icon = Icons.Filled.Chat,
        hasUnread = false,
        badgeCount = null,
    )
}