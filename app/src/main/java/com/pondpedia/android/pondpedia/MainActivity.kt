package com.pondpedia.android.pondpedia

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.runtime.DisposableEffect
import androidx.navigation.NavHostController
import com.google.accompanist.navigation.animation.rememberAnimatedNavController
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.pondpedia.android.pondpedia.core.app.PondPediaApplication
import com.pondpedia.android.pondpedia.navigation.NavGraph
import com.pondpedia.android.pondpedia.navigation.Screen
import com.pondpedia.android.pondpedia.presentation.ui.theme.PondPediaCustomTheme
import dagger.hilt.android.AndroidEntryPoint
import com.pondpedia.android.pondpedia.presentation.screens.auth.AuthViewModel

//class MainActivity : ComponentActivity() {
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContent {
//            PondPediaTheme {
//                // A surface container using the 'background' color from the theme
//                Surface(
//                    modifier = Modifier.fillMaxSize(),
//                    color = MaterialTheme.colorScheme.background
//                ) {
//                    Greeting("Android")
//                }
//            }
//        }
//    }
//}
//
//@Composable
//fun Greeting(name: String, modifier: Modifier = Modifier) {
//    Text(
//        text = "Hello $name!",
//        modifier = modifier
//    )
//}
//
//@Preview(showBackground = true)
//@Composable
//fun GreetingPreview() {
//    PondPediaTheme {
//        Greeting("Android")
//    }
//}

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val dataStore by lazy { (application as PondPediaApplication).pondPediaDataStore }

    private lateinit var navController: NavHostController
    private val viewModel by viewModels<AuthViewModel>()

    @OptIn(ExperimentalAnimationApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {

            val systemUiController = rememberSystemUiController()
            val darkTheme = true
            val isDynamicColor = true
//            val darkTheme = shouldUseDarkTheme(uiState)
//            val isDynamicColor = shouldUseDynamicColor(uiState)

            DisposableEffect(systemUiController, darkTheme) {
                systemUiController.systemBarsDarkContentEnabled = !darkTheme
                onDispose {}
            }
            PondPediaCustomTheme(
//                darkTheme = darkTheme,
                dynamicColor = isDynamicColor
            ) {
//                PondPediaApp(navController = rememberNavController())
                navController = rememberAnimatedNavController()
                NavGraph(
                    navController = navController
                )
                checkAuthState()
            }
        }
    }
    private fun checkAuthState() {
        if(viewModel.isUserAuthenticated) {
            navigateToProfileScreen()
        }
    }

    private fun navigateToProfileScreen() = navController.navigate(Screen.ProfileScreen.route)
}
/*

@Composable
private fun shouldUseDarkTheme(
    uiState: MainActivityUiState,
): Boolean = when (uiState) {
    Loading -> false
    is Success -> when (uiState.farmer.preferences.darkMode) {
        DarkThemeConfig.FOLLOW_SYSTEM -> isSystemInDarkTheme()
        DarkThemeConfig.LIGHT -> false
        DarkThemeConfig.DARK -> true
    }
}

@Composable
private fun shouldUseDynamicColor(
    uiState: MainActivityUiState,
): Boolean = when (uiState) {
    MainActivityUiState.Loading -> isSystemInDarkTheme()
    is MainActivityUiState.Success -> when (uiState.farmer.preferences.) {
        true -> true
        false -> false
    }
}*/
