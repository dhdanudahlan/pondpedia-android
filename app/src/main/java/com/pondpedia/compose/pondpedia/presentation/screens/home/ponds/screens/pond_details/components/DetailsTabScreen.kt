package com.pondpedia.compose.pondpedia.presentation.screens.home.ponds.screens.pond_details.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.sp
import com.pondpedia.compose.pondpedia.presentation.screens.home.ponds.components.viewmodel.PondDetailsState
import com.pondpedia.compose.pondpedia.presentation.screens.home.ponds.screens.pond_details.screens.DetailsScreenA
import com.pondpedia.compose.pondpedia.presentation.screens.home.ponds.screens.pond_details.screens.DetailsScreenB
import com.pondpedia.compose.pondpedia.presentation.screens.home.ponds.screens.pond_details.screens.DetailsScreenC

enum class Tab (val title: String) {
    DetailsTabA("Komoditas"),
    DetailsTabB("Air"),
    DetailsTabC("Pakan"),
//    DetailsTabD("Analisa"),
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
    data object More : PondTabScreen(
        route = "MORE",
        title = "Lainnya",
    )
}

@Composable
fun DetailsTabScreen(
    pondDetailsState: PondDetailsState,
) {
    var selectedTab by remember { mutableStateOf(Tab.DetailsTabA) }
    var tabIndex by remember { mutableStateOf(0) }
    val tabs =
        listOf(
            Tab.DetailsTabA,
            Tab.DetailsTabB,
            Tab.DetailsTabC,
//            Tab.DetailsTabD,
        )


    Column(modifier = Modifier.fillMaxWidth()) {
        TabRow(selectedTabIndex = tabIndex) {
            tabs.forEachIndexed { index, tab ->
                Tab(
                    text = { Text(text = tab.title, fontSize = 12.sp, maxLines = 1) },
                    selected = selectedTab == tab,
                    onClick = {
                        selectedTab = tab
                        tabIndex = index
                    },
                )
            }
        }
        when (selectedTab) {
            Tab.DetailsTabA -> DetailsScreenA(pondDetailsState = pondDetailsState)
            Tab.DetailsTabB -> DetailsScreenB(pondDetailsState = pondDetailsState)
            Tab.DetailsTabC -> DetailsScreenC(pondDetailsState = pondDetailsState)
//            Tab.DetailsTabD -> DetailsScreenD(pondDetailsState = pondDetailsState)
        }
    }
}

