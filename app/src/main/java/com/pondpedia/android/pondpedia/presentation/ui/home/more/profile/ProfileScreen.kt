package com.pondpedia.android.pondpedia.presentation.ui.home.more.profile

import android.widget.Toast
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.SnackbarResult
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import com.pondpedia.android.pondpedia.components.ProfileTopBar
import com.pondpedia.android.pondpedia.core.util.Constants.PROFILE_SCREEN
import com.pondpedia.android.pondpedia.core.util.Constants.REVOKE_ACCESS_MESSAGE
import com.pondpedia.android.pondpedia.core.util.Constants.SIGN_OUT
import com.pondpedia.android.pondpedia.presentation.ui.home.more.profile.components.ProfileContent
import com.pondpedia.android.pondpedia.presentation.ui.home.more.profile.components.ProfileViewModel
import com.pondpedia.android.pondpedia.presentation.ui.home.more.profile.components.RevokeAccess
import kotlinx.coroutines.launch

@Composable
fun ProfileScreen(
    viewModel: ProfileViewModel = hiltViewModel(),
    navigateToAuthScreen: () -> Unit = {},
) {

    val context = LocalContext.current
    val snackbarHostState = remember { SnackbarHostState() }
    val coroutineScope = rememberCoroutineScope()
    val currentUser = viewModel.currentUser.collectAsState().value

    Scaffold(
        topBar = {
            ProfileTopBar(
                title = PROFILE_SCREEN,
                signOut = {
                    viewModel.signOut(
                        onSuccess = { navigateToAuthScreen() },
                        onError = { Toast.makeText(context, it, Toast.LENGTH_SHORT).show() }
                    )
                },
                revokeAccess = {
                    viewModel.revokeAccess()
                    navigateToAuthScreen()
                },
                reloadUser = {
                    viewModel.reloadUser()
                }
            )
        },
        content = { padding ->
            ProfileContent(
                padding = padding,
                user = currentUser,
                sendEmailVerification = {
                    viewModel.sendEmailVerification()
                }
            )
        },
        snackbarHost = { SnackbarHost(snackbarHostState) },
    )

    fun showSnackBar() = coroutineScope.launch {
        val result = snackbarHostState.showSnackbar(
            message = REVOKE_ACCESS_MESSAGE,
            actionLabel = SIGN_OUT
        )
        if (result == SnackbarResult.ActionPerformed) {
            viewModel.signOut()
        }
    }

    RevokeAccess(
        snackbarHostState = snackbarHostState,
        coroutineScope = coroutineScope,
        signOut = { accessRevoked ->
            viewModel.signOut(
                onSuccess = { if (accessRevoked) {
                    navigateToAuthScreen()
                }},
                onError = { Toast.makeText(context, it, Toast.LENGTH_SHORT).show() }
            )
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
