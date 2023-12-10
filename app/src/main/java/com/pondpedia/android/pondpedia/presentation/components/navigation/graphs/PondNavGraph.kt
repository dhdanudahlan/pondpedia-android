package com.pondpedia.android.pondpedia.presentation.components.navigation.graphs

import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.pondpedia.android.pondpedia.presentation.Graph
import com.pondpedia.android.pondpedia.presentation.PondPediaAppState
import com.pondpedia.android.pondpedia.presentation.ui.home.TestScreen
import com.pondpedia.android.pondpedia.presentation.ui.home.components.Screens
import com.pondpedia.android.pondpedia.presentation.ui.home.ponds.components.viewmodel.PondDetailsEvent
import com.pondpedia.android.pondpedia.presentation.ui.home.ponds.components.viewmodel.PondDetailsViewModel
import com.pondpedia.android.pondpedia.presentation.ui.home.ponds.components.viewmodel.PondsState
import com.pondpedia.android.pondpedia.presentation.ui.home.ponds.screens.pond_details.screens.DetailsScreen

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
            val viewModel = hiltViewModel<PondDetailsViewModel>()
            val pondManagementState by viewModel.state.collectAsState()
            viewModel.onEvent(PondDetailsEvent.SetPondId(pondsState.selectedPondId))

            DetailsScreen(
                homeState = homeState,
                pondsState = pondsState,
                pondDetailsState = pondManagementState,
                onEvent = viewModel::onEvent
//                onRouteToUpdate = { navController.navigate(Screens.Update.route) },
//                onRouteToAnalytics = { navController.navigate(Screens.Analytics.route) },
//                onPondDeleted = { pondId -> onPondDeleted(pondId); navController.popBackStack(route = Graph.HOME_PONDS, inclusive = true) },
//                setDisplayActionMenu = setDisplayActionMenu,
//                setDisplayActionScreen = setDisplayActionScreen,
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
