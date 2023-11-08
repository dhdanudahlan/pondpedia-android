package com.pondpedia.compose.pondpedia.presentation.screens.home.ponds.screens.pond_details.screens.update.screen
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.pondpedia.compose.pondpedia.presentation.ui.theme.PondPediaCustomTheme

@Preview(showBackground = true)
@Composable
fun UpdateScreenPreview() {
    PondPediaCustomTheme(darkTheme = false) {
        UpdateScreen(
//            mainState = MainState(),
            /*pondsState = PondDetailsState(
                pondLogList = PondDummyGenerator.getDummyPondLogList(5)
            ),*/
            /*pondState = PondDetailsState(
                pondCreateTabList = listOf("Kolam", "Ikan", "Air", "Pakan"),
            ),*/
            onTabIndexSelected = {},
            setDisplayActionMenu = {},
            setDisplayActionScreen = {},
        ) {}
    }
}
@Composable
fun UpdateScreen(
//    mainState: MainState,
    /*pondsState: PondDetailsState,
    pondState: PondDetailsState,*/
    onTabIndexSelected: (Int) -> Unit,
    setDisplayActionMenu: (Boolean) -> Unit,
    setDisplayActionScreen: (Boolean) -> Unit,
    onPondSaved: (String) -> Unit
) {
    Scaffold(
        topBar = {
//            TopActionBar(
//                mainState = mainState,
//                pondsState = pondsState,
//                pondState = pondState,
//                setDisplayActionMenu = setDisplayActionMenu,
//                setDisplayActionScreen = setDisplayActionMenu,
//                onRouteChanged = {},
//                onPondSaved = onPondSaved,
//                onPondDeleted = {},
//            )
        }
    ) { innerPadding ->
        Box(modifier = Modifier.padding(innerPadding)) {
            Column(
                modifier = Modifier.fillMaxSize(),
            ) {
                UpdateTabs(
                    /*pondState = pondState,*/
                    onTabIndexSelected = onTabIndexSelected,
                )
            }
        }
    }
}

@Composable
fun UpdateTabs(
    /*pondState: PondDetailsState,*/
    onTabIndexSelected: (Int) -> Unit,
) {
    /*val selectedTabIndex = remember(pondState.selectedTabIndex) { pondState.selectedTabIndex }

    TabRow(selectedTabIndex = selectedTabIndex) {
        pondState.pondCreateTabList.forEachIndexed { index, tab ->
            PondTab(
                text = {
                    Text(text = tab, fontSize = 12.sp, maxLines = 1)
                },
                selected = tab == pondState.pondCreateTabList[selectedTabIndex],
                onClick = { onTabIndexSelected(index)}
            )
        }
    }*/
}

