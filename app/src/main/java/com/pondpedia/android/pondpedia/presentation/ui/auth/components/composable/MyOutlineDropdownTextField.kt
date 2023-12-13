@file:OptIn(ExperimentalMaterial3Api::class)

package com.pondpedia.android.pondpedia.presentation.ui.auth.components.composable

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp

@Composable
fun MyOutlineDropdownTextField(
    textFieldValue: TextFieldValue,
    onTextFieldValueChange: (newValue: TextFieldValue) -> Unit,
    onExpandedChange: (newValue: Boolean) -> Unit,
    isExpanded: Boolean = false,
    isErrorMessage: String? = null,
    label: String? = null,
    imageVector: ImageVector? = null,
) {
    var isExpandedLocal by rememberSaveable {
        mutableStateOf(isExpanded)
    }
    Box(
        modifier = Modifier
            .fillMaxWidth(),
        contentAlignment = Alignment.CenterStart
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize(),
            horizontalAlignment = Alignment.Start
        ) {
            if (label != null) {
                Text(
                    text = label,
                    maxLines = 1,
                )
            }

            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {

                ExposedDropdownMenuBox(
                    expanded = isExpandedLocal,
                    onExpandedChange = { newValue ->
                        isExpandedLocal = newValue
                        onExpandedChange(newValue)
                    }
                ) {
                    OutlinedTextField(
                        value = textFieldValue,
                        onValueChange = { newValue ->
                            onTextFieldValueChange(newValue)
                        },
                        placeholder = {
                            if (label != null) {
                                Text(
                                    text = label,
                                    maxLines = 1,
                                )
                            }
                        },
                        keyboardOptions = KeyboardOptions(
                            keyboardType = KeyboardType.Text,
                            imeAction = ImeAction.Done
                        ),
                        modifier = Modifier
                            .fillMaxWidth()
                            .menuAnchor(),
                        shape = RoundedCornerShape(8.dp),
                        readOnly = true,
                        leadingIcon = {
                            if (imageVector != null) {
                                Icon(
                                    imageVector = imageVector,
                                    contentDescription = null
                                )
                            }
                        },
                        trailingIcon = {
                            ExposedDropdownMenuDefaults.TrailingIcon(expanded = isExpandedLocal)
                        },
                        isError = isErrorMessage != null,
                        supportingText = {
                            if (isErrorMessage != null) {
                                Text(
                                    text = isErrorMessage,
                                    color = Color.Red
                                )
                            }
                        }
                    )
                }
            }
        }
    }
}