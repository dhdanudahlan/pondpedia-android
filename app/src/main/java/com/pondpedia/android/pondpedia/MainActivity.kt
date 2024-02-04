package com.pondpedia.android.pondpedia

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.DisposableEffect
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import com.google.accompanist.navigation.animation.rememberAnimatedNavController
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.pondpedia.android.pondpedia.presentation.PondPediaApp
import com.pondpedia.android.pondpedia.presentation.theme.PondPediaCustomTheme
import dagger.hilt.android.AndroidEntryPoint

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
    //    private val dataStore by lazy { (application as PondPediaApplication).pondPediaDataStore }

    private lateinit var navController: NavHostController

    @OptIn(ExperimentalAnimationApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {

            val systemUiController = rememberSystemUiController()
            val darkTheme = false
            val isDynamicColor = true
//            val darkTheme = shouldUseDarkTheme(uiState)
//            val isDynamicColor = shouldUseDynamicColor(uiState)

            DisposableEffect(systemUiController, darkTheme) {
                systemUiController.systemBarsDarkContentEnabled = !darkTheme
                onDispose {}
            }
            PondPediaCustomTheme(
                darkTheme = darkTheme,
                dynamicColor = isDynamicColor
            ) {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    navController = rememberAnimatedNavController()
                    PondPediaApp(
                        navController = navController,
                    )

//                Button(
//                    onClick = {
//                        if (checkForInternet(this)) {
//                            Toast.makeText(this, "Connected", Toast.LENGTH_SHORT).show()
//                        } else {
//                            Toast.makeText(this, "Disconnected", Toast.LENGTH_SHORT).show()
//                        }
//                    }
//                ) {
//                    Text(text = "Check Internet Access")
//                }}
                }
            }
        }
    }


//    @Composable
//    private fun NavigateToVerifyEmailScreen() = navController.navigate(VerifyEmailScreen.route) {
//        popUpTo(navController.graph.id) {
//            inclusive = true
//        }
//    }
}

private fun checkForInternet(context: Context): Boolean {

    // register activity with the connectivity manager service
    val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

    // if the android version is equal to M
    // or greater we need to use the
    // NetworkCapabilities to check what type of
    // network has the internet connection

    // Returns a Network object corresponding to
    // the currently active default data network.
    val network = connectivityManager.activeNetwork ?: return false

    // Representation of the capabilities of an active network.
    val activeNetwork = connectivityManager.getNetworkCapabilities(network) ?: return false

    return when {
        // Indicates this network uses a Wi-Fi transport,
        // or WiFi has network connectivity
        activeNetwork.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true

        // Indicates this network uses a Cellular transport. or
        // Cellular has network connectivity
        activeNetwork.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true

        // else return false
        else -> false
    }
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
