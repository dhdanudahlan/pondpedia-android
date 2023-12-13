package com.pondpedia.android.pondpedia.presentation.ui.home.more.profile.components

import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.SnackbarResult
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import com.pondpedia.android.pondpedia.components.ProgressBar
import com.pondpedia.android.pondpedia.core.util.Constants.ACCESS_REVOKED_MESSAGE
import com.pondpedia.android.pondpedia.core.util.Constants.REVOKE_ACCESS_MESSAGE
import com.pondpedia.android.pondpedia.core.util.Constants.SENSITIVE_OPERATION_MESSAGE
import com.pondpedia.android.pondpedia.core.util.Constants.SIGN_OUT_ITEM
import com.pondpedia.android.pondpedia.core.util.Utils.Companion.showMessage
import com.pondpedia.android.pondpedia.domain.model.auth.Response.Failure
import com.pondpedia.android.pondpedia.domain.model.auth.Response.Loading
import com.pondpedia.android.pondpedia.domain.model.auth.Response.Success
import com.pondpedia.android.pondpedia.presentation.ui.home.more.profile.ProfileViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

/*
@Composable
fun RevokeAccess(
    viewModel: ProfileViewModel = hiltViewModel(),
    navigateToAuthScreen: (accessRevoked: Boolean) -> Unit,
    showSnackBar: () -> Unit
) {
    when(val revokeAccessResponse = viewModel.revokeAccessResponseGoogle) {
        is Loading -> ProgressBar()
        is Success -> revokeAccessResponse.data?.let { accessRevoked ->
            LaunchedEffect(accessRevoked) {
                navigateToAuthScreen(accessRevoked)
            }
        }
        is Failure -> LaunchedEffect(Unit) {
            print(revokeAccessResponse.e)
            showSnackBar()
        }
    }
}*/
@Composable
fun RevokeAccess(
    viewModel: ProfileViewModel = hiltViewModel(),
    snackbarHostState: SnackbarHostState,
    coroutineScope: CoroutineScope,
    signOut: () -> Unit,
) {
    val context = LocalContext.current

    fun showRevokeAccessMessage() = coroutineScope.launch {
        val result = snackbarHostState.showSnackbar(
            message = REVOKE_ACCESS_MESSAGE,
            actionLabel = SIGN_OUT_ITEM
        )
        if (result == SnackbarResult.ActionPerformed) {
            signOut()
        }
    }

    when(val revokeAccessResponse = viewModel.revokeAccessResponse) {
        is Loading -> ProgressBar()
        is Success -> {
            val isAccessRevoked = revokeAccessResponse.data
            LaunchedEffect(isAccessRevoked) {
                if (isAccessRevoked == true) {
                    showMessage(context, ACCESS_REVOKED_MESSAGE)
                }
            }
        }
        is Failure -> revokeAccessResponse.apply {
            LaunchedEffect(e) {
                print(e)
                if (e.message == SENSITIVE_OPERATION_MESSAGE) {
                    showRevokeAccessMessage()
                }
            }
        }
    }
}
