package com.pondpedia.android.pondpedia.presentation

import android.util.Log
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.google.accompanist.navigation.animation.composable
import com.pondpedia.android.pondpedia.MainViewModel
import com.pondpedia.android.pondpedia.presentation.components.navigation.graphs.authNavGraph
import com.pondpedia.android.pondpedia.presentation.navigation.Screen
import com.pondpedia.android.pondpedia.presentation.ui.auth.components.viewmodel.AuthViewModel
import com.pondpedia.android.pondpedia.presentation.ui.home.HomeScreen

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun PondPediaApp(
    viewModel: MainViewModel = hiltViewModel(),
    authViewModel: AuthViewModel = hiltViewModel(),
    navController: NavHostController,
) {

    AnimatedNavHost(
        navController = navController,
        startDestination = Graph.AUTHENTICATION,
        enterTransition = { EnterTransition.None },
        exitTransition = { ExitTransition.None }
    ) {
        authNavGraph(
            navController = navController,
            viewModel = authViewModel,
        )
        composable(
            route = Graph.HOME
        ) {
            HomeScreen(
                navigateToAuthScreen = {
                    navController.popBackStack()
                    navController.navigate(Graph.AUTHENTICATION)
                }
            )
        }
    }
    AuthState(
        viewModel = viewModel,
        navController = navController
    )

}
@Composable
private fun AuthState(
    viewModel: MainViewModel,
    navController: NavHostController
) {
    val isUserSignedOut = viewModel.getAuthState().collectAsState().value
    if (isUserSignedOut) {
        NavigateToAuthScreen(
            navController = navController
        )
    } else {
        NavigateToHomeScreen(
            navController = navController
        )
    }
}

@Composable
private fun NavigateToHomeScreen(
    navController: NavHostController
) = navController.navigate(Graph.HOME) {
    Log.d("MainActivity", "Navigate to Home Screen")
    popUpTo(navController.graph.id) {
        inclusive = true
    }
}
@Composable
private fun NavigateToAuthScreen(
    navController: NavHostController
) = navController.navigate(Graph.AUTHENTICATION) {
    Log.d("MainActivity", "Navigate to Auth Screen")
    popUpTo(navController.graph.id) {
        inclusive = true
    }
}

@Composable
private fun NavigateToProfileScreen(
    navController: NavHostController
) = navController.navigate(Screen.ProfileScreen.route) {
    popUpTo(navController.graph.id) {
        inclusive = true
    }
}

@Composable
private fun NavigateToVerifyEmailScreen(
    navController: NavHostController
) = navController.navigate(Screen.VerifyEmailScreen.route) {
    Log.d("MainActivity", "Navigate to Verify Email Screen")
    popUpTo(navController.graph.id) {
        inclusive = true
    }
}

object Graph {
    const val ROOT = "root_graph"
    const val AUTHENTICATION = "auth_graph"
    const val SIGNIN = "auth_signin_graph"
    const val SIGNUP = "auth_signup_graph"
    const val HOME = "home_graph"
    const val HOMED = "homed_graph"


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