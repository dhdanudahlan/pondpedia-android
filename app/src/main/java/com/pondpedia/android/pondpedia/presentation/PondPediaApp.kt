package com.pondpedia.android.pondpedia.presentation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.pondpedia.android.pondpedia.presentation.components.navigation.graphs.authNavGraph
import com.pondpedia.android.pondpedia.presentation.screens.home.HomeScreen

@Composable
fun PondPediaApp(
    navController: NavHostController
) {
    NavHost(
        navController = navController,
        route = Graph.ROOT,
        startDestination = Graph.HOME
    ) {
        authNavGraph(
            navController = navController
        )
        composable(route = Graph.HOME) {
            HomeScreen()
        }
    }
}

object Graph {
    const val ROOT = "root_graph"
    const val AUTHENTICATION = "auth_graph"
    const val HOME = "home_graph"


    const val HOME_PONDS = "home_ponds_graph"
    const val HOME_UPDATES = "home_updates_graph"
    const val HOME_MENU = "home_menu_graph"
    const val HOME_EXPLORE = "home_explore_graph"
    const val HOME_MORE = "home_more_graph"

    const val HOME_PONDS_POND = "home_ponds_pond_graph"
    const val HOME_PONDS_POND_CREATE = "home_ponds_pond_create_graph"
    const val HOME_PONDS_POND_DETAILS = "home_ponds_pond_details_graph"
    const val HOME_PONDS_POND_UPDATE = "home_ponds_pond_update_graph"



}