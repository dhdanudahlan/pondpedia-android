package com.pondpedia.android.pondpedia.presentation.navigation

import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.google.accompanist.navigation.animation.composable
import com.pondpedia.android.pondpedia.presentation.Graph
import com.pondpedia.android.pondpedia.presentation.ui.auth.AuthScreen
import com.pondpedia.android.pondpedia.presentation.ui.auth.sign_in.SignInScreen
import com.pondpedia.android.pondpedia.presentation.ui.auth.sign_up.SignUpScreen
import com.pondpedia.android.pondpedia.presentation.ui.auth.components.data.AuthState
import com.pondpedia.android.pondpedia.presentation.theme.PondPediaCustomTheme

@Composable
@ExperimentalAnimationApi
fun NavGraph(
    navController: NavHostController
) {
    AnimatedNavHost(
        navController = navController,
        startDestination = Graph.AUTHENTICATION,
        enterTransition = {EnterTransition.None },
        exitTransition = { ExitTransition.None }
    ) {
        composable(
            route = Graph.AUTHENTICATION
        ) {
            PondPediaCustomTheme ( darkTheme = false ) {
                AuthScreen(
                    navigateToSignInScreen = {
                        navController.navigate(Graph.SIGNIN)
                    },
                    navigateToSignUpScreen = {
                        navController.navigate(Graph.SIGNUP)
                    },
                    onGuestSignIn = {
                        navController.navigate(Graph.SIGNUP)
                    }
                )
            }
        }
        composable(
            route = Graph.SIGNIN
        ) {

            PondPediaCustomTheme ( darkTheme = false ) {
                SignInScreen(
                    state = AuthState(),
                    navigateToAuthScreen = {
                        navController.popBackStack()
                        navController.navigate(Graph.AUTHENTICATION)
                    },
                    navigateToSignUpScreen = {
                        navController.navigate(Graph.SIGNUP)
                    },
                    onGoogleSignInClick = {},
                    onEmailPasswordSignInClick = { _, _ -> }
                )
            }
        }
        composable(
            route = Graph.SIGNUP
        ) {

            PondPediaCustomTheme ( darkTheme = false ) {
                SignUpScreen(
                    state = AuthState(),
                    navigateToAuthScreen = {
                        navController.popBackStack()
                        navController.navigate(Graph.AUTHENTICATION)
                    },
                    navigateToSignInScreen = {
                        navController.navigate(Graph.SIGNIN)
                    },
                    onEmailPasswordSignUpClick = { _, _ -> },
                    onEvent = { _ -> }
                )
            }
        }
    }
}