package com.pondpedia.android.pondpedia.presentation.ui.auth.verify_email

import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import com.pondpedia.android.pondpedia.components.ProfileTopBar
import com.pondpedia.android.pondpedia.core.util.Constants.EMAIL_NOT_VERIFIED_MESSAGE
import com.pondpedia.android.pondpedia.core.util.Constants.VERIFY_EMAIL_SCREEN
import com.pondpedia.android.pondpedia.core.util.Utils.Companion.showMessage
import com.pondpedia.android.pondpedia.presentation.ui.auth.verify_email.components.ReloadUser
import com.pondpedia.android.pondpedia.presentation.ui.auth.verify_email.components.VerifyEmailContent
import com.pondpedia.android.pondpedia.presentation.ui.home.more.profile.ProfileViewModel
import com.pondpedia.android.pondpedia.presentation.ui.home.more.profile.components.RevokeAccess

@Composable
fun VerifyEmailScreen(
    viewModel: ProfileViewModel = hiltViewModel(),
    navigateToHomeScreen: () -> Unit
) {
    val snackbarHostState = remember { SnackbarHostState() }
    val coroutineScope = rememberCoroutineScope()
    val context = LocalContext.current

    Scaffold(
        topBar = {
            ProfileTopBar(
                title = VERIFY_EMAIL_SCREEN,
                signOut = {
                    viewModel.signOut()
                },
                revokeAccess = {
                    viewModel.revokeAccess()
                }
            )
        },
        content = { padding ->
            VerifyEmailContent(
                padding = padding,
                reloadUser = {
                    viewModel.reloadUser()
                }
            )
        },
        snackbarHost = { SnackbarHost(snackbarHostState) },
    )

    ReloadUser(
        navigateToProfileScreen = {
            if (viewModel.isEmailVerified) {
                navigateToHomeScreen()
            } else {
                showMessage(context, EMAIL_NOT_VERIFIED_MESSAGE)
            }
        }
    )

    RevokeAccess(
        snackbarHostState = snackbarHostState,
        coroutineScope = coroutineScope,
        signOut = {
            viewModel.signOut()
        }
    )
}