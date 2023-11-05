package com.pondpedia.compose.pondpedia.presentation.screens.home.ponds.screens.pond_details.screens

//import com.pondpedia.compose.pondpedia.presentation.components.MainState
//import com.pondpedia.compose.pondpedia.presentation.screens.home.components.TopActionBar
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.NavigationDrawerItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.pondpedia.compose.pondpedia.R
import com.pondpedia.compose.pondpedia.presentation.PondPediaAppState
import com.pondpedia.compose.pondpedia.presentation.screens.home.ponds.components.viewmodel.PondDetailsEvent
import com.pondpedia.compose.pondpedia.presentation.screens.home.ponds.components.viewmodel.PondDetailsState
import com.pondpedia.compose.pondpedia.presentation.screens.home.ponds.components.viewmodel.PondsState
import com.pondpedia.compose.pondpedia.presentation.screens.home.ponds.screens.pond_details.components.DetailsTabScreen
import com.pondpedia.compose.pondpedia.presentation.screens.home.ponds.screens.pond_details.components.PondNavigationItem
import com.pondpedia.compose.pondpedia.presentation.ui.theme.Black
import com.pondpedia.compose.pondpedia.presentation.ui.theme.Navi
import com.pondpedia.compose.pondpedia.presentation.ui.theme.White
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
    val scope = rememberCoroutineScope()
    var selectedItemIndex by rememberSaveable {
        mutableStateOf(0)
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
                            scope.launch {
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
                                scope.launch {
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
            }
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

                }
                PondNavigationItem.Feeding -> {

                }
                PondNavigationItem.Water -> {

                }
            }
        }
    }

}

@Composable
fun PondOverviewScreen(
    homeState: PondPediaAppState,
    pondsState: PondsState,
    pondDetailsState: PondDetailsState,
    onEvent: (PondDetailsEvent) -> Unit,
    innerPadding: PaddingValues
) {
    val navController = homeState.navController

    var showSettingsMenu by rememberSaveable {
        mutableStateOf(false)
    }

    Row(
        Modifier
            .fillMaxSize()
            .padding(innerPadding)
    ) {
        Column(
            Modifier.fillMaxSize()
        ) {
            Box(modifier = Modifier
                .fillMaxWidth()
                .height(250.dp)){
                PondDetailsCard(pondDetailsState = pondDetailsState)
            }
            Box(modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
            ){
                Card(
                    modifier = Modifier
                        .padding(8.dp)
                        .fillMaxWidth()
                        .fillMaxHeight(),
                ) {
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .align(Alignment.CenterHorizontally)
                            .padding(8.dp)
                            .border(1.dp, Black, RoundedCornerShape(4))
                            .padding(4.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        DetailsTabScreen(
                            pondDetailsState = pondDetailsState,
                        )
                    }
                }
            }
        }
    }

}


@Composable
fun PondDetailsCard(pondDetailsState: PondDetailsState) {
    Card(
        modifier = Modifier
            .padding(8.dp)
            .height(250.dp)
            .fillMaxWidth(),
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .align(Alignment.CenterHorizontally)
                .padding(8.dp)
                .border(1.dp, Black, RoundedCornerShape(4))
                .padding(4.dp),
            contentAlignment = Alignment.Center
        ) {
            Column(
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Bottom
            ) {

                Image(
                    painter = /*if (!pond.pondImageUrl.isNullOrEmpty()) rememberAsyncImagePainter(
                        model = pond.pondImageUrl
                    ) else */painterResource(R.drawable.pond_image_1),
                    contentDescription = "Pond Image",
                    contentScale = ContentScale.Fit,
                    modifier = Modifier
                        .width(200.dp)
                        .weight(1f)
                )

                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = pondDetailsState.pond.name ?: "\$pond_name",
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp,
                    color = MaterialTheme.colorScheme.onBackground,
                    overflow = TextOverflow.Ellipsis,
                    maxLines = 1,
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = "Jumlah Panen: ",
                    fontWeight = FontWeight.Normal,
                    color = MaterialTheme.colorScheme.onBackground,
                    overflow = TextOverflow.Ellipsis,
                    maxLines = 1,
                )
                Text(
                    text = pondDetailsState.pondRecords.firstOrNull()?.cycle ?: "-1",
                    fontWeight = FontWeight.Light,
                    color = MaterialTheme.colorScheme.onBackground,
                    style = TextStyle(fontStyle = FontStyle.Italic),
                    overflow = TextOverflow.Ellipsis,
                    maxLines = 1,
                )
                Spacer(modifier = Modifier.height(2.dp))

                Column(
                    modifier = Modifier,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = "Pembaruan Terakhir:",
                        fontWeight = FontWeight.Normal,
                        color = MaterialTheme.colorScheme.onBackground,
                        textAlign = TextAlign.Center
                    )
                    Text(
                        text = pondDetailsState.pond.updatedDate,
                        fontWeight = FontWeight.ExtraLight,
                        color = MaterialTheme.colorScheme.onBackground,
                        overflow = TextOverflow.Ellipsis,
                        maxLines = 1,
                    )
                }
            }
        }
    }
}


