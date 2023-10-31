package com.pondpedia.compose.pondpedia.presentation.components.navigation.graphs

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.pondpedia.compose.pondpedia.presentation_copy.Graph
import com.pondpedia.compose.pondpedia.presentation_copy.PondPediaAppState
import com.pondpedia.compose.pondpedia.presentation_copy.screens.home.components.Screens
import com.pondpedia.compose.pondpedia.presentation_copy.screens.home.ponds.components.viewmodel.PondState
import com.pondpedia.compose.pondpedia.presentation_copy.screens.home.ponds.components.viewmodel.PondViewModel
import com.pondpedia.compose.pondpedia.presentation_copy.screens.home.ponds.components.viewmodel.PondsState
import com.pondpedia.compose.pondpedia.presentation_copy.screens.home.ponds.components.viewmodel.PondsViewModel
import com.pondpedia.compose.pondpedia.presentation.screens.home.ponds.screens.PondsScreen
import com.pondpedia.compose.pondpedia.presentation_copy.screens.home.ponds.screens.create.screens.CreateScreen

@Composable
fun HomeBottomNavGraph(
    homeState: PondPediaAppState,
    onShowSnackbar: suspend (String, String?) -> Boolean,
    modifier: Modifier = Modifier,
    startDestination: String = Screens.Ponds.route,
) {
    val navController = homeState.navController

    val pondsViewModel = hiltViewModel<PondsViewModel>()
    val pondsState by pondsViewModel.state

    val pondViewModel = hiltViewModel<PondViewModel>()
    val pondState by pondViewModel.state

    NavHost(
        navController = navController,
        route = Graph.HOME,
        startDestination = startDestination
    ) {
        pondsNavGraph(
            homeState = homeState,
            pondsState = pondsState,
            onTabIndexSelected = pondsViewModel::setSelectedTab,
            onPondClicked = pondsViewModel::setSelectedPondId,
            onPondListDisplayed = pondsViewModel::getPondList,
        )
        updatesNavGraph(homeState = homeState)
        menuNavGraph(homeState = homeState)
        exploreNavGraph(homeState = homeState)
        moreNavGraph(homeState = homeState)

//        pondCreateNavGraph(
//            homeState = homeState,
//            pondsState = pondsState,
//            pondState = pondState,
//            onTabIndexSelected = pondsViewModel::setSelectedTab,
//            onPondSaved = pondViewModel::addPondToDatabase,
//        )

        com.pondpedia.compose.pondpedia.presentation_copy.components.navigation.graphs.pondNavGraph(
            homeState = homeState,
            pondsState = pondsState,
            onPondSaved = pondViewModel::addPondToDatabase,
            onPondDeleted = pondsViewModel::deletePond,
            setDisplayActionScreen = {},
            setDisplayActionMenu = {}
        )
    }
}

fun NavGraphBuilder.pondsNavGraph(
    homeState: PondPediaAppState,
    pondsState: PondsState,
    onTabIndexSelected: (Int) -> Unit,
    onPondClicked :(String) -> Unit,
    onPondListDisplayed : () -> Unit,
) {
    val navController = homeState.navController

    composable( route = Screens.Ponds.route ) {
        val pondsViewModel = hiltViewModel<PondsViewModel>()
        val pondsState = remember { pondsViewModel.state }
        PondsScreen(
            pondsState = pondsState.value,
            onTabIndexSelected = onTabIndexSelected,
            onPondClicked = onPondClicked,
            onRouteChanged = { route ->
                navController.navigate(route)
            },
            onPondListDisplayed = onPondListDisplayed,
        )
    }
}
fun NavGraphBuilder.updatesNavGraph(
    homeState: PondPediaAppState,
) {
    composable(route = Screens.Updates.route) {
        Text(text = Screens.Updates.route)
    }
}
fun NavGraphBuilder.menuNavGraph(
    homeState: PondPediaAppState,
) {
    composable(route = Screens.Menu.route) {
        Text(text = Screens.Menu.route)
    }
}
fun NavGraphBuilder.exploreNavGraph(
    homeState: PondPediaAppState,
) {
    composable(route = Screens.Explore.route) {
        Text(text = Screens.Explore.route)
    }
}
fun NavGraphBuilder.moreNavGraph(
    homeState: PondPediaAppState,
) {
    composable(route = Screens.More.route) {
        Text(text = Screens.More.route)
    }
}

fun NavGraphBuilder.pondCreateNavGraphn(
    homeState: PondPediaAppState,
    pondsState: PondsState,
    pondState: PondState,
    onTabIndexSelected: (Int) -> Unit,
    onPondSaved: (String) -> Unit,
) {
    composable(route = Screens.Create.route) {
        CreateScreen(
            homeState = homeState,
            pondsState = pondsState,
            pondState = pondState,
            onTabIndexSelected = onTabIndexSelected,
            onPondSaved = onPondSaved,
        )
    }
}



