package com.pondpedia.compose.pondpedia.presentation.screens.home.menu.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.pondpedia.compose.pondpedia.presentation.screens.home.menu.components.MenuItemHexagonCard
import com.pondpedia.compose.pondpedia.presentation.screens.home.menu.components.MenuScreens

@Composable
fun MenuScreen(
    onRouteChanged: (String) -> Unit,
) {
    Spacer(modifier = Modifier.height(8.dp))
    val horizontalGridSize by remember { mutableStateOf(4) }
//    val menuIcons = listOf(
//        MenuIcons(icon = Icons.Default.Chat, label = "AI Chat", contentDescription = "LLM", route = "")
//    )
    val menuIcons = listOf(
        MenuScreens.AiChat
    )

    LazyVerticalGrid(
        columns = GridCells.Fixed(horizontalGridSize)
    ) {
        items(menuIcons.size) { index ->
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    MenuItemHexagonCard(
                        icon = menuIcons[index].icon,
                        contentDescription = stringResource(menuIcons[index].titleTextId),
                        route = menuIcons[index].route,
                        onRouteChanged = onRouteChanged
                    )
                    TextButton(
                        modifier = Modifier.padding(0.dp),
                        onClick = { onRouteChanged(menuIcons[index].route) }
                    ) {

                        Text(text = stringResource(menuIcons[index].labelTextId))
                    }
                }
            }
        }
    }
}

data class MenuIcons(
    val icon: ImageVector,
    val label: String,
    val contentDescription: String,
    val route: String
)