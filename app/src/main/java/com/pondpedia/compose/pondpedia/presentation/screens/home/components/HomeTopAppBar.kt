@file:OptIn(ExperimentalMaterial3Api::class, ExperimentalMaterial3Api::class)

package com.pondpedia.compose.pondpedia.presentation.screens.home.components

import android.R
import androidx.annotation.StringRes
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FilterList
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarColors
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeTopAppBar(
    @StringRes titleRes: Int,
    actionSearchIcon: ImageVector,
    actionSearchIconContentDescription: String?,
    actionOptionsIcon: ImageVector,
    actionOptionsIconContentDescription: String?,
    modifier: Modifier = Modifier,
    colors: TopAppBarColors = TopAppBarDefaults.centerAlignedTopAppBarColors(),
    onActionSearchClick: () -> Unit = {},
    onActionOptionsClick: () -> Unit = {},
) {
    CenterAlignedTopAppBar(
        title = { Text(text = stringResource(id = titleRes)) },
        navigationIcon = {
            IconButton(onClick = onActionSearchClick) {
                Icon(
                    imageVector = actionSearchIcon,
                    contentDescription = actionSearchIconContentDescription,
                    tint = MaterialTheme.colorScheme.onSurface,
                )
            }
        },
        actions = {
            IconButton(onClick = onActionOptionsClick) {
                Icon(
                    imageVector = actionOptionsIcon,
                    contentDescription = actionOptionsIconContentDescription,
                    tint = MaterialTheme.colorScheme.onSurface,
                )
            }
        },
        colors = colors,
        modifier = modifier.testTag("pondpediaTopAppBar"),
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Preview("Top App Bar")
@Composable
private fun HomeTopAppBarPreview() {
    com.pondpedia.compose.pondpedia.presentation_copy.screens.home.components.HomeTopAppBar(
        titleRes = R.string.untitled,
        actionSearchIcon = Icons.Default.Search,
        actionSearchIconContentDescription = "Navigation icon",
        actionOptionsIcon = Icons.Default.FilterList,
        actionOptionsIconContentDescription = "Action icon",
    )
}
//
//enum class HomeTopActionBarItem(val title_short: String, val title_detail: String) {
//    PONDS("Ponds", "Pond Management"),
//    UPDATES("Updates", "Updates"),
//    MENU("Menu", "PondPedia Menu"),
//    EXPLORE("Explore", "Explore"),
//    MORE("More", "More"),
//}
//
//@Composable
//fun HomeTopActionBarContent(
//    mainState: MainState,
//    pondsState: PondsState,
//    pondCreateState: PondState,
//    setDisplayActionMenu: (Boolean) -> Unit,
//    setDisplayActionScreen: (Boolean) -> Unit,
//    onRouteChanged: (String) -> Unit,
//    onPondSaved: (String) -> Unit,
//    onPondDeleted: (String) -> Unit,
//) {
//
//    val topActionBarItem = remember(mainState.topActionBarItem) { mainState.topActionBarItem }
//    var oldTopActionBarItem = TopActionBarItem.PONDS
//
//    var mDisplayMenu = remember(mainState.displayActionMenu) { mainState.displayActionMenu }
//    var mDisplayScreen = remember(mainState.displayActionScreen) { mainState.displayActionScreen }
//    val mContext = LocalContext.current
//
////    if (oldTopActionBarItem != topActionBarItem) {
////        oldTopActionBarItem = topActionBarItem
////        setDisplayActionMenu(false)
////        setDisplayActionScreen(false)
////    }
//
//    TopAppBar(
//        title = {
//            Text(text = mainState.topActionBarItem.title_detail)
//        },
//        actions = {
//            when (mainState.topActionBarItem) {
//                TopActionBarItem.CREATE -> {
//                }
//                TopActionBarItem.UPDATE -> {
//                }
//                TopActionBarItem.DETAILS -> {
//                }
//                else -> {
//                    IconButton(onClick = {
//                        Toast.makeText(
//                            mContext,
//                            "Search Button Clicked",
//                            Toast.LENGTH_SHORT
//                        ).show()
//                    }) {
//                        Icon(Icons.Default.Search, "Search Button")
//                    }
//                }
//            }
//
//            IconButton(onClick = { setDisplayActionMenu(!mDisplayMenu) }) {
//                Icon(Icons.Default.MoreVert, "")
//            }
//            when (topActionBarItem) {
//                TopActionBarItem.PONDS -> {
//                    DropdownMenu(
//                        expanded = mDisplayMenu,
//                        onDismissRequest = { setDisplayActionMenu(false) }
//                        ) {
//                            DropdownMenuItem(
//                                onClick = {
//                                    Toast.makeText(
//                                        mContext,
//                                        "Category Filter",
//                                        Toast.LENGTH_SHORT
//                                    ).show()
//                                },
//                                text = {
//                                    Text(text = "Filter by Category PONDS")
//                                }
//                            )
//
//                            DropdownMenuItem(onClick = {
//                                Toast.makeText(mContext, "Priority Filter", Toast.LENGTH_SHORT)
//                                    .show()
//                            },
//                                text = {
//                                    Text(text = "Filter by Priority PONDS")
//                                }
//                            )
//                        }
//                }
//                TopActionBarItem.UPDATES -> {
//                    DropdownMenu(
//                        expanded = mDisplayMenu,
//                        onDismissRequest = { setDisplayActionMenu(false) }
//                    ) {
//                        DropdownMenuItem(
//                            onClick = {
//                                Toast.makeText(
//                                    mContext,
//                                    "Category Filter",
//                                    Toast.LENGTH_SHORT
//                                ).show()
//                            },
//                            text = {
//                                Text(text = "Filter by Category UPDATES")
//                            }
//                        )
//
//                        DropdownMenuItem(onClick = {
//                            Toast.makeText(mContext, "Priority Filter", Toast.LENGTH_SHORT).show()
//                        },
//                            text = {
//                                Text(text = "Filter by Priority UPDATES")
//                            }
//                        )
//                    }
//                }
//                TopActionBarItem.MENU -> {
//                    DropdownMenu(
//                        expanded = mDisplayMenu,
//                        onDismissRequest = { setDisplayActionMenu(false) }
//                    ) {
//                        DropdownMenuItem(
//                            onClick = {
//                                Toast.makeText(
//                                    mContext,
//                                    "Category Filter",
//                                    Toast.LENGTH_SHORT
//                                ).show()
//                            },
//                            text = {
//                                Text(text = "Filter by Category MENU")
//                            }
//                        )
//
//                        DropdownMenuItem(onClick = {
//                            Toast.makeText(mContext, "Priority Filter", Toast.LENGTH_SHORT).show()
//                        },
//                            text = {
//                                Text(text = "Filter by Priority MENU")
//                            }
//                        )
//                    }
//                }
//                TopActionBarItem.EXPLORE -> {
//                    DropdownMenu(
//                        expanded = mDisplayMenu,
//                        onDismissRequest = { setDisplayActionMenu(false) }
//                    ) {
//                        DropdownMenuItem(
//                            onClick = {
//                                Toast.makeText(
//                                    mContext,
//                                    "Category Filter",
//                                    Toast.LENGTH_SHORT
//                                ).show()
//                            },
//                            text = {
//                                Text(text = "Filter by Category EXPLORE")
//                            }
//                        )
//
//                        DropdownMenuItem(onClick = {
//                            Toast.makeText(mContext, "Priority Filter", Toast.LENGTH_SHORT).show()
//                        },
//                            text = {
//                                Text(text = "Filter by Priority EXPLORE")
//                            }
//                        )
//                    }
//                }
//                TopActionBarItem.MORE -> {
//                    DropdownMenu(
//                        expanded = mDisplayMenu,
//                        onDismissRequest = { setDisplayActionMenu(false) }
//                    ) {
//                        DropdownMenuItem(
//                            onClick = {
//                                Toast.makeText(
//                                    mContext,
//                                    "Category Filter",
//                                    Toast.LENGTH_SHORT
//                                ).show()
//                            },
//                            text = {
//                                Text(text = "Filter by Category MORE")
//                            }
//                        )
//
//                        DropdownMenuItem(onClick = {
//                            Toast.makeText(mContext, "Priority Filter", Toast.LENGTH_SHORT).show()
//                        },
//                            text = {
//                                Text(text = "Filter by Priority MORE")
//                            }
//                        )
//                    }
//                }
//                TopActionBarItem.DETAILS -> {
//
//                    DropdownMenu(
//                        expanded = mDisplayMenu,
//                        onDismissRequest = { setDisplayActionMenu(false) }
//                    ) {
//                        DropdownMenuItem(
//                            onClick = {
//                                Toast.makeText(
//                                    mContext,
//                                    "EDIT CATEGORIES",
//                                    Toast.LENGTH_SHORT
//                                ).show()
//                            },
//                            text = {
//                                Text(text = "Edit Categories")
//                            }
//                        )
//
//                        DropdownMenuItem(
//                            onClick = {
//                                Toast.makeText(
//                                    mContext,
//                                    "UPDATE POND",
//                                    Toast.LENGTH_SHORT
//                                ).show()
//                            },
//                            text = {
//                                Text(text = "Update Pond")
//                            }
//                        )
//
//                        DropdownMenuItem(
//                            onClick = {
//                                Toast.makeText(
//                                    mContext,
//                                    "DELETE POND",
//                                    Toast.LENGTH_SHORT
//                                ).show()
//                                onPondDeleted(pondsState.pondId)
//                                onRouteChanged("home")
//                            },
//                            text = {
//                                Text(text = "Delete Pond")
//                            }
//                        )
//                    }
//                }
//                TopActionBarItem.CREATE -> {
//                    DropdownMenu(
//                        expanded = mDisplayMenu,
//                        onDismissRequest = { setDisplayActionMenu(false) }
//                    ) {
//                        DropdownMenuItem(
//                            onClick = {
//                                Toast.makeText(
//                                    mContext,
//                                    "CREATE POND",
//                                    Toast.LENGTH_SHORT
//                                ).show()
//                                onPondSaved("")
//                                onRouteChanged("home")
//                            },
//                            text = {
//                                Text(text = "Create Pond")
//                            }
//                        )
//                    }
//                }
//                TopActionBarItem.UPDATE -> {
//                    DropdownMenu(
//                        expanded = mDisplayMenu,
//                        onDismissRequest = { setDisplayActionMenu(false) }
//                    ) {
//                        DropdownMenuItem(
//                            onClick = {
//                                Toast.makeText(
//                                    mContext,
//                                    "SAVE UPDATE",
//                                    Toast.LENGTH_SHORT
//                                ).show()
//                            },
//                            text = {
//                                Text(text = "Save Updates")
//                            }
//                        )
//
//                        DropdownMenuItem(onClick = {
//                            Toast.makeText(mContext, "CANCEL UPDATE", Toast.LENGTH_SHORT).show()
//                        },
//                            text = {
//                                Text(text = "Cancel Updates")
//                            }
//                        )
//                    }
//                }
//            }
//        }
//    )
//}
