package com.pondpedia.android.pondpedia.presentation.ui.home.ponds.screens.pond_details.components

import android.health.connect.datatypes.units.Temperature
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.CalendarToday
import androidx.compose.material.icons.outlined.Cloud
import androidx.compose.material.icons.outlined.InvertColors
import androidx.compose.material.icons.outlined.StickyNote2
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.pondpedia.android.pondpedia.R
import com.pondpedia.android.pondpedia.core.util.StringParser
import com.pondpedia.android.pondpedia.presentation.ui.home.ponds.components.viewmodel.PondDetailsEvent
import com.pondpedia.android.pondpedia.presentation.ui.home.ponds.components.viewmodel.PondDetailsState

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddPondManagementRecordsTabWaterScreen(
    pondDetailsState: PondDetailsState,
    onEvent: (PondDetailsEvent) -> Unit,
    onNavigateToOverviewScreen: () -> Unit
) {

    var showDatePicker by remember {
        mutableStateOf(false)
    }

    val scrollState = rememberScrollState()

    var date by rememberSaveable {
        mutableStateOf("")
    }
    var level by rememberSaveable {
        mutableStateOf("")
    }
    var ph by rememberSaveable {
        mutableStateOf("")
    }
    var temperature by rememberSaveable {
        mutableStateOf("")
    }
    var weather by rememberSaveable {
        mutableStateOf("")
    }
    var dissolvedOxygen by rememberSaveable {
        mutableStateOf("")
    }
    var salinity by rememberSaveable {
        mutableStateOf("")
    }
    var turbidity by rememberSaveable {
        mutableStateOf("")
    }
    var clarity by rememberSaveable {
        mutableStateOf("")
    }

    var note by rememberSaveable {
        mutableStateOf("")
    }
    var color by rememberSaveable {
        mutableStateOf("")
    }

    fun reset(){
        date = ""
        level = ""
        ph = ""
        temperature = ""
        weather = ""
        dissolvedOxygen = ""
        salinity = ""
        turbidity = ""
        clarity = ""
        color = ""
        note = ""
    }

    Column(
        verticalArrangement = Arrangement.spacedBy(8.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.verticalScroll(scrollState)
    ) {
        Spacer(modifier = Modifier.height(8.dp))

        ExposedDropdownMenuBox(
            expanded = showDatePicker,
            onExpandedChange = { newValue ->
                showDatePicker = newValue
            }
        ) {
            TextField(
                value = date,
                onValueChange = {},
                readOnly = true,
                trailingIcon = {
                    Icon(imageVector = Icons.Outlined.CalendarToday, contentDescription = "Date")
                },
                label = {
                    Text(text = "Tanggal Pengujian")
                },
                supportingText = {
                    Text(text = "* Wajib diisi", color = androidx.compose.ui.graphics.Color.Gray)
                },
                colors = ExposedDropdownMenuDefaults.textFieldColors(),
                modifier = Modifier.menuAnchor()
            )
            if (showDatePicker) {
                MyDatePickerDialog(
                    onDateSelected = {
                        date = it
                        onEvent(PondDetailsEvent.SetWaterRecordsDate(it))
                    },
                    onDismiss = { showDatePicker = false }
                )
            }
        }
        Spacer(modifier = Modifier.height(4.dp))

        TextField(
            value = level,
            onValueChange = {
                level = it
                onEvent(PondDetailsEvent.SetWaterRecordsLevel(StringParser.toInt(it).toString()))
            },
            label = {
                Text(text = "Ketinggian Air")
            },
            suffix = {
                Text(text = "CM")
            },
            supportingText = {
                Text(text = "* Wajib diisi", color = androidx.compose.ui.graphics.Color.Gray)
            },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Decimal,
                imeAction = ImeAction.Next
            )
        )
        Spacer(modifier = Modifier.height(4.dp))

        TextField(
            value = color,
            onValueChange = {
                color = it
                onEvent(PondDetailsEvent.SetWaterRecordsColor(it))
            },
            label = {
                Text(text = "Warna Air")
            },
            trailingIcon = {
                Icon(
                    imageVector = Icons.Outlined.InvertColors,
                    contentDescription = "Color"
                )
            },
            supportingText = {
                Text(text = "* Wajib diisi", color = androidx.compose.ui.graphics.Color.Gray)
            },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Text,
                imeAction = ImeAction.Next
            )
        )
        Spacer(modifier = Modifier.height(4.dp))

        TextField(
            value = ph,
            onValueChange = {
                ph = it
                onEvent(PondDetailsEvent.SetWaterRecordsPH(StringParser.toFloat(it).toString()))
            },
            label = {
                Text(text = "pH Air")
            },
            trailingIcon = {
                Icon(
                    imageVector = Icons.Outlined.InvertColors,
                    contentDescription = "ph"
                )
            },
            supportingText = {
                Text(text = "* Wajib diisi", color = androidx.compose.ui.graphics.Color.Gray)
            },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Decimal,
                imeAction = ImeAction.Next
            )
        )
        Spacer(modifier = Modifier.height(4.dp))

        TextField(
            value = temperature,
            onValueChange = {
                temperature = it
                onEvent(PondDetailsEvent.SetWaterRecordsTemperature(StringParser.toFloat(it).toString()))
            },
            label = {
                Text(text = "Suhu Air")
            },
            suffix = {
                Text(text = "°C")
            },
            supportingText = {
                Text(text = "* Wajib diisi", color = androidx.compose.ui.graphics.Color.Gray)
            },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Decimal,
                imeAction = ImeAction.Next
            )
        )
        Spacer(modifier = Modifier.height(4.dp))

        TextField(
            value = weather,
            onValueChange = {
                weather = it
                onEvent(PondDetailsEvent.SetWaterRecordsWeather(it))
            },
            label = {
                Text(text = "Cuaca")
            },
            trailingIcon = {
                Icon(
                    imageVector = Icons.Outlined.Cloud,
                    contentDescription = "Color"
                )
            },
            supportingText = {
                Text(text = "* Wajib diisi", color = androidx.compose.ui.graphics.Color.Gray)
            },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Text,
                imeAction = ImeAction.Next
            )
        )
        Spacer(modifier = Modifier.height(4.dp))

        TextField(
            value = dissolvedOxygen,
            onValueChange = {
                dissolvedOxygen = it
                onEvent(PondDetailsEvent.SetWaterRecordsDissolvedOxygen(StringParser.toFloat(it).toString()))
            },
            label = {
                Text(text = "Oksigen Terlarut Air")
            },
            trailingIcon = {
            },
            supportingText = {
                Text(text = "* Opsional", color = androidx.compose.ui.graphics.Color.Gray)
            },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Decimal,
                imeAction = ImeAction.Next
            )
        )
        Spacer(modifier = Modifier.height(4.dp))

        TextField(
            value = salinity,
            onValueChange = {
                salinity = it
                onEvent(PondDetailsEvent.SetWaterRecordsSalinity(StringParser.toFloat(it).toString()))
            },
            label = {
                Text(text = "Salinitas Air")
            },
            trailingIcon = {
            },
            supportingText = {
                Text(text = "* Opsional", color = androidx.compose.ui.graphics.Color.Gray)
            },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Decimal,
                imeAction = ImeAction.Next
            )
        )
        Spacer(modifier = Modifier.height(4.dp))

        TextField(
            value = turbidity,
            onValueChange = {
                turbidity = it
                onEvent(PondDetailsEvent.SetWaterRecordsTurbidity(StringParser.toFloat(it).toString()))
            },
            label = {
                Text(text = "Kekeruhan Air")
            },
            trailingIcon = {
            },
            supportingText = {
                Text(text = "* Opsional", color = androidx.compose.ui.graphics.Color.Gray)
            },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Decimal,
                imeAction = ImeAction.Next
            )
        )
        Spacer(modifier = Modifier.height(4.dp))

        TextField(
            value = clarity,
            onValueChange = {
                clarity = it
                onEvent(PondDetailsEvent.SetWaterRecordsClarity(StringParser.toFloat(it).toString()))
            },
            label = {
                Text(text = "Warna Air")
            },
            trailingIcon = {
            },
            supportingText = {
                Text(text = "* Opsional", color = androidx.compose.ui.graphics.Color.Gray)
            },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Decimal,
                imeAction = ImeAction.Next
            )
        )
        Spacer(modifier = Modifier.height(4.dp))

        TextField(
            value = note,
            onValueChange = {
                note = it
                onEvent(PondDetailsEvent.SetWaterRecordsNote(it))
            },
            label = {
                Text(text = "Catatan Tambahan")
            },
            trailingIcon = {
                Icon(
                    imageVector = Icons.Outlined.StickyNote2,
                    contentDescription = "Note"
                )
            },
            supportingText = {
                Text(text = "* Opsional", color = androidx.compose.ui.graphics.Color.Gray)
            },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Text,
                imeAction = ImeAction.Next
            )
        )
        Spacer(modifier = Modifier.height(4.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            Button(
                enabled = validateForm(date, level, ph, temperature, weather, color),
                onClick = {
                    onEvent(PondDetailsEvent.AddWaterRecords)
                    reset()
                    onNavigateToOverviewScreen()
                }
            ) {
                Text(text = stringResource(R.string.button_save))
            }
        }
    }
}
private fun validateForm(date: String, level: String, ph: String, temperature: String, weather: String, color: String): Boolean {
    return !(date.isBlank() || level.isBlank() || ph.isBlank() || temperature.isBlank() || weather.isBlank() || color.isBlank())
}

