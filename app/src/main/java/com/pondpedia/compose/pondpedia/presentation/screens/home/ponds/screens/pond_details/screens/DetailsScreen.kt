package com.pondpedia.compose.pondpedia.presentation.screens.home.ponds.screens.pond_details.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Anchor
import androidx.compose.material.icons.filled.BrandingWatermark
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Button
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.NavigationDrawerItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDrawerState
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.pondpedia.compose.pondpedia.presentation.PondPediaAppState
import com.pondpedia.compose.pondpedia.presentation.screens.home.ponds.components.viewmodel.PondDetailsEvent
import com.pondpedia.compose.pondpedia.presentation.screens.home.ponds.components.viewmodel.PondDetailsState
import com.pondpedia.compose.pondpedia.presentation.screens.home.ponds.components.viewmodel.PondsState
import com.pondpedia.compose.pondpedia.presentation.screens.home.ponds.screens.pond_details.components.AddCommodityDialog
import com.pondpedia.compose.pondpedia.presentation.screens.home.ponds.screens.pond_details.components.PondNavigationItem
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailsScreen(
    homeState: PondPediaAppState,
    pondsState: PondsState,
    pondDetailsState: PondDetailsState,
    onEvent: (PondDetailsEvent) -> Unit
    ) {

    onEvent(PondDetailsEvent.SetPondId(pondsState.pond.pondId))

    val items = listOf(
        PondNavigationItem.Overview,
        PondNavigationItem.Commodity,
        PondNavigationItem.Feeding,
        PondNavigationItem.Water,
    )
    
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val sheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true)
    var isAddRecordsSheetOpen by rememberSaveable {
        mutableStateOf(false)
    }
    var isAddCommoditySheetOpen by rememberSaveable {
        mutableStateOf(false)
    }


    val scope = rememberCoroutineScope()
    var selectedItemIndex by rememberSaveable {
        mutableStateOf(0)
    }

    if (pondDetailsState.isAddingCommodity) {
        AddCommodityDialog(pondDetailsState, onEvent)
    }

    ModalNavigationDrawer(
        drawerContent = {
            ModalDrawerSheet {
                Spacer(modifier = Modifier.height(16.dp))
                items.forEachIndexed { index, item ->
                    NavigationDrawerItem(
                        label = {
                                Text(text = stringResource(item.titleTextId))
                        },
                        selected = index == selectedItemIndex,
                        onClick = {
                            selectedItemIndex = index
                            scope.launch(Dispatchers.Main) {
                                drawerState.close()
                            }
                        },
                        icon = {
                            Icon(
                                imageVector = if (index == selectedItemIndex) {
                                    item.selectedIcon
                                } else {
                                    item.unselectedIcon
                                },
                                contentDescription = stringResource(item.titleTextId)
                            )
                        },
                        badge = {
                            item.badgeCount?.let {
                                Text(text = item.badgeCount.toString())
                            }
                        },
                        modifier = Modifier
                            .padding(NavigationDrawerItemDefaults.ItemPadding)
                    )
                }
            }
        },
        drawerState = drawerState
    ) {
        Scaffold(
            topBar = {
                CenterAlignedTopAppBar(
                    title = { 
                        Text(text = stringResource(id = items[selectedItemIndex].titleTextId))
                    },
                    navigationIcon = {
                        IconButton(
                            onClick = {
                                scope.launch(Dispatchers.Main) {
                                    drawerState.open()
                                }
                            }
                        ) {
                            Icon(
                                imageVector = Icons.Default.Menu,
                                contentDescription = "Menu"
                            )
                        }
                    },
                    actions = {
                        IconButton(onClick = {}) {
                            Icon(
                                imageVector = Icons.Default.MoreVert,
                                contentDescription = stringResource(id = items[selectedItemIndex].titleTextId) +" Actions",
                                tint = MaterialTheme.colorScheme.onSurface,
                            )
                        }
                    },
                )
            },
            bottomBar = {
                BottomAppBar(
                    actions = {
                        IconButton(
                            onClick = {
                                isAddCommoditySheetOpen = true
                            }
                        ) {
                            Icon(
                                imageVector = Icons.Default.Anchor,
                                contentDescription = "Add Commodity"
                            )
                        }
                        IconButton(
                            onClick = {}
                        ) {
                            Icon(
                                imageVector = Icons.Default.BrandingWatermark,
                                contentDescription = "Add Category"
                            )
                        }
                    },
                    floatingActionButton = {
                        FloatingActionButton(
                            onClick = {
                                isAddRecordsSheetOpen = true
                            }
                        ) {
                            Icon(
                                imageVector = Icons.Default.Add,
                                contentDescription = "Add Records"
                            )
                        }
                    }
                )
            },

        ) { innerPadding ->
            when (items[selectedItemIndex]) {
                PondNavigationItem.Overview -> {
                    PondOverviewScreen(
                        homeState = homeState,
                        pondsState = pondsState,
                        pondDetailsState = pondDetailsState,
                        onEvent = onEvent,
                        innerPadding = innerPadding
                    )
                }
                PondNavigationItem.Commodity -> {
                    if (pondDetailsState.commodity.isNotEmpty()) {
                        PondCommodityScreen(
                            homeState = homeState,
                            pondsState = pondsState,
                            pondDetailsState = pondDetailsState,
                            onEvent = onEvent,
                            innerPadding = innerPadding
                        )
                    } else {
                        Box(
                            modifier = Modifier
                                .fillMaxSize(),
                            contentAlignment = Alignment.Center
                        ) {
                            Column {
                                Box(
                                    modifier = Modifier
                                        .fillMaxWidth(),
                                    contentAlignment = Alignment.Center
                                ) {
                                    Text(
                                        text = "Komoditas tidak ditemukan!",
                                        fontWeight = FontWeight.Bold,
                                        fontSize = 20.sp
                                    )
                                }
                                Box(
                                    modifier = Modifier
                                        .fillMaxWidth(),
                                    contentAlignment = Alignment.Center
                                ) {
                                    Button(
                                        onClick = { onEvent(PondDetailsEvent.ShowDialog) }
                                    ) {
                                        Text(text = "Tambah Komoditas")
                                    }
                                }
                            }
                        }
                    }
                }
                PondNavigationItem.Feeding -> {

                    if (pondDetailsState.feedingRecords.isNotEmpty()) {
                        PondFeedingScreen(
                            homeState = homeState,
                            pondsState = pondsState,
                            pondDetailsState = pondDetailsState,
                            onEvent = onEvent,
                            innerPadding = innerPadding
                        )
                    } else {
                        Box(
                            modifier = Modifier
                                .fillMaxSize(),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(text = "Belum ada data pemberian pakan!", fontWeight = FontWeight.Bold, fontSize = 20.sp)
                        }
                    }
                }
                PondNavigationItem.Water -> {

                    if (pondDetailsState.waterRecords.isNotEmpty()) {
                        PondWaterScreen(
                            homeState = homeState,
                            pondsState = pondsState,
                            pondDetailsState = pondDetailsState,
                            onEvent = onEvent,
                            innerPadding = innerPadding
                        )
                    } else {
                        Box(
                            modifier = Modifier
                                .fillMaxSize(),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(text = "Belum ada data kualitas air!", fontWeight = FontWeight.Bold, fontSize = 20.sp)
                        }
                    }
                }
            }
            if (isAddRecordsSheetOpen) {
                ModalBottomSheet(
                    sheetState = sheetState,
                    onDismissRequest = {
                        isAddRecordsSheetOpen = false
                    },
                    modifier = Modifier.fillMaxSize()
                ) {
                    AddPondManagementRecordsTabScreen(pondDetailsState = pondDetailsState, onEvent = onEvent)
                }
            }
            if (isAddCommoditySheetOpen) {
                ModalBottomSheet(
                    sheetState = sheetState,
                    onDismissRequest = {
                        isAddCommoditySheetOpen = false
                    },
                    modifier = Modifier.fillMaxSize()
                ) {

                    if (pondDetailsState.commodity.isNotEmpty()) {
                        AddPondManagementCommmodityTabScreen(
                            pondDetailsState = pondDetailsState,
                            onEvent = onEvent
                        )
                    } else {
                        Column {
                            Box(
                                modifier = Modifier
                                    .fillMaxWidth(),
                                contentAlignment = Alignment.Center
                            ) {
                                Text(
                                    text = "Komoditas tidak ditemukan!",
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 20.sp
                                )
                            }
                            Box(
                                modifier = Modifier
                                    .fillMaxWidth(),
                                contentAlignment = Alignment.Center
                            ) {
                                Button(
                                    onClick = { onEvent(PondDetailsEvent.ShowDialog) }
                                ) {
                                    Text(text = "Tambah Komoditas")
                                }
                            }
                        }
                    }
                }
            }
        }

    }

}


