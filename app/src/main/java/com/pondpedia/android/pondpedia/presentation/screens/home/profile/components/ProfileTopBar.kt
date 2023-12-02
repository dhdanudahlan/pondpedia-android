package com.pondpedia.android.pondpedia.presentation.screens.home.profile.components

import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.MoreVert
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.*
import com.pondpedia.android.pondpedia.core.util.Constants.PROFILE_SCREEN
import com.pondpedia.android.pondpedia.core.util.Constants.REVOKE_ACCESS
import com.pondpedia.android.pondpedia.core.util.Constants.SIGN_OUT

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileTopBar(
    signOut: () -> Unit,
    revokeAccess: () -> Unit
) {
    var openMenu by remember { mutableStateOf(false) }

    TopAppBar(
        title = {
            Text(
                text = PROFILE_SCREEN
            )
        },
        actions = {
            IconButton(
                onClick = {
                    openMenu = !openMenu
                }
            ) {
                Icon(
                    imageVector = Icons.Outlined.MoreVert,
                    contentDescription = null,
                )
            }
            DropdownMenu(
                expanded = openMenu,
                onDismissRequest = {
                    openMenu = !openMenu
                }
            ) {
                DropdownMenuItem(
                    onClick = {
                        signOut()
                        openMenu = !openMenu
                    },
                    text = {
                        Text(
                            text = SIGN_OUT
                        )
                    }
                )
                DropdownMenuItem(
                    onClick = {
                        revokeAccess()
                        openMenu = !openMenu
                    },
                    text = {
                        Text(
                            text = REVOKE_ACCESS
                        )
                    }
                )
            }
        }
    )
}