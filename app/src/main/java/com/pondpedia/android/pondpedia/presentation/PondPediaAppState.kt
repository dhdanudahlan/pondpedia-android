package com.pondpedia.android.pondpedia.presentation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.navigation.NavDestination
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.pondpedia.android.pondpedia.presentation.ui.home.components.Screens
import kotlinx.coroutines.CoroutineScope

@Composable
fun rememberPondPediaAppState(
    coroutineScope: CoroutineScope = rememberCoroutineScope(),
    navController: NavHostController = rememberNavController(),
): PondPediaAppState {
    return remember(
        navController,
        coroutineScope
    ) {
        PondPediaAppState(
            navController = navController,
            coroutineScope = coroutineScope,
        )
    }
}

@Stable
class PondPediaAppState(
    val navController: NavHostController,
    val coroutineScope: CoroutineScope,
) {
    val currentDestination: NavDestination?
        @Composable get() = navController
            .currentBackStackEntryAsState().value?.destination
    val currentHomeScreenDestination: Screens?
        @Composable get() = when (currentDestination?.route) {
            Graph.HOME -> Screens.Ponds
            Screens.Ponds.route -> Screens.Ponds
            Screens.Menu.route -> Screens.Menu
            Screens.More.route -> Screens.More
            else -> null
        }
    val currentPondScreenDestination: Screens?
        @Composable get() = when (currentDestination?.route) {
            Graph.HOME_PONDS_POND -> Screens.Details
            Screens.Details.route -> Screens.Details
            Screens.Update.route -> Screens.Update
            Screens.Analytics.route -> Screens.Analytics
            Screens.Add.route -> Screens.Add
            else -> null
        }

    val shouldShowTopBar: Boolean
        @Composable get() = (currentHomeScreenDestination != null)
    val shouldShowBottomBar: Boolean
        @Composable get() = (currentHomeScreenDestination != null)

    val shouldShowFloatingActionButton: Boolean
        @Composable get() = (currentHomeScreenDestination == Screens.Ponds)

    val isOffline: Boolean = false

    val homeScreenDestinations: List<Screens> = listOf(
        Screens.Ponds,
        Screens.Menu,
        Screens.More,
    )

    val screensDestinations: List<Screens> = listOf(
        Screens.Details,
        Screens.Update,
        Screens.Analytics
    )

//    val homeScreenDestinationsWithUnreadResources: StateFlow<Set<Screens>> =

    fun navigateToHomeScreenDestination(screen: Screens) {
        navController.navigate(screen.route) {
            popUpTo(navController.graph.findStartDestination().id)
            launchSingleTop = true
        }
    }
}