@Composable
fun DetailsScreenA (
    pondDetailsState: PondDetailsState,
) {

    /*val pond = pondsState.pondLogList.first {
        it.pondId == pondsState.pondId
    }.pondData*/

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top,
    ) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                horizontalAlignment = Alignment.Start,
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                Text(text = "Nama Kolam", fontWeight = FontWeight.SemiBold, maxLines = 1)
                Spacer(modifier = Modifier.height(2.dp))
                Text(
                    text = "pond.pondName",
                    fontWeight = FontWeight.Normal,
                    maxLines = 1
                )
            }
        }
        Spacer(modifier = Modifier.height(8.dp))
        Card(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                horizontalAlignment = Alignment.Start,
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                Text(text = "Panjang Kolam", fontWeight = FontWeight.SemiBold, maxLines = 1)
                Spacer(modifier = Modifier.height(2.dp))
                Text(
                    text = "\${pond.pondLength}",
                    fontWeight = FontWeight.Normal,
                    maxLines = 1
                )
            }
        }
        Spacer(modifier = Modifier.height(8.dp))
        Card(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                horizontalAlignment = Alignment.Start,
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                Text(text = "Lebar Kolam", fontWeight = FontWeight.SemiBold, maxLines = 1)
                Spacer(modifier = Modifier.height(2.dp))
                Text(
                    text = "{pond.pondWidth}",
                    fontWeight = FontWeight.Normal,
                    maxLines = 1
                )
            }
        }
        Spacer(modifier = Modifier.height(8.dp))
        Card(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                horizontalAlignment = Alignment.Start,
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                Text(text = "Kedalaman Kolam", fontWeight = FontWeight.SemiBold, maxLines = 1)
                Spacer(modifier = Modifier.height(2.dp))
                Text(
                    text = "{pond.pondDepth}",
                    fontWeight = FontWeight.Normal,
                    maxLines = 1
                )
            }
        }
        Spacer(modifier = Modifier.height(8.dp))
        Card(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                horizontalAlignment = Alignment.Start,
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                Text(text = "Derajat Suhu Air", fontWeight = FontWeight.SemiBold, maxLines = 1)
                Spacer(modifier = Modifier.height(2.dp))
                Text(
                    text = "{pond.pondWater.temperature}",
                    fontWeight = FontWeight.Normal,
                    maxLines = 1
                )
            }
        }
        Spacer(modifier = Modifier.height(8.dp))
        Card(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                horizontalAlignment = Alignment.Start,
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                Text(text = "Tingkat Kekeruhan Air", fontWeight = FontWeight.SemiBold, maxLines = 1)
                Spacer(modifier = Modifier.height(2.dp))
                Text(
                    text = "{pond.pondWater.turbidity}",
                    fontWeight = FontWeight.Normal,
                    maxLines = 1
                )
            }
        }
        Spacer(modifier = Modifier.height(8.dp))
        Card(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                horizontalAlignment = Alignment.Start,
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = "Kadar Oksigen Terlarut di Air",
                    fontWeight = FontWeight.SemiBold,
                    maxLines = 1
                )
                Spacer(modifier = Modifier.height(2.dp))
                Text(
                    text = "pond.pondWater.dissolvedOxygen}",
                    fontWeight = FontWeight.Normal,
                    maxLines = 1
                )
            }
        }
        Spacer(modifier = Modifier.height(8.dp))
        Card(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                horizontalAlignment = Alignment.Start,
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                Text(text = "Kadar pH Air", fontWeight = FontWeight.SemiBold, maxLines = 1)
                Spacer(modifier = Modifier.height(2.dp))
                Text(
                    text = "pond.pondWater.pH}",
                    fontWeight = FontWeight.Normal,
                    maxLines = 1
                )
            }
        }
        Spacer(modifier = Modifier.height(8.dp))
        Card(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                horizontalAlignment = Alignment.Start,
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                Text(text = "Kadar Ammonia di Air", fontWeight = FontWeight.SemiBold, maxLines = 1)
                Spacer(modifier = Modifier.height(2.dp))
                Text(
                    text = "pond.pondWater.ammonia}",
                    fontWeight = FontWeight.Normal,
                    maxLines = 1
                )
            }
        }
        Spacer(modifier = Modifier.height(8.dp))
        Card(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                horizontalAlignment = Alignment.Start,
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                Text(text = "Kadar Nitrat di Air", fontWeight = FontWeight.SemiBold, maxLines = 1)
                Spacer(modifier = Modifier.height(2.dp))
                Text(
                    text = "pond.pondWater.nitrate}",
                    fontWeight = FontWeight.Normal,
                    maxLines = 1
                )
            }
        }
        Spacer(modifier = Modifier.height(12.dp))
    }

}
@Composable
fun DetailsScreenB(
    pondDetailsState: PondDetailsState,
) {
    /*val pond = pondsState.pondLogList.first {
        it.pondId == pondsState.pondId
    }.pondData*/
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top,
    ) {
        Spacer(modifier = Modifier.height(8.dp))
        Card(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                horizontalAlignment = Alignment.Start,
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                Text(text = "Nama Umum Ikan", fontWeight = FontWeight.SemiBold, maxLines = 1)
                Spacer(modifier = Modifier.height(2.dp))
                Text(
                    text = "pond.pondFish.fishCommonName",
                    fontWeight = FontWeight.Normal,
                    maxLines = 1
                )
            }
        }
        Spacer(modifier = Modifier.height(8.dp))
        Card(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                horizontalAlignment = Alignment.Start,
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                Text(text = "Nama Ilmiah Ikan", fontWeight = FontWeight.SemiBold, maxLines = 1)
                Spacer(modifier = Modifier.height(2.dp))
                Text(
                    text = "pond.pondFish.fishScientificName}",
                    fontWeight = FontWeight.Normal,
                    maxLines = 1
                )
            }
        }
        Spacer(modifier = Modifier.height(8.dp))
        Card(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                horizontalAlignment = Alignment.Start,
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                Text(text = "Jumlah Ikan", fontWeight = FontWeight.SemiBold, maxLines = 1)
                Spacer(modifier = Modifier.height(2.dp))
                Text(
                    text = "pond.pondFish.fishAmount}",
                    fontWeight = FontWeight.Normal,
                    maxLines = 1
                )
            }
        }
        Spacer(modifier = Modifier.height(8.dp))
        Card(
            modifier = Modifier
                .fillMaxWidth()

        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                horizontalAlignment = Alignment.Start,
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                Text(text = "Berat Ikan (Target)", fontWeight = FontWeight.SemiBold, maxLines = 1)
                Spacer(modifier = Modifier.height(2.dp))
                Text(
                    text = "pond.pondFish.fishTargetWeight}",
                    fontWeight = FontWeight.Normal,
                    maxLines = 1
                )
            }
        }
        Spacer(modifier = Modifier.height(8.dp))
        Card(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                horizontalAlignment = Alignment.Start,
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                Text(text = "Berat Ikan (Sekarang)", fontWeight = FontWeight.SemiBold, maxLines = 1)
                Spacer(modifier = Modifier.height(2.dp))
                Text(
                    text = "pond.pondFish.fishCurrentWeight}",
                    fontWeight = FontWeight.Normal,
                    maxLines = 1
                )
            }
        }
        Spacer(modifier = Modifier.height(8.dp))
        Card(
            modifier = Modifier
                .fillMaxSize()
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                horizontalAlignment = Alignment.Start,
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = "Panjang Ikan (Sekarang)",
                    fontWeight = FontWeight.SemiBold,
                    maxLines = 1
                )
                Spacer(modifier = Modifier.height(2.dp))
                Text(
                    text = "pond.pondFish.fishCurrentLength}",
                    fontWeight = FontWeight.Normal,
                    maxLines = 1
                )
            }
        }
        Spacer(modifier = Modifier.height(12.dp))
    }
}


