package com.pondpedia.compose.pondpedia

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.DisposableEffect
import androidx.navigation.compose.rememberNavController
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.pondpedia.compose.pondpedia.core.app.PondPediaApplication
import com.pondpedia.compose.pondpedia.presentation.PondPediaApp
import com.pondpedia.compose.pondpedia.presentation.ui.theme.PondPediaCustomTheme
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
    private val dataStore by lazy { (application as PondPediaApplication).pondPediaDataStore }

    /*val viewModel: MainActivityViewModel by viewModels()*/

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        /*var uiState: MainActivityUiState by mutableStateOf(MainActivityUiState.Loading)*/

        /*lifecycleScope.launch {
            lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.uiState
                    .onEach {
                        uiState = it
                    }
                    .collect()
            }
        }*/
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
                darkTheme = darkTheme,
                dynamicColor = isDynamicColor
            ) {
                PondPediaApp(navController = rememberNavController())
            }
        }
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
