package com.pondpedia.android.pondpedia.presentation.components.navigation.graphs

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.pondpedia.android.pondpedia.presentation.Graph
import com.pondpedia.android.pondpedia.presentation.PondPediaAppState
import com.pondpedia.android.pondpedia.presentation.ui.home.components.Screens
import com.pondpedia.android.pondpedia.presentation.ui.home.menu.components.MenuScreens
import com.pondpedia.android.pondpedia.presentation.ui.home.menu.screens.MenuScreen
import com.pondpedia.android.pondpedia.presentation.ui.home.menu.screens.ai_chat.AiChatScreen

fun NavGraphBuilder.menuNavGraph(
    homeState: PondPediaAppState,
) {

    val navController = homeState.navController
    navigation(
        route = Graph.HOME_MENU,
        startDestination = Screens.Menu.route
    ) {
        composable(route = Screens.Menu.route) {
            MenuScreen(
                onRouteChanged = { route ->
                    navController.navigate(route)
                },
            )
        }

        composable(route = MenuScreens.AiChat.route) {
            AiChatScreen()
//            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
//                Text(text = "Under Construction...")
//            }
        }
    }
}