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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.pondpedia.compose.pondpedia.domain.model.ponds.PondDummyGenerator.getDummyPondLogList
import com.pondpedia.compose.pondpedia.presentation_copy.screens.home.ponds.components.PondItemHexagonCard
import com.pondpedia.compose.pondpedia.presentation_copy.screens.home.ponds.components.viewmodel.PondsState
import com.pondpedia.compose.pondpedia.presentation_copy.ui.theme.PondPediaCustomTheme

@Preview(showBackground = true)
@Composable
fun PondScreenPreview() {
    PondPediaCustomTheme(darkTheme = false) {
        PondsScreen(
            pondsState = PondsState(
                pondLogList = getDummyPondLogList(5)
            ),
            onTabIndexSelected = {},
            onPondClicked = {},
            onRouteChanged = {},
            onPondListDisplayed = {},
        )
    }
}
@Preview(showBackground = true)
@Composable
fun PondScreenDarkPreview() {
    PondPediaCustomTheme(darkTheme = true) {
        PondsScreen(
            pondsState = PondsState(
                pondLogList = getDummyPondLogList(5)
            ),
            onTabIndexSelected = {},
            onPondClicked = {},
            onRouteChanged = {},
            onPondListDisplayed = {},
        )
    }
}


@Composable
fun PondsScreen(
    pondsState: PondsState,
    onTabIndexSelected: (Int) -> Unit,
    onPondClicked :(String) -> Unit,
    onRouteChanged: (String) -> Unit,
    onPondListDisplayed : () -> Unit,
) {
    onTabIndexSelected(0)

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
            onPondClicked = onPondClicked,
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
    val selectedTabIndex = remember(pondsState.selectedTabIndex) { pondsState.selectedTabIndex }

    TabRow(selectedTabIndex = selectedTabIndex) {
        pondsState.pondTabList.forEachIndexed { index, tab ->
            Tab(
                text = {
                    Text(text = tab, fontSize = 12.sp, maxLines = 1)
                },
                selected = tab == pondsState.pondTabList[0],
                onClick = { onTabIndexSelected(index) ; onPondListDisplayed()}
            )
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun PondsLazyListContent(
    pondsState: PondsState,
    onPondClicked :(String) -> Unit,
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
        val pondLogList = pondsState.pondLogList
        item {
            Box(modifier = Modifier.height(47.dp))
        }
        items(pondLogList.size) { index ->
            PondItemHexagonCard(
                pond = pondLogList[index].pondData,
                onPondClicked = onPondClicked,
                onRouteChanged = onRouteChanged,
            )
        }
    }
}
@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {
    PondPediaCustomTheme(darkTheme = true) {
//        HomeScreen(
//            mainState = MainState(),
//            pondsState = PondsState(
//                pondTabList = listOf("Semua", "Lele", "Nila"),
//                pondLogList = getDummyPondLogList(5)
//            ),
//            pondCreateState = PondState(),
//            onNavItemSelected = {},
//            onTabIndexSelected = {},
//            onPondClicked = {},
//            onRouteChanged = {},
//            onPondListDisplayed = {},
//            setFilterCategorized = {},
//            setFilterPrioritized = {},
//            setFilterHarvested = {},
//            setDisplayActionMenu = {},
//            setDisplayActionScreen = {}
//        )
    }
}