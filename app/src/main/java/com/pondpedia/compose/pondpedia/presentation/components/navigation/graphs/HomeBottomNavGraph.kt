package com.pondpedia.compose.pondpedia.presentation.components.navigation.graphs

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.DialogProperties
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.pondpedia.compose.pondpedia.presentation.Graph
import com.pondpedia.compose.pondpedia.presentation.PondPediaAppState
import com.pondpedia.compose.pondpedia.presentation.screens.home.components.Screens
import com.pondpedia.compose.pondpedia.presentation.screens.home.ponds.components.viewmodel.PondsEvent
import com.pondpedia.compose.pondpedia.presentation.screens.home.ponds.components.viewmodel.PondsState
import com.pondpedia.compose.pondpedia.presentation.screens.home.ponds.screens.PondsScreen

@Composable
fun HomeBottomNavGraph(
    homeState: PondPediaAppState,
    pondsState: PondsState,
    onEvent: (PondsEvent) -> Unit,
    startDestination: String = Screens.Ponds.route,
) {
    val navController = homeState.navController

    NavHost(
        navController = navController,
        route = Graph.HOME,
        startDestination = startDestination
    ) {
        pondsNavGraph(
            homeState = homeState,
            pondsState = pondsState,
            onTabIndexSelected = /*pondsViewModel::setSelectedTab*/ {},
            onPondListDisplayed = /*pondsViewModel::getPondList*/{},
            onEvent = onEvent
        )
        updatesNavGraph(homeState = homeState)
        menuNavGraph(homeState = homeState)
        exploreNavGraph(homeState = homeState)
        moreNavGraph(homeState = homeState)

        /*pondCreateNavGraph(
            homeState = homeState,
            pondsState = pondsState,
            onEvent = {  }
        )*/

        pondNavGraph(
            homeState = homeState,
            pondsState = pondsState,
            onPondSaved = /*pondViewModel::addPondToDatabase*/{},
            onPondDeleted = /*pondsViewModel::deletePond*/{},
            setDisplayActionScreen = {},
            setDisplayActionMenu = {}
        )
    }
}

fun NavGraphBuilder.pondsNavGraph(
    homeState: PondPediaAppState,
    pondsState: PondsState,
    onTabIndexSelected: (Int) -> Unit,
    onPondListDisplayed : () -> Unit,
    onEvent: (PondsEvent) -> Unit,
) {
    val navController = homeState.navController

    composable( route = Screens.Ponds.route ) {
        PondsScreen(
            pondsState = pondsState,
            onTabIndexSelected = onTabIndexSelected,
            onRouteChanged = { route ->
                navController.navigate(route)
            },
            onPondListDisplayed = onPondListDisplayed,
            onEvent = onEvent,
        )
    }
}
fun NavGraphBuilder.updatesNavGraph(
    homeState: PondPediaAppState,
) {
    composable(route = Screens.Updates.route) {
        Text(text = Screens.Updates.route)
    }
}
fun NavGraphBuilder.menuNavGraph(
    homeState: PondPediaAppState,
) {
    composable(route = Screens.Menu.route) {
        Text(text = Screens.Menu.route)
    }
}
fun NavGraphBuilder.exploreNavGraph(
    homeState: PondPediaAppState,
) {
    composable(route = Screens.Explore.route) {
        Text(text = Screens.Explore.route)
    }
}
fun NavGraphBuilder.moreNavGraph(
    homeState: PondPediaAppState,
) {
    composable(route = Screens.More.route) {
        Text(text = Screens.More.route)
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddPondDialog(
    pondsState: PondsState,
    onEvent: (PondsEvent) -> Unit,
    modifier: Modifier = Modifier
) {
    AlertDialog(
        onDismissRequest = {
                           onEvent(PondsEvent.HideDialog)
                           },
        properties = DialogProperties(dismissOnClickOutside = false),
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(8.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            TextField(
                value = pondsState.name,
                onValueChange = {
                    onEvent(PondsEvent.SetName(it))
                },
                placeholder = {
                    Text(text = "Nama kolam")
                }
            )
            Spacer(modifier = Modifier.height(8.dp))
            TextField(
                value = pondsState.area,
                onValueChange = {
                    onEvent(PondsEvent.SetArea(it))
                },
                placeholder = {
                    Text(text = "Luas kolam")
                }
            )
            Spacer(modifier = Modifier.height(8.dp))
            TextField(
                value = pondsState.depth,
                onValueChange = {
                    onEvent(PondsEvent.SetDepth(it))
                },
                placeholder = {
                    Text(text = "Kedalaman kolam")
                }
            )
            Spacer(modifier = Modifier.height(8.dp))
            TextField(
                value = pondsState.pondType,
                onValueChange = {
                    onEvent(PondsEvent.SetPondType(it))
                },
                placeholder = {
                    Text(text = "Tipe kolam")
                }
            )
            Spacer(modifier = Modifier.height(8.dp))
            TextField(
                value = pondsState.waterType,
                onValueChange = {
                    onEvent(PondsEvent.SetWaterType(it))
                },
                placeholder = {
                    Text(text = "Tipe air")
                }
            )
            Spacer(modifier = Modifier.height(8.dp))
            TextField(
                value = pondsState.location,
                onValueChange = {
                    onEvent(PondsEvent.SetLocation(it))
                },
                placeholder = {
                    Text(text = "Lokasi")
                }
            )
            Spacer(modifier = Modifier.height(8.dp))
            TextField(
                value = pondsState.description,
                onValueChange = {
                    onEvent(PondsEvent.SetDescription(it))
                },
                placeholder = {
                    Text(text = "Deskripsi tambahan")
                }
            )
            Spacer(modifier = Modifier.height(8.dp))

            Box(modifier = Modifier.fillMaxWidth()) {
                Button(onClick = {
                    onEvent(PondsEvent.AddPond)
                }) {
                    Text(text = "Create")
                }
            }
        }
    }
}



