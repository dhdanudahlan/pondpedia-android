package com.pondpedia.android.pondpedia.presentation

import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.google.accompanist.navigation.animation.composable
import com.pondpedia.android.pondpedia.presentation.components.navigation.graphs.authNavGraph
import com.pondpedia.android.pondpedia.presentation.ui.auth.components.viewmodel.AuthViewModel
import com.pondpedia.android.pondpedia.presentation.ui.home.HomeScreen

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun PondPediaApp(
    navController: NavHostController
) {
//    HomeScreen()

    val authViewModel = hiltViewModel<AuthViewModel>()
    val authState by authViewModel.state.collectAsState()
    AnimatedNavHost(
        navController = navController,
        startDestination = Graph.AUTHENTICATION,
        enterTransition = { EnterTransition.None },
        exitTransition = { ExitTransition.None }
    ) {
        authNavGraph(
            navController = navController,
            viewModel = authViewModel,
            state = authState
        )
        composable(
            route = Graph.HOME
        ) {
            HomeScreen()
        }
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