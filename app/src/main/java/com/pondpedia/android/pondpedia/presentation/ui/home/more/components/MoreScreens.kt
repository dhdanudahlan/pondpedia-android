package com.pondpedia.android.pondpedia.presentation.ui.home.more.components

import com.pondpedia.android.pondpedia.R

sealed class MoreScreens(
    val route: String,
    val labelTextId: Int,
    val titleTextId: Int,
    val categoryId: Int,
) {
    data object Profile : MoreScreens(
        route = "MORE_PROFILE",
        labelTextId = R.string.more_profile_label,
        titleTextId = R.string.more_profile_title,
        categoryId = R.string.more_category_common
    )
    data object Settings : MoreScreens(
        route = "MORE_SETTINGS",
        labelTextId = R.string.more_settings_label,
        titleTextId = R.string.more_settings_title,
        categoryId = R.string.more_category_common
    )
}