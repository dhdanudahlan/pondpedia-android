package com.pondpedia.android.pondpedia.presentation.ui.home.ponds.screens.pond_details.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.ScrollableTabRow
import androidx.compose.material3.Tab
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.pondpedia.android.pondpedia.presentation.ui.home.ponds.components.viewmodel.PondDetailsEvent
import com.pondpedia.android.pondpedia.presentation.ui.home.ponds.components.viewmodel.PondDetailsState
import com.pondpedia.android.pondpedia.presentation.ui.home.ponds.screens.pond_details.components.AddPondManagementRecordsTab
import com.pondpedia.android.pondpedia.presentation.ui.home.ponds.screens.pond_details.components.AddPondManagementRecordsTabCommodityGrowthScreen
import com.pondpedia.android.pondpedia.presentation.ui.home.ponds.screens.pond_details.components.AddPondManagementRecordsTabCommodityHealthScreen
import com.pondpedia.android.pondpedia.presentation.ui.home.ponds.screens.pond_details.components.AddPondManagementRecordsTabFeedingScreen
import com.pondpedia.android.pondpedia.presentation.ui.home.ponds.screens.pond_details.components.AddPondManagementRecordsTabWaterScreen

@Composable
fun AddPondManagementRecordsTabScreen(
    pondDetailsState: PondDetailsState,
    onEvent: (PondDetailsEvent) -> Unit
) {
    var selectedTab by rememberSaveable { mutableStateOf(AddPondManagementRecordsTab.AddPondManagementRecordsTabWater) }
    var tabIndex by rememberSaveable { mutableStateOf(0) }
    val pondTabs =
        listOf(
            AddPondManagementRecordsTab.AddPondManagementRecordsTabWater,
            AddPondManagementRecordsTab.AddPondManagementRecordsTabFeeding,
            AddPondManagementRecordsTab.AddPondManagementRecordsTabCommodityGrowth,
            AddPondManagementRecordsTab.AddPondManagementRecordsTabCommodityHealth,
        )

    Scaffold { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Text(text = "Tambahkan Catatan", fontWeight = FontWeight.Bold)
            ScrollableTabRow(
                selectedTabIndex = tabIndex,
                modifier = Modifier.fillMaxWidth()
            ) {
                pondTabs.forEachIndexed { index, tab ->
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
                AddPondManagementRecordsTab.AddPondManagementRecordsTabCommodityGrowth -> {
                    AddPondManagementRecordsTabCommodityGrowthScreen(
                        pondDetailsState = pondDetailsState,
                        onEvent = onEvent
                    )
                }
                AddPondManagementRecordsTab.AddPondManagementRecordsTabCommodityHealth -> {
                    AddPondManagementRecordsTabCommodityHealthScreen(
                        pondDetailsState = pondDetailsState,
                        onEvent = onEvent
                    )
                }
                AddPondManagementRecordsTab.AddPondManagementRecordsTabFeeding -> {
                    AddPondManagementRecordsTabFeedingScreen(
                        pondDetailsState = pondDetailsState,
                        onEvent = onEvent
                    )
                }
                AddPondManagementRecordsTab.AddPondManagementRecordsTabWater -> {
                    AddPondManagementRecordsTabWaterScreen(
                        pondDetailsState = pondDetailsState,
                        onEvent = onEvent
                    )
                }

            }
        }
    }
}

