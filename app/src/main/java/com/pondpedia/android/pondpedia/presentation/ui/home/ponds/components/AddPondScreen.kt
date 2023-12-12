package com.pondpedia.android.pondpedia.presentation.ui.home.ponds.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Map
import androidx.compose.material.icons.outlined.StickyNote2
import androidx.compose.material.icons.outlined.TextFields
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
import com.pondpedia.android.pondpedia.presentation.Graph
import com.pondpedia.android.pondpedia.presentation.ui.home.components.Screens
import com.pondpedia.android.pondpedia.presentation.ui.home.ponds.components.viewmodel.PondsEvent
import com.pondpedia.android.pondpedia.presentation.ui.home.ponds.components.viewmodel.PondsState
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import java.time.LocalDateTime

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddPondScreen(
    pondsState: PondsState,
    onEvent: (PondsEvent) -> Unit,
    onNavigateToDestination: (Screens) -> Unit,
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

    val pondTypeList = listOf(
        "Tanah",
        "Beton",
        "Terpal",
        "Plastik"
    )

    val waterTypeList = listOf(
        "Air Laut",
        "Air Payau",
        "Air Tawar"
    )


    var date by rememberSaveable {
        mutableStateOf(LocalDateTime.now())
    }
    var name by rememberSaveable {
        mutableStateOf("")
    }
    var area by rememberSaveable {
        mutableStateOf("")
    }
    var depth by rememberSaveable {
        mutableStateOf("")
    }
    var pondType by rememberSaveable {
        mutableStateOf("")
    }
    var waterType by rememberSaveable {
        mutableStateOf("")
    }
    var location by rememberSaveable {
        mutableStateOf("")
    }
    var description by rememberSaveable {
        mutableStateOf("")
    }

    Column(
        verticalArrangement = Arrangement.spacedBy(8.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.verticalScroll(scrollState)
    ) {


        Spacer(modifier = Modifier.height(8.dp))
        Text(text = "Tambahkan Kolam", fontWeight = FontWeight.ExtraBold, color = MaterialTheme.colorScheme.onBackground, fontSize = 20.sp)
        Spacer(modifier = Modifier.height(8.dp))

        TextField(
            value = name,
            onValueChange = {
                name = it
                onEvent(PondsEvent.SetName(name))
            },
            label = {
                Text(text = "Nama kolam")
            },
            keyboardOptions = KeyboardOptions(
                imeAction = ImeAction.Next
            ),
            supportingText = {
                Text(text = "* Wajib diisi", color = androidx.compose.ui.graphics.Color.Gray)
            },
            trailingIcon = {
                Icon(imageVector = Icons.Outlined.TextFields, contentDescription = "Pond Name")
            }
        )

        TextField(
            value = area,
            onValueChange = {
                area = it
                onEvent(PondsEvent.SetArea(StringParser.toInt(area).toString()))
            },
            label = {
                Text(text = "Luas kolam")
            },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Decimal,
                imeAction = ImeAction.Next
            ),
            supportingText = {
                Text(text = "* Wajib diisi", color = androidx.compose.ui.graphics.Color.Gray)
            },
            suffix = {
                Text(text = "M\u00B2")
            }
        )

        TextField(
            value = depth,
            onValueChange = {
                depth = it
                onEvent(PondsEvent.SetDepth(StringParser.toInt(depth).toString()))
            },
            label = {
                Text(text = "Kedalaman kolam")
            },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Decimal,
                imeAction = ImeAction.Next
            ),
            supportingText = {
                Text(text = "* Wajib diisi", color = androidx.compose.ui.graphics.Color.Gray)
            },
            suffix = {
                Text(text = "CM")
            }
        )

        ExposedDropdownMenuBox(
            expanded = isPondTypeExpanded,
            onExpandedChange = { newValue ->
                isPondTypeExpanded = newValue
            }
        ) {
            TextField(
                value = pondType,
                onValueChange = {},
                readOnly = true,
                trailingIcon = {
                    ExposedDropdownMenuDefaults.TrailingIcon(expanded = isPondTypeExpanded)
                },
                label = {
                    Text(text = "Tipe Bahan Kolam")
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
                pondTypeList.forEachIndexed { _, type ->
                    DropdownMenuItem(
                        text = {
                            Text(text = type)
                        },
                        onClick = {
                            pondType = type
                            onEvent(PondsEvent.SetPondType(pondType))
                            isPondTypeExpanded = false
                        }
                    )
                }
            }
        }

        ExposedDropdownMenuBox(
            expanded = isWaterTypeExpanded,
            onExpandedChange = { newValue ->
                isWaterTypeExpanded = newValue
            }
        ) {
            TextField(
                value = waterType,
                onValueChange = {},
                readOnly = true,
                trailingIcon = {
                    ExposedDropdownMenuDefaults.TrailingIcon(expanded = isWaterTypeExpanded)
                },
                label = {
                    Text(text = "Tipe Air Kolam")
                },
                supportingText = {
                    Text(text = "* Wajib diisi", color = androidx.compose.ui.graphics.Color.Gray)
                },
                colors = ExposedDropdownMenuDefaults.textFieldColors(),
                modifier = Modifier.menuAnchor()
            )
            ExposedDropdownMenu(
                expanded = isWaterTypeExpanded,
                onDismissRequest = {
                    isWaterTypeExpanded = false
                }
            ) {
                waterTypeList.forEachIndexed { _, type ->
                    DropdownMenuItem(
                        text = {
                            Text(text = type)
                        },
                        onClick = {
                            waterType = type
                            onEvent(PondsEvent.SetWaterType(waterType))
                            isWaterTypeExpanded = false
                        }
                    )
                }
            }
        }

        TextField(
            value = location,
            onValueChange = {
                location = it
                onEvent(PondsEvent.SetLocation(location))
            },
            label = {
                Text(text = "Lokasi")
            },
            keyboardOptions = KeyboardOptions(
                imeAction = ImeAction.Next
            ),
            supportingText = {
                Text(text = "* Opsional", color = androidx.compose.ui.graphics.Color.Gray)
            },
            trailingIcon = {
                Icon(imageVector = Icons.Outlined.Map, contentDescription = "Pond Name")
            }
        )

        TextField(
            value = description,
            onValueChange = {
                description = it
                onEvent(PondsEvent.SetDescription(description))
            },
            label = {
                Text(text = "Deskripsi tambahan")
            },
            keyboardOptions = KeyboardOptions(
                imeAction = ImeAction.Next
            ),
            supportingText = {
                Text(text = "* Opsional", color = androidx.compose.ui.graphics.Color.Gray)
            },
            trailingIcon = {
                Icon(imageVector = Icons.Outlined.StickyNote2, contentDescription = "Description")
            }
        )

        val scope = CoroutineScope(Dispatchers.Default)
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            OutlinedButton(onClick = {
//                    onEvent(PondsEvent.HideDialog)
                onNavigateToDestination(Screens.Ponds)
            }) {
                Text(text = "Batal")
            }
            Button(
                onClick = {
                    onEvent(PondsEvent.AddPond)
                    onNavigateToDestination(Screens.Ponds)
                },
                enabled = validateForm(name, area, depth, pondType, waterType)
            ) {
                Text(text = "Tambah")
            }
        }
    }
}
private fun validateForm(name: String, area: String, depth: String, pondType: String, waterType: String): Boolean {
    return !(name.isBlank() || area.isBlank() || depth.isBlank() || pondType.isBlank() || waterType.isBlank())
}