@Composable
fun DetailsScreenC(
    pondDetailsState: PondDetailsState,
) {
    /*val pond = pondsState.pondLogList.first {
        it.pondId == pondsState.pondId
    }.pondData*/
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top,
    ) {
        Spacer(modifier = Modifier.height(8.dp))
        Card(
            modifier = Modifier
                .fillMaxSize()
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                horizontalAlignment = Alignment.Start,
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                Text(text = "Jenis Pakan", fontWeight = FontWeight.SemiBold, maxLines = 1)
                Spacer(modifier = Modifier.height(2.dp))
                Text(
                    text = "pond.pondFeed.feedName",
                    fontWeight = FontWeight.Normal,
                    maxLines = 1
                )
            }
        }
        Spacer(modifier = Modifier.height(8.dp))
        Card(
            modifier = Modifier
                .fillMaxSize()
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                horizontalAlignment = Alignment.Start,
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = "Persentase Pakan / Berat Ikan",
                    fontWeight = FontWeight.SemiBold,
                    maxLines = 1
                )
                Spacer(modifier = Modifier.height(2.dp))
                Text(
                    text = "pond.pondFeed.feedPercentage}",
                    fontWeight = FontWeight.Normal,
                    maxLines = 1
                )
            }
        }
        Spacer(modifier = Modifier.height(8.dp))
        Card(
            modifier = Modifier
                .fillMaxSize()
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                horizontalAlignment = Alignment.Start,
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                Text(text = "Persentase Protein", fontWeight = FontWeight.SemiBold, maxLines = 1)
                Spacer(modifier = Modifier.height(2.dp))
                Text(
                    text = "pond.pondFeed.feedProteinPercentage}",
                    fontWeight = FontWeight.Normal,
                    maxLines = 1
                )
            }
        }
        Spacer(modifier = Modifier.height(8.dp))
        Card(
            modifier = Modifier
                .fillMaxSize()
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                horizontalAlignment = Alignment.Start,
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                Text(text = "Persentase Lipid", fontWeight = FontWeight.SemiBold, maxLines = 1)
                Spacer(modifier = Modifier.height(2.dp))
                Text(
                    text = "pond.pondFeed.feedLipidPercentage}",
                    fontWeight = FontWeight.Normal,
                    maxLines = 1
                )
            }
        }
        Spacer(modifier = Modifier.height(8.dp))
        Card(
            modifier = Modifier
                .fillMaxSize()
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                horizontalAlignment = Alignment.Start,
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = "Persentase Karbohidrat",
                    fontWeight = FontWeight.SemiBold,
                    maxLines = 1
                )
                Spacer(modifier = Modifier.height(2.dp))
                Text(
                    text = "pond.pondFeed.feedCarbohydratePercentage}",
                    fontWeight = FontWeight.Normal,
                    maxLines = 1
                )
            }
        }
        Spacer(modifier = Modifier.height(8.dp))
        Card(
            modifier = Modifier
                .fillMaxSize()
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                horizontalAlignment = Alignment.Start,
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = "Persentase Kandungan Lainnya",
                    fontWeight = FontWeight.SemiBold,
                    maxLines = 1
                )
                Spacer(modifier = Modifier.height(2.dp))
                Text(
                    text = "pond.pondFeed.feedOthersPercentage}",
                    fontWeight = FontWeight.Normal,
                    maxLines = 1
                )
            }
        }
        Spacer(modifier = Modifier.height(8.dp))
        Card(
            modifier = Modifier
                .fillMaxSize()
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                horizontalAlignment = Alignment.Start,
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = "Frekuensi pemberian pakan / hari",
                    fontWeight = FontWeight.SemiBold,
                    maxLines = 1
                )
                Spacer(modifier = Modifier.height(2.dp))
                Text(
                    text = "pond.pondFeed.feedFrequencyDaily}",
                    fontWeight = FontWeight.Normal,
                    maxLines = 1
                )
            }
        }
        Spacer(modifier = Modifier.height(12.dp))
    }
}

