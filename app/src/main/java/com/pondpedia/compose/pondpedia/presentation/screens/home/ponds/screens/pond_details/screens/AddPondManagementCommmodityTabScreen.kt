package com.pondpedia.compose.pondpedia.presentation.screens.home.ponds.screens.pond_details.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Add
import androidx.compose.material3.Icon
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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.pondpedia.compose.pondpedia.presentation.screens.home.ponds.components.viewmodel.PondDetailsEvent
import com.pondpedia.compose.pondpedia.presentation.screens.home.ponds.components.viewmodel.PondDetailsState

@Composable
fun AddPondManagementCommmodityTabScreen(
    pondDetailsState: PondDetailsState,
    onEvent: (PondDetailsEvent) -> Unit
) {
    var tabIndex by rememberSaveable { mutableStateOf(0) }
    val commodityTabs = pondDetailsState.commodity
    val scrollState = rememberScrollState()

    Scaffold { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .verticalScroll(scrollState),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Text(text = "Tambahkan Komoditas", fontWeight = FontWeight.Bold)

            ScrollableTabRow(
                selectedTabIndex = tabIndex,
                modifier = Modifier.fillMaxWidth()
            ) {
                if (commodityTabs.isNotEmpty()) {
                    commodityTabs.forEachIndexed { index, tab ->
                        Tab(
                            text = { Text(text = tab.name, fontSize = 12.sp, maxLines = 1) },
                            selected = tabIndex == index,
                            onClick = {
                                tabIndex = index
                            },
                        )
                    }

                    Tab(
                        selected = true,
                        onClick = { onEvent(PondDetailsEvent.ShowDialog) }
                    ) {
                        Icon(imageVector = Icons.Outlined.Add, contentDescription = "Add Commodity")
                    }
                } else {
                    Tab(
                        selected = true,
                        onClick = { onEvent(PondDetailsEvent.ShowDialog) },
                        modifier = Modifier.height(24.dp)
                    ) {
                        Icon(imageVector = Icons.Outlined.Add, contentDescription = "Add Commodity")
                    }
                }
            }

        }
    }
}

