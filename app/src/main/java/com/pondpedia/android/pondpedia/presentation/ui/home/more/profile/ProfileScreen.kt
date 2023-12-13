package com.pondpedia.android.pondpedia.presentation.ui.home.more.profile

import android.util.Log
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.hilt.navigation.compose.hiltViewModel
import com.pondpedia.android.pondpedia.components.ProfileTopBar
import com.pondpedia.android.pondpedia.core.util.Constants.DISPLAY_NAME
import com.pondpedia.android.pondpedia.core.util.Constants.EMAIL
import com.pondpedia.android.pondpedia.core.util.Constants.PROFILE_SCREEN
import com.pondpedia.android.pondpedia.presentation.ui.home.more.profile.components.ProfileContent
import com.pondpedia.android.pondpedia.presentation.ui.home.more.profile.components.RevokeAccess

@Composable
fun ProfileScreen(
    viewModel: ProfileViewModel = hiltViewModel(),
    navigateToAuthScreen: () -> Unit = {}
) {
    val snackbarHostState = remember { SnackbarHostState() }
    val coroutineScope = rememberCoroutineScope()
    val currentUser = viewModel.currentUser


    Scaffold(
        topBar = {
            ProfileTopBar(
                title = PROFILE_SCREEN,
                signOut = {
                    viewModel.signOut()
                    navigateToAuthScreen()
                },
                revokeAccess = {
                    viewModel.revokeAccess()
                    navigateToAuthScreen()
                }
            )
        },
        content = { padding ->
            ProfileContent(
                padding = padding,
                user = currentUser
            )
        },
        snackbarHost = { SnackbarHost(snackbarHostState) },
    )

    RevokeAccess(
        snackbarHostState = snackbarHostState,
        coroutineScope = coroutineScope,
        signOut = {
            viewModel.signOut()
            navigateToAuthScreen()
        }
    )
}


/*
@Preview
@Composable
fun ProfileScreenPreview(
) {
    ProfileScreen(
        navigateToAuthScreen = {}
    )
}*/
