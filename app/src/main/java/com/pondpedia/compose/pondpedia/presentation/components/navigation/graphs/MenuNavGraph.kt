package com.pondpedia.compose.pondpedia.presentation.components.navigation.graphs

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.pondpedia.compose.pondpedia.presentation.Graph
import com.pondpedia.compose.pondpedia.presentation.PondPediaAppState
import com.pondpedia.compose.pondpedia.presentation.screens.home.components.Screens
import com.pondpedia.compose.pondpedia.presentation.screens.home.menu.components.MenuScreens
import com.pondpedia.compose.pondpedia.presentation.screens.home.menu.screens.MenuScreen
import com.pondpedia.compose.pondpedia.presentation.screens.home.menu.screens.ai_chat.AiChatScreen

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
            AiChatScreen(
               //
            )
        }
    }
}