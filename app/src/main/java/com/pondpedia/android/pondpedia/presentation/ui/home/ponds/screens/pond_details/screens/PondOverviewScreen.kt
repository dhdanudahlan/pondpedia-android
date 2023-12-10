package com.pondpedia.android.pondpedia.presentation.ui.home.ponds.screens.pond_details.screens

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
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.pondpedia.android.pondpedia.R
import com.pondpedia.android.pondpedia.presentation.PondPediaAppState
import com.pondpedia.android.pondpedia.presentation.ui.home.ponds.components.viewmodel.PondDetailsEvent
import com.pondpedia.android.pondpedia.presentation.ui.home.ponds.components.viewmodel.PondDetailsState
import com.pondpedia.android.pondpedia.presentation.ui.home.ponds.components.viewmodel.PondsState
import com.pondpedia.android.pondpedia.presentation.ui.home.ponds.screens.pond_details.components.PondTab
import com.pondpedia.android.pondpedia.presentation.theme.Black

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
fun DetailsTabScreen(
    pondDetailsState: PondDetailsState,
) {
    var selectedPondTab by remember { mutableStateOf(PondTab.PondTabCommodity) }
    var tabIndex by remember { mutableStateOf(0) }
    val pondTabs =
        listOf(
            PondTab.PondTabCommodity,
            PondTab.PondTabFeeding,
            PondTab.PondTabWater,
        )


    Column(modifier = Modifier.fillMaxWidth()) {
        TabRow(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.CenterHorizontally)
                .clip(RoundedCornerShape(16)),
            selectedTabIndex = tabIndex
        ) {
            pondTabs.forEachIndexed { index, tab ->
                Tab(
                    text = { Text(text = tab.title, fontSize = 12.sp, maxLines = 1) },
                    selected = selectedPondTab == tab,
                    onClick = {
                        selectedPondTab = tab
                        tabIndex = index
                    },
                )
            }
        }
        when (selectedPondTab) {
            PondTab.PondTabCommodity -> {

                if (pondDetailsState.commodity.isNotEmpty()) {
                    DetailsScreenCommodity(pondDetailsState = pondDetailsState)
                } else {
                    Box(
                        modifier = Modifier
                            .fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(text = "Data tidak ditemukan!", fontWeight = FontWeight.Bold, fontSize = 10.sp)
                    }
                }
            }
            PondTab.PondTabFeeding -> {

                if (pondDetailsState.feedingRecords.isNotEmpty()) {
                    DetailsScreenFeeding(pondDetailsState = pondDetailsState)
                } else {
                    Box(
                        modifier = Modifier
                            .fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(text = "Data tidak ditemukan!", fontWeight = FontWeight.Bold, fontSize = 10.sp)
                    }
                }
            }
            PondTab.PondTabWater -> {

                if (pondDetailsState.waterRecords.isNotEmpty()) {
                    DetailsScreenWater(pondDetailsState = pondDetailsState)

                } else {
                    Box(
                        modifier = Modifier
                            .fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(text = "Data tidak ditemukan!", fontWeight = FontWeight.Bold, fontSize = 10.sp)
                    }
                }
            }
        }
    }
}
@Composable
fun DetailsScreenCommodity (
    pondDetailsState: PondDetailsState,
) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top,
    ) {
        pondDetailsState.commodity.forEachIndexed { index, commodity ->
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
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(8.dp),
                        horizontalAlignment = Alignment.Start,
                        verticalArrangement = Arrangement.SpaceAround
                    ) {
                        Text(
                            text = "Commodity ${index + 1}: ${commodity.name}",
                            fontWeight = FontWeight.Bold,
                            maxLines = 1
                        )
                        Spacer(modifier = Modifier.height(8.dp))

                        Text(
                            text = "Tanggal Penyebaran: ${commodity.date} | Jumlah: ${commodity.quantity} ekor",
                            fontWeight = FontWeight.Normal,
                            maxLines = 1
                        )
                        Spacer(modifier = Modifier.height(4.dp))

                    }
                }
            }
        }
    }
}
@Composable
fun DetailsScreenFeeding(
    pondDetailsState: PondDetailsState,
) {
    val listOfRecords = pondDetailsState.feedingRecords
    val records = listOfRecords.last()
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top,
    ) {

        Card(
            modifier = Modifier
                .padding(8.dp)
                .fillMaxWidth()
                .fillMaxHeight(),
        ) {
            Column {
                Text(
                    text = "| Data Terakhir |",
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = "${records.date}",
                    fontWeight = FontWeight.SemiBold
                )
                Text(text = "Nama Pakan : ${records.feedId}")
                Text(text = "Jumlah Pakan : ${records.quantity}")
                Text(text = "Catatan : ${records.note}")
            }

        }
    }
}


@Composable
fun DetailsScreenWater(
    pondDetailsState: PondDetailsState,
) {
    val listOfRecords = pondDetailsState.waterRecords
    val records = listOfRecords.last()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top,
    ) {
        Card(
            modifier = Modifier
                .padding(8.dp)
                .fillMaxWidth()
                .fillMaxHeight(),
        ) {
            Column {
                Text(
                    text = "| Data Terakhir |",
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = "${records.date}",
                    fontWeight = FontWeight.SemiBold
                )
                Text(text = "Ketinggian Air : ${records.level}")
                Text(text = "Kualitas Air : ${records.quality}")
                Text(text = "Warna Air : ${records.color}")
                Text(text = "Catatan : ${records.note}")
            }

        }
    }
}
