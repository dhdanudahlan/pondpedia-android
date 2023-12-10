package com.pondpedia.android.pondpedia.presentation.ui.home.ponds.screens.pond_details.screens.update.screen
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.pondpedia.android.pondpedia.presentation.theme.PondPediaCustomTheme

@Preview(showBackground = true)
@Composable
fun UpdateScreenPreview() {
    PondPediaCustomTheme(darkTheme = false) {
        UpdateScreen(
        )
    }
}
@Composable
fun UpdateScreen(
) {
    Scaffold(
        topBar = {
        }
    ) { innerPadding ->
        Box(modifier = Modifier.padding(innerPadding)) {
            Column(
                modifier = Modifier.fillMaxSize(),
            ) {
                UpdateTabs(
                )
            }
        }
    }
}

@Composable
fun UpdateTabs(
) {
}