//@Composable
//fun CreateScreenB(
//    pondsViewModel: PondsViewModel,
//    navController: NavHostController,
//    onCreatePond: () -> Unit,
//) {
//
//    val pond = pondsViewModel.state.value.pondLogList.filter {
//        it.pondId == pondsViewModel.state.value.pondId
//    }.first().pondData
//
//    var waterTemperature by rememberSaveable { mutableStateOf(pond.pondWater.temperature) }
//    var waterTurbidity by rememberSaveable { mutableStateOf(pond.pondWater.turbidity) }
//    var waterDissolvedOxygen by rememberSaveable { mutableStateOf(pond.pondWater.dissolvedOxygen) }
//    var waterPH by rememberSaveable { mutableStateOf(pond.pondWater.pH) }
//    var waterAmmonia by rememberSaveable { mutableStateOf(pond.pondWater.ammonia) }
//    var waterNitrate by rememberSaveable { mutableStateOf(pond.pondWater.nitrate) }
//
//    Column(
//        modifier = Modifier
//            .fillMaxSize()
//            .verticalScroll(rememberScrollState()),
//        horizontalAlignment = Alignment.CenterHorizontally,
//        verticalArrangement = Arrangement.Top,
//    ) {
//        Spacer(modifier = Modifier.height(8.dp))
//        Card(
//            modifier = Modifier
//                .fillMaxWidth()
//        ) {
//            TextField(
//                value = waterTemperature.toString(),
//                onValueChange = { waterTemperature = it.toFloat()},
//                label = { Text("Derajat suhu Air") },
//                modifier = Modifier.fillMaxWidth()
//            )
//        }
//        Spacer(modifier = Modifier.height(8.dp))
//        Card(
//            modifier = Modifier
//                .fillMaxWidth()
//        ) {
//            TextField(
//                value = waterTurbidity.toString(),
//                onValueChange = { waterTurbidity = it.toFloat() },
//                label = { Text("Tingkat kekeruhan air") },
//                modifier = Modifier.fillMaxWidth()
//            )
//        }
//        Spacer(modifier = Modifier.height(8.dp))
//        Card(
//            modifier = Modifier
//                .fillMaxWidth()
//        ) {
//            TextField(
//                value = waterDissolvedOxygen.toString(),
//                onValueChange = { waterDissolvedOxygen = it.toFloat()},
//                label = { Text("Kadar oksigen terlarut di air") },
//                modifier = Modifier.fillMaxWidth()
//            )
//        }
//        Spacer(modifier = Modifier.height(8.dp))
//        Card(
//            modifier = Modifier
//                .fillMaxWidth()
//        ) {
//            TextField(
//                value = waterPH.toString(),
//                onValueChange = { waterPH = it.toFloat()},
//                label = { Text("Kadar pH di air") },
//                modifier = Modifier.fillMaxWidth()
//            )
//        }
//        Spacer(modifier = Modifier.height(8.dp))
//        Card(
//            modifier = Modifier
//                .fillMaxWidth()
//        ) {
//            TextField(
//                value = waterAmmonia.toString(),
//                onValueChange = { waterAmmonia = it.toFloat()},
//                label = { Text("Kadar ammonia di air") },
//                modifier = Modifier.fillMaxWidth()
//            )
//        }
//        Spacer(modifier = Modifier.height(8.dp))
//        Card(
//            modifier = Modifier
//                .fillMaxWidth()
//        ) {
//            TextField(
//                value = waterNitrate.toString(),
//                onValueChange = { waterNitrate = it.toFloat()},
//                label = { Text("Kadar Nitrat di air") },
//                modifier = Modifier.fillMaxWidth()
//            )
//        }
//        Spacer(modifier = Modifier.height(12.dp))
//    }
//
//}
//@Composable
//fun CreateScreenA(
//    pondsViewModel: PondsViewModel,
//    navController: NavHostController,
//    onCreatePond: () -> Unit,
//) {
//
//    val pond = pondsViewModel.state.value.pondLogList.filter {
//        it.pondId == pondsViewModel.state.value.pondId
//    }.first().pondData
//
//    var fishId by rememberSaveable { mutableStateOf(pond.pondFish.fishId) }
//    var fishCommonName by rememberSaveable { mutableStateOf(pond.pondFish.fishCommonName) }
//    var fishScientificName by rememberSaveable { mutableStateOf(pond.pondFish.fishScientificName) }
//    var fishAmount by rememberSaveable { mutableStateOf(pond.pondFish.fishAmount) }
//    var fishTargetWeight by rememberSaveable { mutableStateOf(pond.pondFish.fishTargetWeight) }
//    var fishCurrentWeight by rememberSaveable { mutableStateOf(pond.pondFish.fishCurrentWeight) }
//    var fishCurrentLength by rememberSaveable { mutableStateOf(pond.pondFish.fishCurrentLength) }
//
//    Column(
//        modifier = Modifier
//            .fillMaxSize()
//            .verticalScroll(rememberScrollState()),
//        horizontalAlignment = Alignment.CenterHorizontally,
//        verticalArrangement = Arrangement.Top,
//
//        ) {
//        Card(
//            modifier = Modifier
//                .fillMaxWidth()
//        ) {
//            TextField(
//                value = fishCommonName,
//                onValueChange = {
//                    fishCommonName = it
//                },
//                label = { Text("Commodity common name") },
//                readOnly = true,
//                modifier = Modifier.fillMaxWidth(),
//            )
//        }
//        Spacer(modifier = Modifier.height(8.dp))
//        Card(
//            modifier = Modifier
//                .fillMaxWidth()
//        ) {
//            TextField(
//                value = fishScientificName,
//                onValueChange = {
//                    fishScientificName = it
//                },
//                label = { Text("Commodity scientific name") },
//                readOnly = true,
//                modifier = Modifier.fillMaxWidth(),
//            )
//        }
//        Spacer(modifier = Modifier.height(8.dp))
//        Card(
//            modifier = Modifier
//                .fillMaxWidth()
//        ) {
//            TextField(
//                value = fishAmount.toString(),
//                onValueChange = { fishAmount = it.toInt() },
//                label = { Text("Commodity amount") },
//                modifier = Modifier.fillMaxWidth(),
//            )
//        }
//        Spacer(modifier = Modifier.height(8.dp))
//        Card(
//            modifier = Modifier
//                .fillMaxWidth()
//        ) {
//            TextField(
//                value = fishTargetWeight.toString(),
//                onValueChange = {
//                    fishTargetWeight = it.toFloat()
//                },
//                label = { Text("Commodity target weight") },
//                modifier = Modifier.fillMaxWidth(),
//            )
//        }
//        Spacer(modifier = Modifier.height(8.dp))
//        Card(
//            modifier = Modifier
//                .fillMaxWidth()
//        ) {
//            TextField(
//                value = fishCurrentLength.toString(),
//                onValueChange = {
//                    fishCurrentWeight = it.toFloat()
//                },
//                label = { Text("Commodity current weight") },
//                modifier = Modifier.fillMaxWidth(),
//            )
//        }
//        Spacer(modifier = Modifier.height(8.dp))
//        Card(
//            modifier = Modifier
//                .fillMaxWidth()
//        ) {
//            TextField(
//                value = fishCurrentLength.toString(),
//                onValueChange = {
//                    fishCurrentLength = it.toFloat()
//                },
//                label = { Text("Commodity current length") },
//                modifier = Modifier.fillMaxWidth(),
//            )
//        }
//        Spacer(modifier = Modifier.height(12.dp))
//    }
//}
//
//
//@Composable
//fun CreateScreenC(
//    pondsViewModel: PondsViewModel,
//    navController: NavHostController,
//    onCreatePond: () -> Unit,
//) {
//
//    val pond = pondsViewModel.state.value.pondLogList.filter {
//        it.pondId == pondsViewModel.state.value.pondId
//    }.first().pondData
//
//    var feedName by rememberSaveable { mutableStateOf(pond.pondFeed.feedName) }
//    var feedPercentage by rememberSaveable { mutableStateOf(pond.pondFeed.feedPercentage) }
//    var feedProteinPercentage by rememberSaveable { mutableStateOf(pond.pondFeed.feedProteinPercentage) }
//    var feedLipidPercentage by rememberSaveable { mutableStateOf(pond.pondFeed.feedLipidPercentage) }
//    var feedCarbohydratePercentage by rememberSaveable { mutableStateOf(pond.pondFeed.feedCarbohydratePercentage) }
//    var feedOthersPercentage by rememberSaveable { mutableStateOf(pond.pondFeed.feedOthersPercentage) }
//    var feedFrequencyDaily by rememberSaveable { mutableStateOf(pond.pondFeed.feedFrequencyDaily) }
//
//    Column(
//        modifier = Modifier
//            .fillMaxSize()
//            .verticalScroll(rememberScrollState()),
//        horizontalAlignment = Alignment.CenterHorizontally,
//        verticalArrangement = Arrangement.Top,
//    ) {
//        Card(
//            modifier = Modifier
//                .fillMaxWidth()
//        ) {
//            TextField(
//                value = feedName,
//                onValueChange = { feedName = it},
//                label = { Text("Nama Pakan") },
//                readOnly = true,
//                modifier = Modifier.fillMaxWidth(),
//            )
//        }
//        Spacer(modifier = Modifier.height(8.dp))
//        Card(
//            modifier = Modifier
//                .fillMaxWidth()
//        ) {
//            TextField(
//                value = feedPercentage.toString(),
//                onValueChange = {
//                    feedPercentage = it.toFloat()
//                },
//                label = { Text("Persentase Pakan / Berat Badan") },
//                readOnly = true,
//                modifier = Modifier.fillMaxWidth(),
//            )
//        }
//        Spacer(modifier = Modifier.height(8.dp))
//        Card(
//            modifier = Modifier
//                .fillMaxWidth()
//        ) {
//            TextField(
//                value = feedProteinPercentage.toString(),
//                onValueChange = {
//                    feedProteinPercentage = it.toFloat()
//                },
//                label = { Text("Persentase Protein pada Pakan") },
//                readOnly = true,
//                modifier = Modifier.fillMaxWidth(),
//            )
//        }
//        Spacer(modifier = Modifier.height(8.dp))
//        Card(
//            modifier = Modifier
//                .fillMaxWidth()
//        ) {
//            TextField(
//                value = feedLipidPercentage.toString(),
//                onValueChange = {
//                    feedLipidPercentage = it.toFloat()
//                },
//                label = { Text("Persentase Lipid pada Pakan") },
//                readOnly = true,
//                modifier = Modifier.fillMaxWidth(),
//            )
//        }
//        Spacer(modifier = Modifier.height(8.dp))
//        Card(
//            modifier = Modifier
//                .fillMaxWidth()
//        ) {
//            TextField(
//                value = feedCarbohydratePercentage.toString(),
//                onValueChange = {
//                    feedCarbohydratePercentage = it.toFloat()
//                },
//                label = { Text("Persentase Carbohydrate pada pakan") },
//                readOnly = true,
//                modifier = Modifier.fillMaxWidth(),
//            )
//        }
//        Spacer(modifier = Modifier.height(8.dp))
//        Card(
//            modifier = Modifier
//                .fillMaxWidth()
//        ) {
//            TextField(
//                value = feedOthersPercentage.toString(),
//                onValueChange = {
//                    feedOthersPercentage = it.toFloat()
//                },
//                label = { Text("Persentase faktor lain pada pakan") },
//                readOnly = true,
//                modifier = Modifier.fillMaxWidth(),
//            )
//        }
//        Spacer(modifier = Modifier.height(8.dp))
//        Card(
//            modifier = Modifier
//                .fillMaxWidth()
//        ) {
//            TextField(
//                value = feedFrequencyDaily.toString(),
//                onValueChange = {
//                    feedFrequencyDaily = it.toInt()
//                },
//                label = { Text("Pemberian pakan dalam sehari") },
//                readOnly = true,
//                modifier = Modifier.fillMaxWidth(),
//            )
//        }
//        Spacer(modifier = Modifier.height(12.dp))
//    }
//}
//
//@Composable
//fun CreateScreenD(
//    pondsViewModel: PondsViewModel,
//    navController: NavHostController,
//    onCreatePond: () -> Unit,
//) {
//
//    val pond = pondsViewModel.state.value.pondLogList.filter {
//        it.pondId == pondsViewModel.state.value.pondId
//    }.first().pondData
//
//    Column(
//        modifier = Modifier
//            .fillMaxSize()
//            .verticalScroll(rememberScrollState()),
//        horizontalAlignment = Alignment.CenterHorizontally,
//        verticalArrangement = Arrangement.Top,
//    ) {
//        Card(
//            modifier = Modifier
//                .fillMaxWidth()
//        ) {
//            Column(
//                modifier = Modifier
//                    .fillMaxWidth()
//                    .padding(8.dp),
//                horizontalAlignment = Alignment.Start,
//                verticalArrangement = Arrangement.SpaceBetween
//            ) {
//                Text(text = "Nama Kolam", fontWeight = FontWeight.SemiBold, maxLines = 1)
//                Spacer(modifier = Modifier.height(2.dp))
//                Text(
//                    text = pond.pondName,
//                    fontWeight = FontWeight.Normal,
//                    maxLines = 1
//                )
//            }
//        }
//        Spacer(modifier = Modifier.height(8.dp))
//        Card(
//            modifier = Modifier
//                .fillMaxWidth()
//        ) {
//            Column(
//                modifier = Modifier
//                    .fillMaxWidth()
//                    .padding(8.dp),
//                horizontalAlignment = Alignment.Start,
//                verticalArrangement = Arrangement.SpaceBetween
//            ) {
//                Text(text = "Panjang Kolam", fontWeight = FontWeight.SemiBold, maxLines = 1)
//                Spacer(modifier = Modifier.height(2.dp))
//                Text(
//                    text = "${pond.pondLength}",
//                    fontWeight = FontWeight.Normal,
//                    maxLines = 1
//                )
//            }
//        }
//        Spacer(modifier = Modifier.height(8.dp))
//        Card(
//            modifier = Modifier
//                .fillMaxWidth()
//        ) {
//            Column(
//                modifier = Modifier
//                    .fillMaxWidth()
//                    .padding(8.dp),
//                horizontalAlignment = Alignment.Start,
//                verticalArrangement = Arrangement.SpaceBetween
//            ) {
//                Text(text = "Lebar Kolam", fontWeight = FontWeight.SemiBold, maxLines = 1)
//                Spacer(modifier = Modifier.height(2.dp))
//                Text(
//                    text = "${pond.pondWidth}",
//                    fontWeight = FontWeight.Normal,
//                    maxLines = 1
//                )
//            }
//        }
//        Spacer(modifier = Modifier.height(8.dp))
//        Card(
//            modifier = Modifier
//                .fillMaxWidth()
//        ) {
//            Column(
//                modifier = Modifier
//                    .fillMaxWidth()
//                    .padding(8.dp),
//                horizontalAlignment = Alignment.Start,
//                verticalArrangement = Arrangement.SpaceBetween
//            ) {
//                Text(text = "Kedalaman Kolam", fontWeight = FontWeight.SemiBold, maxLines = 1)
//                Spacer(modifier = Modifier.height(2.dp))
//                Text(
//                    text = "${pond.pondDepth}",
//                    fontWeight = FontWeight.Normal,
//                    maxLines = 1
//                )
//            }
//        }
//        Spacer(modifier = Modifier.height(8.dp))
//        Card(
//            modifier = Modifier
//                .fillMaxWidth()
//        ) {
//            Column(
//                modifier = Modifier
//                    .fillMaxWidth()
//                    .padding(8.dp),
//                horizontalAlignment = Alignment.Start,
//                verticalArrangement = Arrangement.SpaceBetween
//            ) {
//                Text(text = "Derajat Suhu Air", fontWeight = FontWeight.SemiBold, maxLines = 1)
//                Spacer(modifier = Modifier.height(2.dp))
//                Text(
//                    text = "${pond.pondWater.temperature}",
//                    fontWeight = FontWeight.Normal,
//                    maxLines = 1
//                )
//            }
//        }
//        Spacer(modifier = Modifier.height(8.dp))
//        Card(
//            modifier = Modifier
//                .fillMaxWidth()
//        ) {
//            Column(
//                modifier = Modifier
//                    .fillMaxWidth()
//                    .padding(8.dp),
//                horizontalAlignment = Alignment.Start,
//                verticalArrangement = Arrangement.SpaceBetween
//            ) {
//                Text(text = "Tingkat Kekeruhan Air", fontWeight = FontWeight.SemiBold, maxLines = 1)
//                Spacer(modifier = Modifier.height(2.dp))
//                Text(
//                    text = "${pond.pondWater.turbidity}",
//                    fontWeight = FontWeight.Normal,
//                    maxLines = 1
//                )
//            }
//        }
//        Spacer(modifier = Modifier.height(8.dp))
//        Card(
//            modifier = Modifier
//                .fillMaxWidth()
//        ) {
//            Column(
//                modifier = Modifier
//                    .fillMaxWidth()
//                    .padding(8.dp),
//                horizontalAlignment = Alignment.Start,
//                verticalArrangement = Arrangement.SpaceBetween
//            ) {
//                Text(
//                    text = "Kadar Oksigen Terlarut di Air",
//                    fontWeight = FontWeight.SemiBold,
//                    maxLines = 1
//                )
//                Spacer(modifier = Modifier.height(2.dp))
//                Text(
//                    text = "${pond.pondWater.dissolvedOxygen}",
//                    fontWeight = FontWeight.Normal,
//                    maxLines = 1
//                )
//            }
//        }
//        Spacer(modifier = Modifier.height(8.dp))
//        Card(
//            modifier = Modifier
//                .fillMaxWidth()
//        ) {
//            Column(
//                modifier = Modifier
//                    .fillMaxWidth()
//                    .padding(8.dp),
//                horizontalAlignment = Alignment.Start,
//                verticalArrangement = Arrangement.SpaceBetween
//            ) {
//                Text(text = "Kadar pH Air", fontWeight = FontWeight.SemiBold, maxLines = 1)
//                Spacer(modifier = Modifier.height(2.dp))
//                Text(
//                    text = "${pond.pondWater.pH}",
//                    fontWeight = FontWeight.Normal,
//                    maxLines = 1
//                )
//            }
//        }
//        Spacer(modifier = Modifier.height(8.dp))
//        Card(
//            modifier = Modifier
//                .fillMaxWidth()
//        ) {
//            Column(
//                modifier = Modifier
//                    .fillMaxWidth()
//                    .padding(8.dp),
//                horizontalAlignment = Alignment.Start,
//                verticalArrangement = Arrangement.SpaceBetween
//            ) {
//                Text(text = "Kadar Ammonia di Air", fontWeight = FontWeight.SemiBold, maxLines = 1)
//                Spacer(modifier = Modifier.height(2.dp))
//                Text(
//                    text = "${pond.pondWater.ammonia}",
//                    fontWeight = FontWeight.Normal,
//                    maxLines = 1
//                )
//            }
//        }
//        Spacer(modifier = Modifier.height(8.dp))
//        Card(
//            modifier = Modifier
//                .fillMaxWidth()
//        ) {
//            Column(
//                modifier = Modifier
//                    .fillMaxWidth()
//                    .padding(8.dp),
//                horizontalAlignment = Alignment.Start,
//                verticalArrangement = Arrangement.SpaceBetween
//            ) {
//                Text(text = "Kadar Nitrat di Air", fontWeight = FontWeight.SemiBold, maxLines = 1)
//                Spacer(modifier = Modifier.height(2.dp))
//                Text(
//                    text = "${pond.pondWater.nitrate}",
//                    fontWeight = FontWeight.Normal,
//                    maxLines = 1
//                )
//            }
//        }
//        Spacer(modifier = Modifier.height(8.dp))
//        Card(
//            modifier = Modifier
//                .fillMaxWidth()
//        ) {
//            Column(
//                modifier = Modifier
//                    .fillMaxWidth()
//                    .padding(8.dp),
//                horizontalAlignment = Alignment.Start,
//                verticalArrangement = Arrangement.SpaceBetween
//            ) {
//                Text(text = "Nama Umum Ikan", fontWeight = FontWeight.SemiBold, maxLines = 1)
//                Spacer(modifier = Modifier.height(2.dp))
//                Text(
//                    text = pond.pondFish.fishCommonName,
//                    fontWeight = FontWeight.Normal,
//                    maxLines = 1
//                )
//            }
//        }
//        Spacer(modifier = Modifier.height(8.dp))
//        Card(
//            modifier = Modifier
//                .fillMaxWidth()
//        ) {
//            Column(
//                modifier = Modifier
//                    .fillMaxWidth()
//                    .padding(8.dp),
//                horizontalAlignment = Alignment.Start,
//                verticalArrangement = Arrangement.SpaceBetween
//            ) {
//                Text(text = "Nama Ilmiah Ikan", fontWeight = FontWeight.SemiBold, maxLines = 1)
//                Spacer(modifier = Modifier.height(2.dp))
//                Text(
//                    text = "${pond.pondFish.fishScientificName}",
//                    fontWeight = FontWeight.Normal,
//                    maxLines = 1
//                )
//            }
//        }
//        Spacer(modifier = Modifier.height(8.dp))
//        Card(
//            modifier = Modifier
//                .fillMaxWidth()
//        ) {
//            Column(
//                modifier = Modifier
//                    .fillMaxWidth()
//                    .padding(8.dp),
//                horizontalAlignment = Alignment.Start,
//                verticalArrangement = Arrangement.SpaceBetween
//            ) {
//                Text(text = "Jumlah Ikan", fontWeight = FontWeight.SemiBold, maxLines = 1)
//                Spacer(modifier = Modifier.height(2.dp))
//                Text(
//                    text = "${pond.pondFish.fishAmount}",
//                    fontWeight = FontWeight.Normal,
//                    maxLines = 1
//                )
//            }
//        }
//        Spacer(modifier = Modifier.height(8.dp))
//        Card(
//            modifier = Modifier
//                .fillMaxWidth()
//
//        ) {
//            Column(
//                modifier = Modifier
//                    .fillMaxWidth()
//                    .padding(8.dp),
//                horizontalAlignment = Alignment.Start,
//                verticalArrangement = Arrangement.SpaceBetween
//            ) {
//                Text(text = "Berat Ikan (Target)", fontWeight = FontWeight.SemiBold, maxLines = 1)
//                Spacer(modifier = Modifier.height(2.dp))
//                Text(
//                    text = "${pond.pondFish.fishTargetWeight}",
//                    fontWeight = FontWeight.Normal,
//                    maxLines = 1
//                )
//            }
//        }
//        Spacer(modifier = Modifier.height(8.dp))
//        Card(
//            modifier = Modifier
//                .fillMaxWidth()
//        ) {
//            Column(
//                modifier = Modifier
//                    .fillMaxWidth()
//                    .padding(8.dp),
//                horizontalAlignment = Alignment.Start,
//                verticalArrangement = Arrangement.SpaceBetween
//            ) {
//                Text(text = "Berat Ikan (Sekarang)", fontWeight = FontWeight.SemiBold, maxLines = 1)
//                Spacer(modifier = Modifier.height(2.dp))
//                Text(
//                    text = "${pond.pondFish.fishCurrentWeight}",
//                    fontWeight = FontWeight.Normal,
//                    maxLines = 1
//                )
//            }
//        }
//        Spacer(modifier = Modifier.height(8.dp))
//        Card(
//            modifier = Modifier
//                .fillMaxSize()
//        ) {
//            Column(
//                modifier = Modifier
//                    .fillMaxWidth()
//                    .padding(8.dp),
//                horizontalAlignment = Alignment.Start,
//                verticalArrangement = Arrangement.SpaceBetween
//            ) {
//                Text(
//                    text = "Panjang Ikan (Sekarang)",
//                    fontWeight = FontWeight.SemiBold,
//                    maxLines = 1
//                )
//                Spacer(modifier = Modifier.height(2.dp))
//                Text(
//                    text = "${pond.pondFish.fishCurrentLength}",
//                    fontWeight = FontWeight.Normal,
//                    maxLines = 1
//                )
//            }
//        }
//        Spacer(modifier = Modifier.height(8.dp))
//        Card(
//            modifier = Modifier
//                .fillMaxSize()
//        ) {
//            Column(
//                modifier = Modifier
//                    .fillMaxWidth()
//                    .padding(8.dp),
//                horizontalAlignment = Alignment.Start,
//                verticalArrangement = Arrangement.SpaceBetween
//            ) {
//                Text(text = "Jenis Pakan", fontWeight = FontWeight.SemiBold, maxLines = 1)
//                Spacer(modifier = Modifier.height(2.dp))
//                Text(
//                    text = pond.pondFeed.feedName,
//                    fontWeight = FontWeight.Normal,
//                    maxLines = 1
//                )
//            }
//        }
//        Spacer(modifier = Modifier.height(8.dp))
//        Card(
//            modifier = Modifier
//                .fillMaxSize()
//        ) {
//            Column(
//                modifier = Modifier
//                    .fillMaxWidth()
//                    .padding(8.dp),
//                horizontalAlignment = Alignment.Start,
//                verticalArrangement = Arrangement.SpaceBetween
//            ) {
//                Text(
//                    text = "Persentase Pakan / Berat Ikan",
//                    fontWeight = FontWeight.SemiBold,
//                    maxLines = 1
//                )
//                Spacer(modifier = Modifier.height(2.dp))
//                Text(
//                    text = "${pond.pondFeed.feedPercentage}",
//                    fontWeight = FontWeight.Normal,
//                    maxLines = 1
//                )
//            }
//        }
//        Spacer(modifier = Modifier.height(8.dp))
//        Card(
//            modifier = Modifier
//                .fillMaxSize()
//        ) {
//            Column(
//                modifier = Modifier
//                    .fillMaxWidth()
//                    .padding(8.dp),
//                horizontalAlignment = Alignment.Start,
//                verticalArrangement = Arrangement.SpaceBetween
//            ) {
//                Text(text = "Persentase Protein", fontWeight = FontWeight.SemiBold, maxLines = 1)
//                Spacer(modifier = Modifier.height(2.dp))
//                Text(
//                    text = "${pond.pondFeed.feedProteinPercentage}",
//                    fontWeight = FontWeight.Normal,
//                    maxLines = 1
//                )
//            }
//        }
//        Spacer(modifier = Modifier.height(8.dp))
//        Card(
//            modifier = Modifier
//                .fillMaxSize()
//        ) {
//            Column(
//                modifier = Modifier
//                    .fillMaxWidth()
//                    .padding(8.dp),
//                horizontalAlignment = Alignment.Start,
//                verticalArrangement = Arrangement.SpaceBetween
//            ) {
//                Text(text = "Persentase Lipid", fontWeight = FontWeight.SemiBold, maxLines = 1)
//                Spacer(modifier = Modifier.height(2.dp))
//                Text(
//                    text = "${pond.pondFeed.feedLipidPercentage}",
//                    fontWeight = FontWeight.Normal,
//                    maxLines = 1
//                )
//            }
//        }
//        Spacer(modifier = Modifier.height(8.dp))
//        Card(
//            modifier = Modifier
//                .fillMaxSize()
//        ) {
//            Column(
//                modifier = Modifier
//                    .fillMaxWidth()
//                    .padding(8.dp),
//                horizontalAlignment = Alignment.Start,
//                verticalArrangement = Arrangement.SpaceBetween
//            ) {
//                Text(
//                    text = "Persentase Karbohidrat",
//                    fontWeight = FontWeight.SemiBold,
//                    maxLines = 1
//                )
//                Spacer(modifier = Modifier.height(2.dp))
//                Text(
//                    text = "${pond.pondFeed.feedCarbohydratePercentage}",
//                    fontWeight = FontWeight.Normal,
//                    maxLines = 1
//                )
//            }
//        }
//        Spacer(modifier = Modifier.height(8.dp))
//        Card(
//            modifier = Modifier
//                .fillMaxSize()
//        ) {
//            Column(
//                modifier = Modifier
//                    .fillMaxWidth()
//                    .padding(8.dp),
//                horizontalAlignment = Alignment.Start,
//                verticalArrangement = Arrangement.SpaceBetween
//            ) {
//                Text(
//                    text = "Persentase Kandungan Lainnya",
//                    fontWeight = FontWeight.SemiBold,
//                    maxLines = 1
//                )
//                Spacer(modifier = Modifier.height(2.dp))
//                Text(
//                    text = "${pond.pondFeed.feedOthersPercentage}",
//                    fontWeight = FontWeight.Normal,
//                    maxLines = 1
//                )
//            }
//        }
//        Spacer(modifier = Modifier.height(8.dp))
//        Card(
//            modifier = Modifier
//                .fillMaxSize()
//        ) {
//            Column(
//                modifier = Modifier
//                    .fillMaxWidth()
//                    .padding(8.dp),
//                horizontalAlignment = Alignment.Start,
//                verticalArrangement = Arrangement.SpaceBetween
//            ) {
//                Text(
//                    text = "Frekuensi pemberian pakan / hari",
//                    fontWeight = FontWeight.SemiBold,
//                    maxLines = 1
//                )
//                Spacer(modifier = Modifier.height(2.dp))
//                Text(
//                    text = "${pond.pondFeed.feedFrequencyDaily}",
//                    fontWeight = FontWeight.Normal,
//                    maxLines = 1
//                )
//            }
//        }
//        Spacer(modifier = Modifier.height(12.dp))
//        Button(
//            modifier = Modifier.fillMaxWidth(),
//            colors = ButtonDefaults.buttonColors(containerColor = Navi, contentColor = White),
//            shape = RoundedCornerShape(8.dp),
//            onClick = {
//                pondsViewModel.onAddPondToDatabase(userId = pondsViewModel.getLocalUserId(), pond = pond)
//                onCreatePond()
//        }) {
//            Text(text = "Buat Kolam", modifier = Modifier, fontWeight = FontWeight.Bold, color = White)
//        }
//    }
//}