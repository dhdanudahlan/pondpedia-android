package com.pondpedia.android.pondpedia.presentation.ui.home.ponds.components

import androidx.compose.foundation.shape.CornerSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import com.pondpedia.android.pondpedia.presentation.ui.home.ponds.components.viewmodel.PondsEvent

@Composable
fun FloatingActionButtonCompose(
    onEvent: (PondsEvent) -> Unit
) {
    FloatingActionButton(shape = MaterialTheme.shapes.large.copy(CornerSize(percent = 50)),
        onClick = {
            onEvent(PondsEvent.ShowDialog)
        }
    ) {
        Icon(Icons.Default.Add, contentDescription = null)
    }
}