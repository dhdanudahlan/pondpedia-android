@file:OptIn(ExperimentalFoundationApi::class)

package com.pondpedia.compose.pondpedia.presentation.screens.home.ponds.screens

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.pondpedia.compose.pondpedia.presentation.components.navigation.graphs.AddPondDialog
import com.pondpedia.compose.pondpedia.presentation.screens.home.ponds.components.PondItemHexagonCard
import com.pondpedia.compose.pondpedia.presentation.screens.home.ponds.components.viewmodel.PondsEvent
import com.pondpedia.compose.pondpedia.presentation.screens.home.ponds.components.viewmodel.PondsState
import com.pondpedia.compose.pondpedia.presentation.ui.theme.PondPediaCustomTheme

@Preview(showBackground = true)
@Composable
fun PondScreenPreview() {
    PondPediaCustomTheme(darkTheme = false) {
        PondsScreen(
            pondsState = PondsState(),
            onTabIndexSelected = {},
            onRouteChanged = {},
            onPondListDisplayed = {},
            onEvent = { }
        )
    }
}
@Preview(showBackground = true)
@Composable
fun PondScreenDarkPreview() {
    PondPediaCustomTheme(darkTheme = true) {
        PondsScreen(
            pondsState = PondsState(),
            onTabIndexSelected = {},
            onRouteChanged = {},
            onPondListDisplayed = {},
            onEvent = {}
        )
    }
}


@Composable
fun PondsScreen(
    pondsState: PondsState,
    onTabIndexSelected: (Int) -> Unit,
    onRouteChanged: (String) -> Unit,
    onPondListDisplayed : () -> Unit,
    onEvent: (PondsEvent) -> Unit,
) {
    onTabIndexSelected(0)
    if (pondsState.isAddingPond) {
        AddPondDialog(pondsState, onEvent)
    }

    Column(
        modifier = Modifier.fillMaxSize(),
    ) {
        PondsTabs(
            pondsState = pondsState,
            onTabIndexSelected = onTabIndexSelected,
            onPondListDisplayed = onPondListDisplayed
        )
        PondsLazyListContent(
            pondsState = pondsState,
            onEvent = onEvent,
            onRouteChanged = onRouteChanged
        )
    }
}

@Composable
fun PondsTabs(
    pondsState: PondsState,
    onTabIndexSelected: (Int) -> Unit,
    onPondListDisplayed : () -> Unit,
) {
    val selectedCategoryIndex = remember(pondsState.selectedCategoryIndex) { pondsState.selectedCategoryIndex }

    TabRow(selectedTabIndex = selectedCategoryIndex) {
        pondsState.categories.forEachIndexed { index, tab ->
            Tab(
                text = {
                    Text(text = tab.categoryName, fontSize = 12.sp, maxLines = 1, color = Color.Cyan)
                },
                selected = tab == pondsState.categories[0],
                onClick = { onTabIndexSelected(index) ; onPondListDisplayed()}
            )
        }
//        Tab(
//            text = {
//                Text(text = "tab.categoryName", fontSize = 12.sp, maxLines = 1)
//            },
//            selected = "tab" == "pondsState.categories[0]",
//            onClick = { }
//        )
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun PondsLazyListContent(
    pondsState: PondsState,
    onEvent: (PondsEvent) -> Unit,
    onRouteChanged: (String) -> Unit,
) {

//    val horizontalGridSize by remember { mutableStateOf(2) }
//    LazyVerticalGrid(
//        columns = GridCells.Fixed(horizontalGridSize)
//    ) {
//        val pondLogList = pondsState.pondLogList
//        items(pondLogList.size) {
//            PondItemSquareCard(
//                pond = pondLogList[it].pondData,
//                onPondClicked = onPondClicked
//            )
//        }
//    }

    LazyVerticalStaggeredGrid(
        columns = StaggeredGridCells.Fixed(2),
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(16.dp),
        horizontalArrangement = Arrangement.spacedBy((-191).dp),
        verticalItemSpacing = 107.dp,
    ) {
        val pondList = pondsState.ponds
        item {
            Box(modifier = Modifier.height(47.dp))
        }
        items(pondList.size) { index ->
            PondItemHexagonCard(
                pond = pondList[index],
                onEvent = onEvent,
                onRouteChanged = onRouteChanged,
            )
        }
    }
}