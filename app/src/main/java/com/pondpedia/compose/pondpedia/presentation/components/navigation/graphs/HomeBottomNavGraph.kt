package com.pondpedia.compose.pondpedia.presentation.components.navigation.graphs

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.pondpedia.compose.pondpedia.presentation.Graph
import com.pondpedia.compose.pondpedia.presentation.PondPediaAppState
import com.pondpedia.compose.pondpedia.presentation.screens.home.components.Screens
import com.pondpedia.compose.pondpedia.presentation.screens.home.menu.screens.MenuScreen
import com.pondpedia.compose.pondpedia.presentation.screens.home.ponds.components.viewmodel.PondsEvent
import com.pondpedia.compose.pondpedia.presentation.screens.home.ponds.components.viewmodel.PondsState
import com.pondpedia.compose.pondpedia.presentation.screens.home.ponds.screens.PondsScreen

@Composable
fun HomeBottomNavGraph(
    homeState: PondPediaAppState,
    pondsState: PondsState,
    onEvent: (PondsEvent) -> Unit,
    startDestination: String = Screens.Ponds.route,
) {
    val navController = homeState.navController

    NavHost(
        navController = navController,
        route = Graph.HOME,
        startDestination = startDestination
    ) {
        pondsNavGraph(
            homeState = homeState,
            pondsState = pondsState,
            onTabIndexSelected = /*pondsViewModel::setSelectedTab*/ {},
            onPondListDisplayed = /*pondsViewModel::getPondList*/{},
            onEvent = onEvent
        )
        updatesNavGraph(homeState = homeState)
        menuNavGraph(homeState = homeState)
        exploreNavGraph(homeState = homeState)
        moreNavGraph(homeState = homeState)

        pondNavGraph(
            homeState = homeState,
            pondsState = pondsState,
            onPondSaved = /*pondViewModel::addPondToDatabase*/{},
            onPondDeleted = /*pondsViewModel::deletePond*/{},
            setDisplayActionScreen = {},
            setDisplayActionMenu = {}
        )
    }
}

fun NavGraphBuilder.pondsNavGraph(
    homeState: PondPediaAppState,
    pondsState: PondsState,
    onTabIndexSelected: (Int) -> Unit,
    onPondListDisplayed : () -> Unit,
    onEvent: (PondsEvent) -> Unit,
) {
    val navController = homeState.navController

    composable( route = Screens.Ponds.route ) {
        PondsScreen(
            pondsState = pondsState,
            onTabIndexSelected = onTabIndexSelected,
            onRouteChanged = { route ->
                navController.navigate(route)
            },
            onPondListDisplayed = onPondListDisplayed,
            onEvent = onEvent,
        )
    }
}
fun NavGraphBuilder.updatesNavGraph(
    homeState: PondPediaAppState,
) {
    composable(route = Screens.Updates.route) {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            Text(text = "Under Construction...")
        }
    }
}
fun NavGraphBuilder.menuNavGraph2(
    homeState: PondPediaAppState,
) {

    val navController = homeState.navController
    composable(route = Screens.Menu.route) {
        MenuScreen(
            onRouteChanged = { route ->
                navController.navigate(route)
            },
        )
    }
}
fun NavGraphBuilder.exploreNavGraph(
    homeState: PondPediaAppState,
) {
    composable(route = Screens.Explore.route) {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            Text(text = "Under Construction...")
        }
    }
}
fun NavGraphBuilder.moreNavGraph(
    homeState: PondPediaAppState,
) {
    composable(route = Screens.More.route) {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            Text(text = "Under Construction...")
        }
    }
}





