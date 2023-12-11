package com.pondpedia.android.pondpedia.presentation.ui.home.ponds.screens.pond_details.screens

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
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.pondpedia.android.pondpedia.presentation.PondPediaAppState
import com.pondpedia.android.pondpedia.presentation.ui.home.ponds.components.viewmodel.PondDetailsEvent
import com.pondpedia.android.pondpedia.presentation.ui.home.ponds.components.viewmodel.PondDetailsState
import com.pondpedia.android.pondpedia.presentation.ui.home.ponds.components.viewmodel.PondsState
import com.pondpedia.android.pondpedia.presentation.theme.Black

@Composable
fun PondWaterScreen(
    homeState: PondPediaAppState,
    pondsState: PondsState,
    pondDetailsState: PondDetailsState,
    onEvent: (PondDetailsEvent) -> Unit,
    innerPadding: PaddingValues
) {
    
    val listOfRecords = pondDetailsState.waterRecords.sortedBy { it.date }
    val scrollState = rememberScrollState()
    val listOfRecordsReversed = listOfRecords.reversed()
    Row(
        Modifier
            .fillMaxSize()
            .padding(innerPadding)
    ) {
        Column(
            Modifier
                .fillMaxSize()
                .verticalScroll(scrollState)
        ) {
            listOfRecordsReversed.forEachIndexed { index, waterRecords ->
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
                                text = waterRecords.date,
                                fontWeight = FontWeight.SemiBold
                            )

                            Text(text = "Ketinggian Air : ${waterRecords.level}")

                            if (waterRecords.pH != null) {
                                Text(text = "PH Air : ${waterRecords.pH}")
                            }

                            if (waterRecords.temperature != null) {
                                Text(text = "Suhu Air : ${waterRecords.temperature}")
                            }

                            if ((waterRecords.weather != "null") || (waterRecords.weather.isBlank()) ) {
                                Text(text = "Cuaca : ${waterRecords.weather}")
                            }

                            if ((waterRecords.color != "null") || (waterRecords.color.isBlank()) ) {
                                Text(text = "Warna Air : ${waterRecords.color}")
                            }

                            if (waterRecords.dissolvedOxygen != null) {
                                Text(text = "Oksigen Terlarut : ${waterRecords.dissolvedOxygen}")
                            }

                            if (waterRecords.salinity != null) {
                                Text(text = "Salinitas : ${waterRecords.salinity}")
                            }

                            if (waterRecords.turbidity != null) {
                                Text(text = "Kekeruhan : ${waterRecords.turbidity}")
                            }

                            if (waterRecords.clarity != null) {
                                Text(text = "Kecerahan : ${waterRecords.clarity}")
                            }

                            if (waterRecords.note.isNotBlank()) {
                                Text(text = "Catatan : ${waterRecords.note}")
                            }
                        }
                    }
                }
            }
        }
    }
}