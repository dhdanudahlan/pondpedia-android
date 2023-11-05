package com.pondpedia.compose.pondpedia.presentation.screens.home.ponds.screens.pond_details.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Anchor
import androidx.compose.material.icons.filled.FoodBank
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Water
import androidx.compose.material.icons.outlined.Anchor
import androidx.compose.material.icons.outlined.FoodBank
import androidx.compose.material.icons.outlined.Info
import androidx.compose.material.icons.outlined.Water
import androidx.compose.ui.graphics.vector.ImageVector
import com.pondpedia.compose.pondpedia.R

sealed class PondNavigationItem (
    val route: String,
    val labelTextId: Int,
    val titleTextId: Int,
    val selectedIcon: ImageVector,
    val unselectedIcon: ImageVector,
    val hasUnread: Boolean,
    val badgeCount: Int?,
) {
    data object Overview: PondNavigationItem(
        route = "POND_OVERVIEW",
        labelTextId = R.string.pond_details_overview,
        titleTextId = R.string.pond_details_overview,
        selectedIcon = Icons.Filled.Info,
        unselectedIcon = Icons.Outlined.Info,
        hasUnread = false,
        badgeCount = null,
    )
    data object Commodity: PondNavigationItem(
        route = "POND_COMMODITY",
        labelTextId = R.string.pond_details_commodity,
        titleTextId = R.string.pond_details_commodity,
        selectedIcon = Icons.Filled.Anchor,
        unselectedIcon = Icons.Outlined.Anchor,
        hasUnread = false,
        badgeCount = null,
    )
    data object Feeding: PondNavigationItem(
        route = "POND_FEEDING",
        labelTextId = R.string.pond_details_feeding,
        titleTextId = R.string.pond_details_feeding,
        selectedIcon = Icons.Filled.FoodBank,
        unselectedIcon = Icons.Outlined.FoodBank,
        hasUnread = false,
        badgeCount = null,
    )
    data object Water: PondNavigationItem(
        route = "POND_WATER",
        labelTextId = R.string.pond_details_water,
        titleTextId = R.string.pond_details_water,
        selectedIcon = Icons.Filled.Water,
        unselectedIcon = Icons.Outlined.Water,
        hasUnread = false,
        badgeCount = null,
    )

}

