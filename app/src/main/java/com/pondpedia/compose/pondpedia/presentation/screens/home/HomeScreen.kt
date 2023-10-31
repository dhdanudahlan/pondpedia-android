
package com.pondpedia.compose.pondpedia.presentation.screens.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FilterList
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.SnackbarResult
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.pondpedia.compose.pondpedia.presentation_copy.PondPediaAppState
import com.pondpedia.compose.pondpedia.presentation.components.navigation.graphs.HomeBottomNavGraph
import com.pondpedia.compose.pondpedia.presentation_copy.rememberPondPediaAppState
import com.pondpedia.compose.pondpedia.presentation_copy.screens.home.components.HomeBottomNavBar
import com.pondpedia.compose.pondpedia.presentation_copy.screens.home.components.HomeTopAppBar
import com.pondpedia.compose.pondpedia.presentation_copy.screens.home.components.Screens
import com.pondpedia.compose.pondpedia.presentation_copy.screens.home.ponds.components.FloatingActionButtonCompose


//val bottomNavItems = listOf(
//    BottomNavItem.PONDS,
//    BottomNavItem.UPDATES,
//    BottomNavItem.MENU,
//    BottomNavItem.EXPLORE,
//    BottomNavItem.MORE
//)
//@Preview(showBackground = true)
//@Composable
//fun HomeScreenPreview() {
//    PondPediaCustomTheme(darkTheme = false) {
//        Screens(
//            mainState = MainState(),
//            pondsState = PondsState(
//                pondTabList = listOf("Semua", "Lele", "Nila"),
//                pondLogList = PondDummyGenerator.getDummyPondLogList(5)
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
//            setDisplayActionScreen = {},
//        )
//    }
//}
//@Preview(showBackground = true)
//@Composable
//fun HomeScreenDarkPreview() {
//    PondPediaCustomTheme(darkTheme = true) {
//        Screens(
//            mainState = MainState(),
//            pondsState = PondsState(
//                pondTabList = listOf("Semua", "Lele", "Nila"),
//                pondLogList = PondDummyGenerator.getDummyPondLogList(5)
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
//            setDisplayActionScreen = {},
//        )
//
//    }
//}
//@Composable
//fun Screens(
//    mainState: MainState,
//    pondsState: PondsState,
//    pondCreateState: PondState,
//    onNavItemSelected: (BottomNavItem) -> Unit,
//    onTabIndexSelected: (Int) -> Unit,
//    onPondClicked: (String) -> Unit,
//    onRouteChanged: (String) -> Unit,
//    onPondListDisplayed: () -> Unit,
//    setFilterCategorized: (PondsCategoryFilterType) -> Unit,
//    setFilterPrioritized: (PondsPriorityFilterType) -> Unit,
//    setFilterHarvested: (PondsHarvestFilterType) -> Unit,
//    setDisplayActionMenu: (Boolean) -> Unit,
//    setDisplayActionScreen: (Boolean) -> Unit,
//) {
//    val selectedNavItem = remember(mainState.currentRoute) {mainState.selectedNavItem}
//    Scaffold(
//        topBar = {
//            TopActionBar(
//                mainState = mainState,
//                pondsState = pondsState,
//                pondCreateState = pondCreateState,
//                setDisplayActionMenu = setDisplayActionMenu,
//                setDisplayActionScreen = setDisplayActionScreen,
//                onRouteChanged = onRouteChanged,
//                onPondSaved = {},
//                onPondDeleted = {},
//            )
//        },
//        bottomBar = {
//            BottomNavigationBar(
//                items = bottomNavItems,
//                selectedItem = mainState.selectedNavItem,
//                onNavItemSelected = { navItem ->
//                    onNavItemSelected(navItem)
//                }
//            )
//        },
//        floatingActionButton = { if (selectedNavItem == BottomNavItem.PONDS) FloatingActionButtonCompose( onRouteChanged = onRouteChanged ) },
//        floatingActionButtonPosition = FabPosition.End
//    ) { innerPadding ->
//        Box(
//            Modifier
//                .padding(innerPadding)
//                .background(MaterialTheme.colorScheme.background)
//        ) {
//            when(mainState.selectedNavItem) {
//                BottomNavItem.PONDS -> PondsScreen(
//                    mainState = mainState,
//                    pondsState = pondsState,
//                    setTopActionBarItem = {},
//                    onTabIndexSelected = onTabIndexSelected,
//                    onPondClicked = onPondClicked,
//                    onRouteChanged = onRouteChanged,
//                    onPondListDisplayed = onPondListDisplayed
//                )
//                BottomNavItem.UPDATES -> Text(text = "UPDATES")
//                BottomNavItem.MENU -> Text(text = "MENU")
//                BottomNavItem.EXPLORE -> Text(text = "EXPLORE")
//                BottomNavItem.MORE -> Text(text = "MORE")
//            }
//        }
//    }
//}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    homeState: PondPediaAppState = rememberPondPediaAppState()
) {
    val snackbarHostState = remember { SnackbarHostState() }

    var showSettingsDialog by rememberSaveable {
        mutableStateOf(false)
    }
    
    var showSearchBar by rememberSaveable {
        mutableStateOf(false)
    }
    val unreadDestinations = emptySet<Screens>()
    Scaffold(
        snackbarHost = { SnackbarHost(snackbarHostState) },
        bottomBar = {
            if (homeState.shouldShowBottomBar) {
                HomeBottomNavBar(
                    destinations = homeState.homeScreenDestinations,
                    destinationsWithUnreadResources = unreadDestinations,
                    onNavigateToDestination = homeState::navigateToHomeScreenDestination,
                    currentDestination = homeState.currentDestination
                )
            }
        },
        floatingActionButton = {
            if (homeState.shouldShowFloatingActionButton) {
                FloatingActionButtonCompose(onRouteChanged = homeState::navigateToHomeScreenDestination)
            }
        },
    ) { innerPadding ->
        Row(
            Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            Column(
                Modifier.fillMaxSize()
            ) {
                val homeDestination = homeState.currentHomeScreenDestination
                if (homeDestination != null) {
                    HomeTopAppBar(
                        titleRes = homeDestination.titleTextId,
                        actionSearchIcon = Icons.Default.Search,
                        actionSearchIconContentDescription = stringResource(
                            id = homeDestination.titleTextId,
                        ),
                        actionOptionsIcon = Icons.Default.FilterList,
                        actionOptionsIconContentDescription = stringResource(
                            id = homeDestination.titleTextId,
                        ),
                        onActionSearchClick =  { showSearchBar = true },
                        onActionOptionsClick = { showSettingsDialog = true }
                    )
                }

                HomeBottomNavGraph(
                    homeState = homeState,
                    onShowSnackbar = { message, action ->
                        snackbarHostState.showSnackbar(
                            message = message,
                            actionLabel = action,
                            duration = SnackbarDuration.Short
                        ) == SnackbarResult.ActionPerformed
                    }
                )
            }
        }
    }
}


