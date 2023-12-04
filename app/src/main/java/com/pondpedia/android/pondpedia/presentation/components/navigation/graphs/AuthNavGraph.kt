package com.pondpedia.android.pondpedia.presentation.components.navigation.graphs

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.navigation
import com.google.accompanist.navigation.animation.composable
import com.pondpedia.android.pondpedia.presentation.Graph
import com.pondpedia.android.pondpedia.presentation.screens.auth.AuthScreen
import com.pondpedia.android.pondpedia.presentation.screens.auth.sign_in.SignInScreen
import com.pondpedia.android.pondpedia.presentation.screens.auth.sign_up.SignUpScreen
import com.pondpedia.android.pondpedia.presentation.screens.auth.components.data.AuthState
import com.pondpedia.android.pondpedia.presentation.ui.theme.PondPediaCustomTheme

@OptIn(ExperimentalAnimationApi::class)
fun NavGraphBuilder.authNavGraph(navController: NavHostController) {
    navigation(
        route = Graph.AUTHENTICATION,
        startDestination = AuthScreens.Auth.route
    ) {
        composable(
            route = AuthScreens.Auth.route
        ) {
            PondPediaCustomTheme ( darkTheme = false ) {
                AuthScreen(
                    navigateToSignInScreen = {
                        navController.navigate(AuthScreens.SignIn.route)
                    },
                    navigateToSignUpScreen = {
                        navController.navigate(AuthScreens.SignUp.route)
                    },
                    onGuestSignIn = {
                        navController.navigate(Graph.HOME)
                    }
                )
            }
        }
        composable(
            route = AuthScreens.SignIn.route
        ) {

            PondPediaCustomTheme ( darkTheme = false ) {
                SignInScreen(
                    state = AuthState(),
                    navigateToAuthScreen = {
                        navController.popBackStack()
                        navController.navigate(Graph.AUTHENTICATION)
                    },
                    navigateToSignUpScreen = {
                        navController.navigate(AuthScreens.SignUp.route)
                    },
                    onGoogleSignInClick = {},
                    onEmailPasswordSignInClick = { _, _ -> }
                )
            }
        }
        composable(
            route = AuthScreens.SignUp.route
        ) {

            PondPediaCustomTheme ( darkTheme = false ) {
                SignUpScreen(
                    state = AuthState(),
                    navigateToAuthScreen = {
                        navController.popBackStack()
                        navController.navigate(Graph.AUTHENTICATION)
                    },
                    navigateToSignInScreen = {
                        navController.navigate(AuthScreens.SignIn.route)
                    },
                    onEmailPasswordSignUpClick = { _, _ -> }
                )
            }
        }
    }
}

sealed class AuthScreens(val route: String) {
    data object Auth : AuthScreens(route = "AUTH")
    data object SignIn : AuthScreens(route = "SIGN_IN")
    data object SignUp : AuthScreens(route = "SIGN_UP")
    data object Forgot : AuthScreens(route = "FORGOT")
}