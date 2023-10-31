package com.pondpedia.compose.pondpedia.presentation.components.navigation.graphs

import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.pondpedia.compose.pondpedia.presentation_copy.Graph
import com.pondpedia.compose.pondpedia.presentation_copy.PondPediaAppState
import com.pondpedia.compose.pondpedia.presentation_copy.screens.home.TestScreen
import com.pondpedia.compose.pondpedia.presentation_copy.screens.home.components.Screens
import com.pondpedia.compose.pondpedia.presentation_copy.screens.home.ponds.components.viewmodel.PondViewModel
import com.pondpedia.compose.pondpedia.presentation_copy.screens.home.ponds.components.viewmodel.PondsState
import com.pondpedia.compose.pondpedia.presentation.screens.home.ponds.screens.details.screens.DetailsScreen

fun NavGraphBuilder.pondNavGraph(
    homeState: PondPediaAppState,
    pondsState: PondsState,
    onPondSaved: (String) -> Unit,
    onPondDeleted: (String) -> Unit,
    setDisplayActionMenu: (Boolean) -> Unit,
    setDisplayActionScreen: (Boolean) -> Unit,
) {
    val navController = homeState.navController

    navigation(
        route = Graph.HOME_PONDS_POND,
        startDestination = Screens.Details.route
    ) {

        composable(route = Screens.Details.route) {
            val pondViewModel = hiltViewModel<PondViewModel>()
            val pondState = pondViewModel.state
            DetailsScreen(
                homeState = homeState,
                pondsState = pondsState,
                onRouteToUpdate = { navController.navigate(Screens.Update.route) },
                onRouteToAnalytics = { navController.navigate(Screens.Analytics.route) },
                onPondDeleted = { pondId -> onPondDeleted(pondId); navController.popBackStack(route = Graph.HOME_PONDS, inclusive = true) },
                setDisplayActionMenu = setDisplayActionMenu,
                setDisplayActionScreen = setDisplayActionScreen,
            )
        }
        composable(route = Screens.Update.route) {
//            UpdateScreen(
//                pondsState = pondsState,
//                pondState = pondState,
//                onTabIndexSelected = {},
//                setDisplayActionMenu = setDisplayActionMenu,
//                setDisplayActionScreen = setDisplayActionScreen,
//                onPondSaved = { pondId -> onPondSaved(pondId); navController.navigate(Graph.HOME_PONDS) },
//            )
            TestScreen()
        }
        composable(route = Screens.Analytics.route) {

        }
    }

}
