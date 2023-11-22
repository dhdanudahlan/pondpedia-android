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
import androidx.compose.material.icons.outlined.Animation
import androidx.compose.material.icons.outlined.CalendarToday
import androidx.compose.material.icons.outlined.Map
import androidx.compose.material.icons.outlined.StickyNote2
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.DialogProperties
import com.pondpedia.android.pondpedia.core.util.StringParser
import com.pondpedia.android.pondpedia.presentation.screens.home.ponds.components.viewmodel.PondDetailsEvent
import com.pondpedia.android.pondpedia.presentation.screens.home.ponds.components.viewmodel.PondDetailsState

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddCommodityDialog(
    pondDetailsState: PondDetailsState,
    onEvent: (PondDetailsEvent) -> Unit,
) {
    val scrollState = rememberScrollState()

    var isPondTypeExpanded by remember { mutableStateOf(false) }
    var selectedPondTypeIndex by rememberSaveable {
        mutableStateOf(0)
    }

    var isWaterTypeExpanded by remember { mutableStateOf(false) }
    var selectedWaterTypeIndex by rememberSaveable {
        mutableStateOf(0)
    }

    val commodityTypeList = listOf(
        "Udang Vaname",
        "Udang Windu",
        "Ikan Nila",
        "Ikan Lele",
        "Lainnya"
    )

    val waterTypeList = listOf(
        "Air Laut",
        "Air Payau",
        "Air Tawar"
    )

    AlertDialog(
        onDismissRequest = {
            onEvent(PondDetailsEvent.HideDialog)
        },
        properties = DialogProperties(dismissOnClickOutside = false),
    ) {

        var showDatePicker by remember {
            mutableStateOf(false)
        }

        var date by rememberSaveable {
            mutableStateOf("")
        }
        var name by rememberSaveable {
            mutableStateOf("")
        }
        var quantity by rememberSaveable {
            mutableStateOf("")
        }
        var origin by rememberSaveable {
            mutableStateOf("")
        }
        var sciName by rememberSaveable {
            mutableStateOf("")
        }

        Column(
            verticalArrangement = Arrangement.spacedBy(8.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .verticalScroll(scrollState)
                .fillMaxWidth()
        ) {

            Spacer(modifier = Modifier.height(8.dp))
            Text(text = "Tambahkan Komoditas", fontWeight = FontWeight.ExtraBold, color = MaterialTheme.colorScheme.onBackground, fontSize = 20.sp)
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
                        Text(text = "Tanggal Tebar")
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
                            onEvent(PondDetailsEvent.SetCommodityDate(it))
                        },
                        onDismiss = { showDatePicker = false }
                    )
                }
            }

            ExposedDropdownMenuBox(
                expanded = isPondTypeExpanded,
                onExpandedChange = { newValue ->
                    isPondTypeExpanded = newValue
                }
            ) {
                TextField(
                    value = name,
                    onValueChange = {
                        name = it
                        onEvent(PondDetailsEvent.SetCommodityName(it))
                    },
                    readOnly = false,
                    trailingIcon = {
                        ExposedDropdownMenuDefaults.TrailingIcon(expanded = isPondTypeExpanded)
                    },
                    label = {
                        Text(text = "Nama Komoditas")
                    },
                    supportingText = {
                        Text(text = "* Wajib diisi", color = androidx.compose.ui.graphics.Color.Gray)
                    },
                    colors = ExposedDropdownMenuDefaults.textFieldColors(),
                    modifier = Modifier.menuAnchor()
                )
                ExposedDropdownMenu(
                    expanded = isPondTypeExpanded,
                    onDismissRequest = {
                        isPondTypeExpanded = false
                    }
                ) {
                    commodityTypeList.forEachIndexed { index, type ->
                        DropdownMenuItem(
                            text = {
                                Text(text = type)
                            },
                            onClick = {
                                name = type
                                onEvent(PondDetailsEvent.SetCommodityName(type))
                                isPondTypeExpanded = false
                            }
                        )
                    }
                }
            }

            TextField(
                value = quantity,
                onValueChange = {
                    quantity = it
                    onEvent(PondDetailsEvent.SetCommodityQuantity(StringParser.commaToDot(it)))
                },
                label = {
                    Text(text = "Jumlah Tebar")
                },
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Decimal,
                    imeAction = ImeAction.Next
                ),
                supportingText = {
                    Text(text = "* Wajib diisi", color = androidx.compose.ui.graphics.Color.Gray)
                },
                trailingIcon = {
                    Icon(imageVector = Icons.Outlined.Animation, contentDescription = "Jumlah Komoditas")
                }
            )

            TextField(
                value = origin,
                onValueChange = {
                    origin = it
                    onEvent(PondDetailsEvent.SetCommodityOrigin(it))
                },
                label = {
                    Text(text = "Asal Bibit/Benur")
                },
                keyboardOptions = KeyboardOptions(
                    imeAction = ImeAction.Next
                ),
                supportingText = {
                    Text(text = "* Opsional", color = androidx.compose.ui.graphics.Color.Gray)
                },
                trailingIcon = {
                    Icon(imageVector = Icons.Outlined.Map, contentDescription = "Asal Komoditas")
                }
            )

            TextField(
                value = sciName,
                onValueChange = {
                    sciName = it
                    onEvent(PondDetailsEvent.SetCommoditySciName(it))
                },
                label = {
                    Text(text = "Nama Lain Komoditas")
                },
                keyboardOptions = KeyboardOptions(
                    imeAction = ImeAction.Next
                ),
                supportingText = {
                    Text(text = "* Opsional", color = androidx.compose.ui.graphics.Color.Gray)
                },
                trailingIcon = {
                    Icon(imageVector = Icons.Outlined.StickyNote2, contentDescription = "Nama Lain")
                }
            )

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                OutlinedButton(onClick = {
                    onEvent(PondDetailsEvent.HideDialog)
                }) {
                    Text(text = "Batal")
                }
                Button(onClick = {
                    onEvent(PondDetailsEvent.AddCommodity)
                }) {
                    Text(text = "Tambah")
                }
            }
        }
    }
}