@Composable
fun DetailsScreenD(
    pondDetailsState: PondDetailsState,
) {
    /*val pond = pondsState.pondLogList.first {
        it.pondId == pondsState.pondId
    }.pondData*/
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top,
    ) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                horizontalAlignment = Alignment.Start,
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                Text(text = "Prediksi Pertumbuhan dari Ikan", fontWeight = FontWeight.Bold, maxLines = 1)
                Spacer(modifier = Modifier.height(12.dp))
                Button(
                    modifier = Modifier.fillMaxWidth(),
                    colors = ButtonDefaults.buttonColors(containerColor = Navi, contentColor = White),
                    shape = RoundedCornerShape(8.dp),
                    onClick = {

                    }) {
                    Text(text = "Prediksi Pertumbuhan", modifier = Modifier, fontWeight = FontWeight.Bold, color = White)
                }
                Spacer(modifier = Modifier.height(12.dp))

                Text(text = "Jumlah hari yang dibutuhkan untuk mencapai target:", fontWeight = FontWeight.SemiBold, maxLines = 1)
                Spacer(modifier = Modifier.height(2.dp))
                Text(
                    text = "pond.pondGrowthPrediction?.growthDays ?: Unavailable",
                    fontWeight = FontWeight.Normal,
                    maxLines = 1
                )
                Spacer(modifier = Modifier.height(6.dp))
                Text(text = "Jumlah pakan yang dibutuhkan untuk mencapai target::", fontWeight = FontWeight.SemiBold, maxLines = 1)
                Spacer(modifier = Modifier.height(2.dp))
                Text(
                    text = "pond.pondGrowthPrediction?.growthFeed ?: Unavailable",
                    fontWeight = FontWeight.Normal,
                    maxLines = 1
                )
                Spacer(modifier = Modifier.height(6.dp))
                Text(text = "Panjang dari ikan saat siap dipanen:", fontWeight = FontWeight.SemiBold, maxLines = 1)
                Spacer(modifier = Modifier.height(2.dp))
                Text(
                    text = "pond.pondGrowthPrediction?.growthLength ?: Unavailable",
                    fontWeight = FontWeight.Normal,
                    maxLines = 1
                )
            }
        }
        Spacer(modifier = Modifier.height(16.dp))

        Card(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                horizontalAlignment = Alignment.Start,
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                Text(text = "Prediksi Perubahan dari Kualitas Air", fontWeight = FontWeight.Bold, maxLines = 1)
                Spacer(modifier = Modifier.height(12.dp))
                Button(
                    modifier = Modifier.fillMaxWidth(),
                    colors = ButtonDefaults.buttonColors(containerColor = Navi, contentColor = White),
                    shape = RoundedCornerShape(8.dp),
                    onClick = {

                    }) {
                    Text(text = "Prediksi Perubahan", modifier = Modifier, fontWeight = FontWeight.Bold, color = White)
                }
                Spacer(modifier = Modifier.height(12.dp))

                Text(text = " Temperatur Air:", fontWeight = FontWeight.SemiBold, maxLines = 1)
                Spacer(modifier = Modifier.height(2.dp))
                Text(
                    text = "pond.pondWaterPrediction?.temperature ?: Unavailable",
                    fontWeight = FontWeight.Normal,
                    maxLines = 1
                )
                Spacer(modifier = Modifier.height(6.dp))
                Text(text = " Kekeruhan Air:", fontWeight = FontWeight.SemiBold, maxLines = 1)
                Spacer(modifier = Modifier.height(2.dp))
                Text(
                    text = "pond.pondWaterPrediction?.turbidity ?: }",
                    fontWeight = FontWeight.Normal,
                    maxLines = 1
                )
                Spacer(modifier = Modifier.height(6.dp))
                Text(text = " Oksigen Terlarut dalam Air:", fontWeight = FontWeight.SemiBold, maxLines = 1)
                Spacer(modifier = Modifier.height(2.dp))
                Text(
                    text = "pond.pondWaterPrediction?.dissolvedOxygen ?: Unavailable",
                    fontWeight = FontWeight.Normal,
                    maxLines = 1
                )
                Spacer(modifier = Modifier.height(6.dp))
                Text(text = " Kadar pH dari Air:", fontWeight = FontWeight.SemiBold, maxLines = 1)
                Spacer(modifier = Modifier.height(2.dp))
                Text(
                    text = "nd.pondWaterPrediction?.pH ?: Unavailable",
                    fontWeight = FontWeight.Normal,
                    maxLines = 1
                )
                Spacer(modifier = Modifier.height(6.dp))
                Text(text = "Ammonia dalam Air:", fontWeight = FontWeight.SemiBold, maxLines = 1)
                Spacer(modifier = Modifier.height(2.dp))
                Text(
                    text = "pond.pondWaterPrediction?.ammonia ?:Unavailable",
                    fontWeight = FontWeight.Normal,
                    maxLines = 1
                )
                Spacer(modifier = Modifier.height(6.dp))
                Text(text = " Nitrat dalam Air:", fontWeight = FontWeight.SemiBold, maxLines = 1)
                Spacer(modifier = Modifier.height(2.dp))
                Text(
                    text = "pond.pondWaterPrediction?.nitrate ?: Unavailable",
                    fontWeight = FontWeight.Normal,
                    maxLines = 1
                )
                Spacer(modifier = Modifier.height(6.dp))
            }
        }
        Spacer(modifier = Modifier.height(16.dp))
    }
}
