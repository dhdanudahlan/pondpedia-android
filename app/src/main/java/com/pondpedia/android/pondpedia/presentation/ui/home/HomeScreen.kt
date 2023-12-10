
package com.pondpedia.android.pondpedia.presentation.ui.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FilterList
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import com.pondpedia.android.pondpedia.presentation.PondPediaAppState
import com.pondpedia.android.pondpedia.presentation.components.navigation.graphs.HomeBottomNavGraph
import com.pondpedia.android.pondpedia.presentation.rememberPondPediaAppState
import com.pondpedia.android.pondpedia.presentation.ui.home.components.HomeBottomNavBar
import com.pondpedia.android.pondpedia.presentation.ui.home.components.HomeTopAppBar
import com.pondpedia.android.pondpedia.presentation.ui.home.components.Screens
import com.pondpedia.android.pondpedia.presentation.ui.home.ponds.components.FloatingActionButtonCompose
import com.pondpedia.android.pondpedia.presentation.ui.home.ponds.components.viewmodel.PondsViewModel


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    homeState: PondPediaAppState = rememberPondPediaAppState(),
) {
    val snackbarHostState = remember { SnackbarHostState() }
    val homeDestination = homeState.currentHomeScreenDestination

    var showSettingsDialog by rememberSaveable {
        mutableStateOf(false)
    }

    var showSearchBar by rememberSaveable {
        mutableStateOf(false)
    }
    var showTopAppBar by rememberSaveable {
        mutableStateOf(false)
    }
    val unreadDestinations = emptySet<Screens>()


    val viewModel = hiltViewModel<PondsViewModel>()
    val pondsState by viewModel.state.collectAsState()

    val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior()

    Scaffold(
        modifier = Modifier.fillMaxSize()
            .nestedScroll(scrollBehavior.nestedScrollConnection),
        snackbarHost = { SnackbarHost(snackbarHostState) },
        topBar = {
            if (homeState.shouldShowBottomBar) {
                HomeTopAppBar(
                    titleRes = homeDestination?.titleTextId ?: Screens.More.titleTextId,
                    actionSearchIcon = Icons.Default.Search,
                    actionSearchIconContentDescription = stringResource(
                        id = homeDestination?.titleTextId ?: Screens.More.titleTextId,
                    ),
                    actionOptionsIcon = Icons.Default.FilterList,
                    actionOptionsIconContentDescription = stringResource(
                        id = homeDestination?.titleTextId ?: Screens.More.titleTextId,
                    ),
                    onActionSearchClick =  { showSearchBar = true },
                    onActionOptionsClick = { showSettingsDialog = true },
                    scrollBehavior = scrollBehavior
                )
            }
        },
        bottomBar = {
            if (homeState.shouldShowBottomBar) {
                HomeBottomNavBar(
                    pondsState = pondsState,
                    onEvent = viewModel::onEvent,
                    destinations = homeState.homeScreenDestinations,
                    destinationsWithUnreadResources = unreadDestinations,
                    onNavigateToDestination = homeState::navigateToHomeScreenDestination,
                    currentDestination = homeState.currentDestination
                )
            }
        },
        floatingActionButton = {
            if (homeState.shouldShowFloatingActionButton) {
                FloatingActionButtonCompose(onEvent = viewModel::onEvent )
            }
        },
    ) { innerPadding ->
        Row(
            Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            Column(
                Modifier.fillMaxSize()
            ) {

                HomeBottomNavGraph(
                    homeState = homeState,
                    pondsState = pondsState,
                    onEvent = viewModel::onEvent,
                )
            }
        }
    }
}


