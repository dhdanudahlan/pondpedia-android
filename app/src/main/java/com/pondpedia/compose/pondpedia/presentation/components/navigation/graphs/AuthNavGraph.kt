package com.pondpedia.compose.pondpedia.presentation.components.navigation.graphs

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.pondpedia.compose.pondpedia.presentation_copy.Graph

fun NavGraphBuilder.authNavGraph(navController: NavHostController) {
    navigation(
        route = Graph.AUTHENTICATION,
        startDestination = AuthScreens.SignIn.route
    ) {
        composable(route = AuthScreens.SignIn.route) {
        }
        composable(route = AuthScreens.SignUp.route) {
        }
        composable(route = AuthScreens.Forgot.route) {
        }
    }
}

sealed class AuthScreens(val route: String) {
    object SignIn : AuthScreens(route = "SIGN_IN")
    object SignUp : AuthScreens(route = "SIGN_UP")
    object Forgot : AuthScreens(route = "FORGOT")
}