package com.pondpedia.android.pondpedia.presentation.ui.home.components

import androidx.compose.foundation.layout.RowScope
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Analytics
import androidx.compose.material.icons.filled.Explore
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.Update
import androidx.compose.material.icons.outlined.Add
import androidx.compose.material.icons.outlined.Analytics
import androidx.compose.material.icons.outlined.Explore
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Menu
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material.icons.outlined.RotateRight
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.material3.Badge
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.pondpedia.android.pondpedia.R
import com.pondpedia.android.pondpedia.presentation.ui.home.ponds.components.viewmodel.PondsEvent
import com.pondpedia.android.pondpedia.presentation.ui.home.ponds.components.viewmodel.PondsState

sealed class Screens(
    val route: String,
    val labelTextId: Int,
    val titleTextId: Int,
    val selectedIcon: ImageVector,
    val unselectedIcon: ImageVector,
    val hasUnread: Boolean,
    val badgeCount: Int?,
) {
    data object Ponds : Screens(
        route = "HOME_PONDS",
        labelTextId = R.string.ponds_label,
        titleTextId = R.string.ponds_title,
        selectedIcon = Icons.Filled.Analytics,
        unselectedIcon = Icons.Outlined.Analytics,
        hasUnread = false,
        badgeCount = null,
    )
    data object Updates : Screens(
        route = "HOME_UPDATES",
        labelTextId = R.string.updates_label,
        titleTextId = R.string.updates_title,
        selectedIcon = Icons.Filled.Update,
        unselectedIcon = Icons.Outlined.RotateRight,
        hasUnread = false,
        badgeCount = null,
    )
    data object Menu : Screens(
        route = "HOME_MENU",
        labelTextId = R.string.menu_label,
        titleTextId = R.string.menu_title,
        selectedIcon = Icons.Filled.Home,
        unselectedIcon = Icons.Outlined.Home,
        hasUnread = false,
        badgeCount = null,
    )
    data object Explore : Screens(
        route = "HOME_EXPLORE",
        labelTextId = R.string.explore_label,
        titleTextId = R.string.explore_title,
        selectedIcon = Icons.Filled.Explore,
        unselectedIcon = Icons.Outlined.Explore,
        hasUnread = false,
        badgeCount = null,
    )
    data object More : Screens(
        route = "HOME_MORE",
        labelTextId = R.string.more_label,
        titleTextId = R.string.more_title,
        selectedIcon = Icons.Filled.Menu,
        unselectedIcon = Icons.Outlined.Menu,
        hasUnread = false,
        badgeCount = null,
    )

    data object Add : Screens(
        route = "POND_ADD",
        labelTextId = R.string.pond_add_title,
        titleTextId = R.string.pond_add_title,
        selectedIcon = Icons.Filled.Add,
        unselectedIcon = Icons.Outlined.Add,
        hasUnread = false,
        badgeCount = null,
    )


    data object Details: Screens(
        route = "POND_DETAILS",
        labelTextId = R.string.pond_details_title,
        titleTextId = R.string.pond_details_title,
        selectedIcon = Icons.Filled.Add,
        unselectedIcon = Icons.Outlined.Add,
        hasUnread = false,
        badgeCount = null,
    )
    data object Update: Screens(
        route = "POND_UPDATE",
        labelTextId = R.string.pond_update_title,
        titleTextId = R.string.pond_update_title,
        selectedIcon = Icons.Filled.Add,
        unselectedIcon = Icons.Outlined.Add,
        hasUnread = false,
        badgeCount = null,
    )
    data object Analytics: Screens(
        route = "POND_ANALYTICS",
        labelTextId = R.string.pond_analytics_title,
        titleTextId = R.string.pond_analytics_title,
        selectedIcon = Icons.Filled.Add,
        unselectedIcon = Icons.Outlined.Add,
        hasUnread = false,
        badgeCount = null,
    )
    data object Profile: Screens(
        route = "MORE_PROFILE",
        labelTextId = R.string.more_profile_label,
        titleTextId = R.string.more_profile_title,
        selectedIcon = Icons.Filled.Person,
        unselectedIcon = Icons.Outlined.Person,
        hasUnread = false,
        badgeCount = null,
    )
    data object Settings: Screens(
        route = "MORE_SETTINGS",
        labelTextId = R.string.more_settings_label,
        titleTextId = R.string.more_settings_title,
        selectedIcon = Icons.Filled.Settings,
        unselectedIcon = Icons.Outlined.Settings,
        hasUnread = false,
        badgeCount = null,
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeBottomNavBar(
    pondsState: PondsState,
    onEvent: (PondsEvent) -> Unit,
    destinations: List<Screens>,
    destinationsWithUnreadResources: Set<Screens>,
    onNavigateToDestination: (Screens) -> Unit,
    currentDestination: NavDestination?,
    modifier: Modifier = Modifier,
) {
    HomeNavigationBar(
        modifier = modifier
    ) {
        destinations.forEach { destination ->
            val hasUnread = destinationsWithUnreadResources
            val selected = currentDestination.isTopLevelDestinationInHierarchy(destination)

            NavigationBarItem(
                icon = {
                    BadgedBox(
                        badge = {
                            if (destination.badgeCount != null) {
                                Text(text = destination.badgeCount.toString())
                            } else if (destination.hasUnread) {
                                Badge()
                            }
                        }
                    ) {
                        Icon(
                            imageVector = if (
                                selected
                            ) {
                                destination.selectedIcon
                            } else destination.unselectedIcon,
                            contentDescription = stringResource(destination.titleTextId)
                        )
                    }
                },
                label = {
                    Text(text = stringResource(destination.labelTextId))
                },
                selected = selected,
                onClick = {
                    onNavigateToDestination(destination)
                },
                colors = NavigationBarItemDefaults.colors(
                    selectedIconColor = HomeNavigationDefaults.navigationSelectedItemColor(),
                    unselectedIconColor = HomeNavigationDefaults.navigationContentColor(),
                    selectedTextColor = HomeNavigationDefaults.navigationSelectedItemColor(),
                    unselectedTextColor = HomeNavigationDefaults.navigationContentColor(),
                    indicatorColor = HomeNavigationDefaults.navigationIndicatorColor(),
                ),
            )
        }
    }
}
@Composable
fun HomeNavigationBar(
    modifier: Modifier = Modifier,
    content: @Composable RowScope.() -> Unit,
) {
    NavigationBar(
        modifier = modifier,
        contentColor = HomeNavigationDefaults.navigationContentColor(),
        tonalElevation = 5.dp,
        content = content,
    )
}

object HomeNavigationDefaults {
    @Composable
    fun navigationContentColor() = MaterialTheme.colorScheme.onSurfaceVariant

    @Composable
    fun navigationSelectedItemColor() = MaterialTheme.colorScheme.onPrimaryContainer

    @Composable
    fun navigationIndicatorColor() = MaterialTheme.colorScheme.primaryContainer
}

private fun NavDestination?.isTopLevelDestinationInHierarchy(destination: Screens) =
    this?.hierarchy?.any {
        it.route?.contains(destination.route, true) ?: false
    } ?: false
