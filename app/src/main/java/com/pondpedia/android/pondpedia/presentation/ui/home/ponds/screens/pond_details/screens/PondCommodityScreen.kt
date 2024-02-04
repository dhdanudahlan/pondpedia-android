package com.pondpedia.android.pondpedia.presentation.ui.home.ponds.screens.pond_details.screens

import android.util.Log
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Add
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.pondpedia.android.pondpedia.presentation.PondPediaAppState
import com.pondpedia.android.pondpedia.presentation.ui.home.ponds.components.viewmodel.PondDetailsEvent
import com.pondpedia.android.pondpedia.presentation.ui.home.ponds.components.viewmodel.PondDetailsState
import com.pondpedia.android.pondpedia.presentation.ui.home.ponds.components.viewmodel.PondsState
import com.pondpedia.android.pondpedia.presentation.theme.Black

@Composable
fun PondCommodityScreen(
    homeState: PondPediaAppState,
    pondsState: PondsState,
    pondDetailsState: PondDetailsState,
    onEvent: (PondDetailsEvent) -> Unit,
    innerPadding: PaddingValues,
    onNavigateToAddCommodityScreen: () -> Unit
) {
    val listOfCommodity = pondDetailsState.commodity
    val listOfRecords = listOf(
        "Growth Records",
        "Health Records",
    )
    var selectedCommodityId by rememberSaveable { mutableStateOf(listOfCommodity[0].commodityId)  }
    var commodityTabIndex by rememberSaveable { mutableStateOf(0) }
    var selectedRecordsTab by rememberSaveable { mutableStateOf(listOfRecords.first()) }
    var recordsTabIndex by rememberSaveable { mutableStateOf(0) }

    val scrollState = rememberScrollState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(innerPadding),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        TabRow(
            selectedTabIndex = commodityTabIndex,
        ) {
            if (listOfCommodity.isNotEmpty()) {
                listOfCommodity.forEachIndexed { index, tab ->
                    Tab(
                        text = { Text(text = tab.name, fontSize = 12.sp, maxLines = 1) },
                        selected = selectedCommodityId == (tab.commodityId),
                        onClick = {
                            Log.d("PondCommodityScreen", "${tab.commodityId}")
                            selectedCommodityId = tab.commodityId
                            commodityTabIndex = index
                        },
                    )
                }
            } else {
                Tab(
                    selected = true,
                    onClick = {
                        /*onEvent(PondDetailsEvent.ShowDialog)*/
                        onNavigateToAddCommodityScreen()
                    }
                ) {
                    Icon(imageVector = Icons.Outlined.Add, contentDescription = "Add Commodity")
                }
            }
        }
        TabRow(
            selectedTabIndex = recordsTabIndex,
        ) {
            listOfRecords.forEachIndexed { index, tab ->
                Tab(
                    text = { Text(text = tab, fontSize = 12.sp, maxLines = 1) },
                    selected = selectedRecordsTab == tab,
                    onClick = {
                        selectedRecordsTab = tab
                        recordsTabIndex = index
                    },
                )
            }
        }
        when (recordsTabIndex) {
            0 -> {
                onEvent(PondDetailsEvent.SetCommodityId(selectedCommodityId))
                CommodityGrowthScreen(pondDetailsState, innerPadding)
            }

            1 -> {
                onEvent(PondDetailsEvent.SetCommodityId(selectedCommodityId))
                CommodityHealthScreen(pondDetailsState, innerPadding)
            }
        }
    }

}

@Composable
fun CommodityGrowthScreen(
    pondDetailsState: PondDetailsState,
    innerPadding: PaddingValues
) {
    val listOfRecords = pondDetailsState.commodityGrowthRecords.sortedBy { it.date }
    val scrollState = rememberScrollState()
    val listOfRecordsReversed = listOfRecords.reversed()
    Box(
        Modifier
            .fillMaxSize()
//            .padding(innerPadding)
    ) {
        Column(
            Modifier
                .fillMaxSize()
                .verticalScroll(scrollState)
        ) {
            listOfRecordsReversed.forEachIndexed { index, records ->
                Card(
                    modifier = Modifier
                        .padding(8.dp)
                        .fillMaxWidth()
                        .fillMaxHeight(),
                ) {
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(8.dp)
                            .border(1.dp, Black, RoundedCornerShape(4))
                            .padding(4.dp),
                    ) {
                        Column {
                            Text(
                                text = "Kolam: ${pondDetailsState.pond.name}",
                                fontWeight = FontWeight.Bold
                            )
                            Text(
                                text = "${records.date}",
                                fontWeight = FontWeight.SemiBold
                            )
                            Text(text = "Umur : ${records.age}")
                            Text(text = "Panjang : ${records.length}")
                            Text(text = "Berat : ${records.weight}")
                            Text(text = "Catatan : ${records.note}")
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun CommodityHealthScreen(
    pondDetailsState: PondDetailsState,
    innerPadding: PaddingValues
) {
    val listOfRecords = pondDetailsState.commodityHealthRecords.sortedBy { it.date }
    val scrollState = rememberScrollState()
    var listOfRecordsReversed = listOfRecords.reversed()
    Box(
        Modifier
            .fillMaxSize()
            .padding(innerPadding)
    ) {
        Column(
            Modifier
                .fillMaxSize()
                .verticalScroll(scrollState)
        ) {
            listOfRecordsReversed.forEachIndexed { index, records ->
                Card(
                    modifier = Modifier
                        .padding(8.dp)
                        .fillMaxWidth()
                        .fillMaxHeight(),
                ) {
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(8.dp)
                            .border(1.dp, Black, RoundedCornerShape(4))
                            .padding(4.dp),
                    ) {
                        Column {
                            Text(
                                text = "Kolam: ${pondDetailsState.pond.name}",
                                fontWeight = FontWeight.Bold
                            )
                            Text(
                                text = "${records.date}",
                                fontWeight = FontWeight.SemiBold
                            )
                            Text(text = "Kemartian : ${records.death}")
                            Text(text = "Indikator : ${records.indicator}")
                            Text(text = "Tindakan : ${records.action}")
                            Text(text = "Catatan : ${records.note}")
                        }
                    }
                }
            }
        }
    }
}