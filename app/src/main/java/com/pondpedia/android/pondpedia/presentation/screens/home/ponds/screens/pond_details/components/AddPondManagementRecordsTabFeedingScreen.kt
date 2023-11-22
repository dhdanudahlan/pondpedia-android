package com.pondpedia.android.pondpedia.presentation.screens.home.ponds.screens.pond_details.components

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
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.pondpedia.android.pondpedia.core.util.StringParser
import com.pondpedia.android.pondpedia.presentation.screens.home.ponds.components.viewmodel.PondDetailsEvent
import com.pondpedia.android.pondpedia.presentation.screens.home.ponds.components.viewmodel.PondDetailsState

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddPondManagementRecordsTabFeedingScreen(
    pondDetailsState: PondDetailsState,
    onEvent: (PondDetailsEvent) -> Unit
) {

    var showDatePicker by remember {
        mutableStateOf(false)
    }

    val scrollState = rememberScrollState()



    var date by rememberSaveable {
        mutableStateOf("")
    }
    var quantity by rememberSaveable {
        mutableStateOf("")
    }
    var note by rememberSaveable {
        mutableStateOf("")
    }

    fun reset(){
        date = ""
        quantity = ""
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
                        onEvent(PondDetailsEvent.SetFeedingRecordsDate(it))
                    },
                    onDismiss = { showDatePicker = false }
                )
            }
        }
        Spacer(modifier = Modifier.height(4.dp))

        TextField(
            value = quantity,
            onValueChange = {
                quantity = it
                onEvent(PondDetailsEvent.SetFeedingRecordsQuantity(StringParser.commaToDot(it)))
            },
            label = {
                Text(text = "Jumlah Pakan yang Diberikan")
            },
            suffix = {
                Text(text = "KG")
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
            value = note,
            onValueChange = {
                note = it
                onEvent(PondDetailsEvent.SetFeedingRecordsNote(it))
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
            Button(onClick = {
                onEvent(PondDetailsEvent.AddFeedingRecords)
                reset()
            }) {
                Text(text = "Simpan")
            }
        }

    }
}