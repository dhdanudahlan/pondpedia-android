//@file:OptIn(ExperimentalMaterial3Api::class, ExperimentalMaterial3Api::class)
//
package com.pondpedia.compose.pondpedia.presentation.screens.home.components
//
//import android.widget.Toast
//import androidx.compose.material.icons.Icons
//import androidx.compose.material.icons.filled.MoreVert
//import androidx.compose.material.icons.filled.Search
//import androidx.compose.material3.DropdownMenu
//import androidx.compose.material3.DropdownMenuItem
//import androidx.compose.material3.ExperimentalMaterial3Api
//import androidx.compose.material3.Icon
//import androidx.compose.material3.IconButton
//import androidx.compose.material3.Text
//import androidx.compose.material3.TopAppBar
//import androidx.compose.runtime.Composable
//import androidx.compose.runtime.remember
//import androidx.compose.ui.platform.LocalContext
//import com.pondpedia.compose.pondpedia.presentation_copy.screens.home.ponds.components.viewmodel.PondDetailsState
//import com.pondpedia.compose.pondpedia.presentation_copy.screens.home.ponds.components.viewmodel.PondDetailsState
//
//@Composable
//fun TopActionBar(
//    pondsState: PondDetailsState,
//    pondState: PondDetailsState,
//    setDisplayActionMenu: (Boolean) -> Unit,
//    setDisplayActionScreen: (Boolean) -> Unit,
//    onRouteChanged: (String) -> Unit,
//    onPondSaved: (String) -> Unit,
//    onPondDeleted: (String) -> Unit,
//) {
//
//    TopActionBarContent(
//        pondsState = pondsState,
//        pondState = pondState,
//        setDisplayActionMenu = setDisplayActionMenu,
//        setDisplayActionScreen = setDisplayActionScreen,
//        onRouteChanged = onRouteChanged,
//        onPondSaved = onPondSaved,
//        onPondDeleted = onPondDeleted
//    )
//
//
//}
//
//enum class TopActionBarItem(val title_short: String, val title_detail: String) {
//    PONDS("Ponds", "Pond Management"),
//    UPDATES("Updates", "Updates"),
//    MENU("Menu", "PondPedia Menu"),
//    EXPLORE("Explore", "Explore"),
//    MORE("More", "More"),
//    DETAILS("Details", "Pond Details"),
//    CREATE("Create", "Create Ponds"),
//    UPDATE("UPDATE", "Update Ponds")
//}
//
//@Composable
//fun TopActionBarContent(
//    pondsState: PondDetailsState,
//    pondState: PondDetailsState,
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
