@file:OptIn(ExperimentalMaterial3Api::class)

package com.pondpedia.android.pondpedia.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.RotateRight
import androidx.compose.material.icons.outlined.MoreVert
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.pondpedia.android.pondpedia.R

@Composable
fun ProfileTopBar(
    title: String,
    signOut: () -> Unit,
    revokeAccess: () -> Unit,
    reloadUser: () -> Unit
) {
    var openMenu by remember { mutableStateOf(false) }

    CenterAlignedTopAppBar (
        title = {
            Text(
                text = title
            )
//            Row(
//                modifier = Modifier.fillMaxWidth(),
//                verticalAlignment = Alignment.CenterVertically
//            ) {
//                Text(
//                    text = title
//                )
//                Row(
//                    modifier = Modifier.fillMaxWidth(),
//                    horizontalArrangement = Arrangement.End
//                ) {
//                    IconButton(
//                        onClick = {
//                            openMenu = !openMenu
//                        }
//                    ) {
//                        Icon(
//                            imageVector = Icons.Outlined.MoreVert,
//                            contentDescription = null,
//                        )
//                    }
//                }
//            }
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
                    text = {
                        Text(
                            text = stringResource(R.string.reload_item)
                        )
                    },
                    onClick = {
                        reloadUser()
                    }
                )
                DropdownMenuItem(
                    text = {
                        Text(
                            text = stringResource(R.string.sign_out_item)
                        )
                    },
                    onClick = {
                        signOut()
                        openMenu = !openMenu
                    }
                )
                DropdownMenuItem(
                    text = {
                        Text(
                            text = stringResource(R.string.revoke_access_item)
                        )
                    },
                    onClick = {
                        revokeAccess()
                        openMenu = !openMenu
                    }
                )
            }
        }
    )
}