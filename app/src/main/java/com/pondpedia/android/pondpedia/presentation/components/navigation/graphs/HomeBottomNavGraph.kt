package com.pondpedia.android.pondpedia.presentation.components.navigation.graphs

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.pondpedia.android.pondpedia.presentation.Graph
import com.pondpedia.android.pondpedia.presentation.PondPediaAppState
import com.pondpedia.android.pondpedia.presentation.ui.home.components.Screens
import com.pondpedia.android.pondpedia.presentation.ui.home.more.profile.ProfileScreen
import com.pondpedia.android.pondpedia.presentation.ui.home.more.screens.MoreScreen
import com.pondpedia.android.pondpedia.presentation.ui.home.ponds.components.AddPondScreen
import com.pondpedia.android.pondpedia.presentation.ui.home.ponds.components.viewmodel.PondsEvent
import com.pondpedia.android.pondpedia.presentation.ui.home.ponds.components.viewmodel.PondsState
import com.pondpedia.android.pondpedia.presentation.ui.home.ponds.screens.PondsScreen

@Composable
fun HomeBottomNavGraph(
    homeState: PondPediaAppState,
    pondsState: PondsState,
    onEvent: (PondsEvent) -> Unit,
    startDestination: String = Screens.Ponds.route,
    navigateToAuthScreen: () -> Unit
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
        menuNavGraph(homeState = homeState)
        moreNavGraph(
            homeState = homeState,
            navigateToAuthScreen = navigateToAuthScreen
        )

        pondNavGraph(
            homeState = homeState,
            pondsState = pondsState,
            onPondSaved = /*pondViewModel::addPondToDatabase*/{},
            onPondDeleted = /*pondsViewModel::deletePond*/{},
            setDisplayActionScreen = {},
            setDisplayActionMenu = {}
        )
        addPondNavGraph(
            homeState = homeState,
            pondsState = pondsState,
            onEvent = onEvent,
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

@Deprecated(
    message = "At the moment, we are not using this screen",
    replaceWith = ReplaceWith("Nothing need to be replaced"),
    level = DeprecationLevel.ERROR
)
fun NavGraphBuilder.updatesNavGraph(
    homeState: PondPediaAppState,
) {
    composable(route = Screens.Updates.route) {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            Text(text = "Under Construction...")
        }
    }
}

@Deprecated(
    message = "At the moment, we are not using this screen",
    replaceWith = ReplaceWith("Nothing need to be replaced"),
    level = DeprecationLevel.ERROR
)
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
    navigateToAuthScreen: () -> Unit
) {
    val navController = homeState.navController
    composable(route = Screens.More.route) {
        MoreScreen(
            onRouteChanged = { route ->
                navController.navigate(route)
            },
        )
    }
    composable(route = Screens.Profile.route) {
        ProfileScreen(
            navigateToAuthScreen = navigateToAuthScreen,
        )
    }
    composable(route = Screens.Settings.route) {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            Text(text = "Settings under Construction...")
        }
    }
}
fun NavGraphBuilder.addPondNavGraph(
    homeState: PondPediaAppState,
    pondsState: PondsState,
    onEvent: (PondsEvent) -> Unit,
) {
    val navController = homeState.navController
    composable(route = Screens.Add.route) {
        AddPondScreen(
            pondsState = pondsState,
            onEvent = onEvent,
            onNavigateToDestination = homeState::navigateToHomeScreenDestination,
        )
    }
}





