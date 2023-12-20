package com.pondpedia.android.pondpedia.presentation.components.navigation.graphs

import android.util.Log
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.navigation
import com.google.accompanist.navigation.animation.composable
import com.pondpedia.android.pondpedia.presentation.Graph
import com.pondpedia.android.pondpedia.presentation.components.navigation.Screen
import com.pondpedia.android.pondpedia.presentation.theme.PondPediaCustomTheme
import com.pondpedia.android.pondpedia.presentation.ui.auth.AuthScreen
import com.pondpedia.android.pondpedia.presentation.ui.auth.components.viewmodel.AuthViewModel
import com.pondpedia.android.pondpedia.presentation.ui.auth.sign_in.SignInScreen
import com.pondpedia.android.pondpedia.presentation.ui.auth.sign_in.viewmodel.SignInViewModel
import com.pondpedia.android.pondpedia.presentation.ui.auth.sign_up.SignUpScreen
import com.pondpedia.android.pondpedia.presentation.ui.auth.verify_email.VerifyEmailScreen
import com.pondpedia.android.pondpedia.presentation.ui.home.more.profile.ProfileScreen

@OptIn(ExperimentalAnimationApi::class)
fun NavGraphBuilder.authNavGraph(
    navController: NavHostController,
    viewModel: AuthViewModel,
) {

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
            val authState by viewModel.state.collectAsState()

            val signInViewModel: SignInViewModel = hiltViewModel()

            PondPediaCustomTheme ( darkTheme = false ) {
                SignInScreen(
                    state = authState,
                    navigateToAuthScreen = {
                        navController.popBackStack()
                        navController.navigate(Graph.AUTHENTICATION)
                    },
                    navigateToSignUpScreen = {
                        navController.navigate(AuthScreens.SignUp.route)
                    },
                    navigateToHomeScreen = {
//                        navController.navigate(Graph.HOME)
                        NavigateToHomeScreen(
                            navController = navController
                        )
                    },
                    viewModel = signInViewModel
                )
            }
        }
        composable(
            route = AuthScreens.SignUp.route
        ) {
            val authState by viewModel.state.collectAsState()

            PondPediaCustomTheme ( darkTheme = false ) {
                SignUpScreen(
                    state = authState,
                    navigateToAuthScreen = {
                        navController.popBackStack()
                        navController.navigate(Graph.AUTHENTICATION)
                    },
                    navigateToSignInScreen = {
                        navController.navigate(AuthScreens.SignIn.route)
                    },
                    onEvent = viewModel::onEvent
                )
            }
        }
        composable(
            route = Screen.VerifyEmailScreen.route
        ) {
            VerifyEmailScreen(
                navigateToHomeScreen = {
                    navController.navigate(Graph.HOME) {
                        popUpTo(navController.graph.id) {
                            inclusive = true
                        }
                    }
                }
            )
        }

        composable(
            route = Screen.ProfileScreen.route
        ) {
            ProfileScreen()
        }
    }

}

fun NavigateToHomeScreen(
    navController: NavHostController
) = navController.navigate(Graph.HOME) {
    Log.d("MainActivity", "Navigate to Home Screen")
    popUpTo(navController.graph.id) {
        inclusive = true
    }
}

sealed class AuthScreens(val route: String) {
    data object Auth : AuthScreens(route = "AUTH")
    data object SignIn : AuthScreens(route = "SIGN_IN")
    data object SignUp : AuthScreens(route = "SIGN_UP")
    data object Forgot : AuthScreens(route = "FORGOT")
}