package com.pondpedia.compose.pondpedia.presentation.screens.home

import android.util.Log
import androidx.compose.foundation.layout.Box
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

@Composable
fun TestScreen() {
    Log.d("TestScreen", "Test Screen is Displayed!")

    Box(
        modifier = Modifier,
        contentAlignment = Alignment.Center
    ) {
        Text(text = "This is a Test Screen")
    }
}