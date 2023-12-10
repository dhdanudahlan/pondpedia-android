package com.pondpedia.android.pondpedia.presentation.ui.home.ponds.screens.pond_details.components

enum class PondTab (val title: String) {
    PondTabCommodity("Komoditas"),
    PondTabFeeding("Pakan"),
    PondTabWater("Air"),
}

sealed class PondTabScreen(
    val route: String,
    val title: String,
) {
    data object Commodity : PondTabScreen(
        route = "COMMODITY",
        title = "Komoditas",
    )
    data object Feed : PondTabScreen(
        route = "FEED",
        title = "Pakan",
    )
    data object Water : PondTabScreen(
        route = "WATER",
        title = "Air",
    )
}



