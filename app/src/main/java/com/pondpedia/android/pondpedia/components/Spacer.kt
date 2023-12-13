package com.pondpedia.android.pondpedia.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun ExtraSmallSpacer() {
    Spacer(
        modifier = Modifier.height(4.dp)
    )
}
@Composable
fun SmallSpacer() {
    Spacer(
        modifier = Modifier.height(8.dp)
    )
}
@Composable
fun MediumSpacer() {
    Spacer(
        modifier = Modifier.height(12.dp)
    )
}
@Composable
fun LargeSpacer() {
    Spacer(
        modifier = Modifier.height(16.dp)
    )
}



