package com.pondpedia.compose.pondpedia.presentation.screens.home.ponds.screens.details.components

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
import com.pondpedia.compose.pondpedia.presentation.screens.home.ponds.components.viewmodel.PondsState
import com.pondpedia.compose.pondpedia.presentation.screens.home.ponds.screens.details.screens.DetailsScreenA
import com.pondpedia.compose.pondpedia.presentation.screens.home.ponds.screens.details.screens.DetailsScreenB
import com.pondpedia.compose.pondpedia.presentation.screens.home.ponds.screens.details.screens.DetailsScreenC
import com.pondpedia.compose.pondpedia.presentation.screens.home.ponds.screens.details.screens.DetailsScreenD

enum class Tab (val title: String) {
    DetailsTabA("Air"),
    DetailsTabB("Ikan"),
    DetailsTabC("Pakan"),
    DetailsTabD("Analisa"),
}

sealed class PondTabScreen(
    val route: String,
    val title: String,
) {
    object Fish : PondTabScreen(
        route = "FISH",
        title = "Ikan",
    )
    object Feed : PondTabScreen(
        route = "FEED",
        title = "Pakan",
    )
    object Water : PondTabScreen(
        route = "WATER",
        title = "Air",
    )
    object More : PondTabScreen(
        route = "MORE",
        title = "Lainnya",
    )
}

@Composable
fun DetailsTabScreen(
    pondsState: PondsState,
) {
    var selectedTab by remember { mutableStateOf(com.pondpedia.compose.pondpedia.presentation_copy.screens.home.ponds.screens.details.components.Tab.DetailsTabA) }
    var tabIndex by remember { mutableStateOf(0) }
    val tabs =
        listOf(
            com.pondpedia.compose.pondpedia.presentation_copy.screens.home.ponds.screens.details.components.Tab.DetailsTabA,
            com.pondpedia.compose.pondpedia.presentation_copy.screens.home.ponds.screens.details.components.Tab.DetailsTabB,
            com.pondpedia.compose.pondpedia.presentation_copy.screens.home.ponds.screens.details.components.Tab.DetailsTabC,
            com.pondpedia.compose.pondpedia.presentation_copy.screens.home.ponds.screens.details.components.Tab.DetailsTabD,
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
            com.pondpedia.compose.pondpedia.presentation_copy.screens.home.ponds.screens.details.components.Tab.DetailsTabA -> DetailsScreenA(pondsState = pondsState)
            com.pondpedia.compose.pondpedia.presentation_copy.screens.home.ponds.screens.details.components.Tab.DetailsTabB -> DetailsScreenB(pondsState = pondsState)
            com.pondpedia.compose.pondpedia.presentation_copy.screens.home.ponds.screens.details.components.Tab.DetailsTabC -> DetailsScreenC(pondsState = pondsState)
            com.pondpedia.compose.pondpedia.presentation_copy.screens.home.ponds.screens.details.components.Tab.DetailsTabD -> DetailsScreenD(pondsState = pondsState)
        }
    }
}

