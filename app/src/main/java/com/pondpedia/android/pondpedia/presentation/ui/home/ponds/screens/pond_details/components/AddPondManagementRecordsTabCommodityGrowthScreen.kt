package com.pondpedia.android.pondpedia.presentation.ui.home.ponds.screens.pond_details.components

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
import androidx.compose.material3.DropdownMenuItem
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
import androidx.compose.ui.graphics.Color
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
fun AddPondManagementRecordsTabCommodityGrowthScreen(
    pondDetailsState: PondDetailsState,
    onEvent: (PondDetailsEvent) -> Unit,
    onNavigateToOverviewScreen: () -> Unit
) {

    var showDatePicker by remember {
        mutableStateOf(false)
    }
    var isCommodityListExpanded by remember { mutableStateOf(false) }

    var commodityList = pondDetailsState.commodity
    var selectedCommodityIndex by rememberSaveable {
        mutableStateOf(0)
    }
    val scrollState = rememberScrollState()

    val isCommodityExist = pondDetailsState.commodity.isNotEmpty()



    var date by rememberSaveable {
        mutableStateOf("")
    }
    var age by rememberSaveable {
        mutableStateOf("")
    }
    var length by rememberSaveable {
        mutableStateOf("")
    }
    var weight by rememberSaveable {
        mutableStateOf("")
    }
    var note by rememberSaveable {
        mutableStateOf("")
    }

    fun reset(){
        date = ""
        age = ""
        length = ""
        weight = ""
        note = ""
    }

    Column(
        verticalArrangement = Arrangement.spacedBy(8.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.verticalScroll(scrollState)
    ) {
        Spacer(modifier = Modifier.height(8.dp))

        if (isCommodityExist) {
            ExposedDropdownMenuBox(
                expanded = isCommodityListExpanded,
                onExpandedChange = { newValue ->
                    isCommodityListExpanded = newValue
                }
            ) {
                TextField(
                    value = commodityList[selectedCommodityIndex].name,
                    onValueChange = {},
                    readOnly = true,
                    trailingIcon = {
                        ExposedDropdownMenuDefaults.TrailingIcon(expanded = isCommodityListExpanded)
                    },
                    label = {
                        Text(text = "Komoditas")
                    },
                    supportingText = {
                        Text(text = "* Wajib diisi", color = androidx.compose.ui.graphics.Color.Gray)
                    },
                    colors = ExposedDropdownMenuDefaults.textFieldColors(),
                    modifier = Modifier.menuAnchor()
                )
                ExposedDropdownMenu(
                    expanded = isCommodityListExpanded,
                    onDismissRequest = {
                        isCommodityListExpanded = false
                    }
                ) {
                    commodityList.forEachIndexed { index, type ->
                        DropdownMenuItem(
                            text = {
                                Text(text = type.name)
                            },
                            onClick = {
                                onEvent(PondDetailsEvent.SetCommodityId(type.commodityId))
                                isCommodityListExpanded = false
                                selectedCommodityIndex = index
                            }
                        )
                    }
                }
            }
        } else {
            TextField(
                enabled = false,
                value = "",
                onValueChange = {},
                readOnly = true,
                trailingIcon = {
                    ExposedDropdownMenuDefaults.TrailingIcon(expanded = isCommodityListExpanded)
                },
                label = {
                    Text(text = "Komoditas Tidak Ditemukan")
                },
                supportingText = {
                    Text(text = "* Wajib diisi", color = Color.Red)
                },
                colors = ExposedDropdownMenuDefaults.textFieldColors(),
            )
        }
        Spacer(modifier = Modifier.height(4.dp))

        ExposedDropdownMenuBox(
            expanded = showDatePicker,
            onExpandedChange = { newValue ->
                showDatePicker = newValue
            }
        ) {
            TextField(
                enabled = isCommodityExist,
                value = date,
                onValueChange = {},
                readOnly = true,
                trailingIcon = {
                    Icon(imageVector = Icons.Outlined.CalendarToday, contentDescription = "Date")
                },
                label = {
                    Text(text = "Tanggal Pencatatan")
                },
                supportingText = {
                    Text(text = "* Wajib diisi", color = androidx.compose.ui.graphics.Color.Gray)
                },
                colors = ExposedDropdownMenuDefaults.textFieldColors(),
                modifier = Modifier.menuAnchor()
            )
            if (isCommodityExist) {
                if (showDatePicker) {
                    MyDatePickerDialog(
                        onDateSelected = {
                            date = it
                            onEvent(PondDetailsEvent.SetCommodityGrowthRecordsDate(it))
                        },
                        onDismiss = { showDatePicker = false }
                    )
                }
            }
        }
        Spacer(modifier = Modifier.height(4.dp))

        TextField(
            enabled = isCommodityExist,
            value = age,
            onValueChange = {
                age = it
                onEvent(PondDetailsEvent.SetCommodityGrowthRecordsAge(StringParser.toInt(it).toString()))
            },
            label = {
                Text(text = "Umur Komoditas (Hari)")
            },
            suffix = {
                Text(text = "D")
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
            enabled = isCommodityExist,
            value = length,
            onValueChange = {
                length = it
                onEvent(PondDetailsEvent.SetCommodityGrowthRecordsLength(StringParser.toInt(it).toString()))
            },
            label = {
                Text(text = "Panjang Komoditas")
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
            enabled = isCommodityExist,
            value = weight,
            onValueChange = {
                weight = it
                onEvent(PondDetailsEvent.SetCommodityGrowthRecordsWeight(StringParser.toInt(it).toString()))
            },
            label = {
                Text(text = "Berat Komoditas")
            },
            suffix = {
                Text(text = "G")
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
            enabled = isCommodityExist,
            value = note,
            onValueChange = {
                note = it
                onEvent(PondDetailsEvent.SetCommodityGrowthRecordsNote(it))
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
                enabled = isCommodityExist || validateForm(date, age, length, weight),
                onClick = {
                    onEvent(PondDetailsEvent.AddCommodityGrowthRecords)
                    reset()
                    onNavigateToOverviewScreen()
                }
            ) {
                Text(text = stringResource(R.string.button_save))
            }
        }

    }
}

private fun validateForm(date: String, age: String, length: String, weight: String): Boolean {
    return !(date.isBlank() || age.isBlank() || length.isBlank() || weight.isBlank())
}