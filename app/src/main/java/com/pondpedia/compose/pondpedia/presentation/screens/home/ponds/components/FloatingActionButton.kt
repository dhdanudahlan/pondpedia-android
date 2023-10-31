package com.pondpedia.compose.pondpedia.presentation.screens.home.ponds.components

import androidx.compose.foundation.shape.CornerSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import com.pondpedia.compose.pondpedia.presentation.screens.home.components.Screens
import com.pondpedia.compose.pondpedia.presentation_copy.screens.home.components.Screens

@Composable
fun FloatingActionButtonCompose(
    onRouteChanged: (Screens) -> Unit
) {
    val mContext = LocalContext.current
    FloatingActionButton(shape = MaterialTheme.shapes.large.copy(CornerSize(percent = 50)),
        onClick = { onRouteChanged(Screens.Create) }) {
        Icon(Icons.Default.Add, contentDescription = null)
    }
}