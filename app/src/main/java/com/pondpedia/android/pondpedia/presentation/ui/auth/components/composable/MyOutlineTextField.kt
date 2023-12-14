package com.pondpedia.android.pondpedia.presentation.ui.auth.components.composable

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp

@Composable
fun MyOutlineTextField(
    textFieldValue: TextFieldValue,
    onTextFieldValueChange: (newValue: TextFieldValue) -> Unit,
    isErrorMessage: String? = null,
    label: String? = null,
    imageVector: ImageVector? = null,
    keyboardOptions: KeyboardOptions = KeyboardOptions(
        keyboardType = KeyboardType.Text,
        imeAction = ImeAction.Done
    ),
) {
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
                modifier = Modifier
                    .fillMaxSize(),
                contentAlignment = Alignment.Center
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
                    modifier = Modifier
                        .fillMaxWidth(),
                    shape = RoundedCornerShape(8.dp),
                    singleLine = true,
                    keyboardOptions = keyboardOptions,
                    leadingIcon = {
                        if (imageVector != null) {
                            Icon(
                                imageVector = imageVector,
                                contentDescription = null
                            )
                        }
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