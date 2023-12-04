package ro.alexmamo.firebasesigninwithgoogle.presentation.profile

import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.SnackbarResult
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.pondpedia.android.pondpedia.core.util.Constants.REVOKE_ACCESS_MESSAGE
import com.pondpedia.android.pondpedia.core.util.Constants.SIGN_OUT
import com.pondpedia.android.pondpedia.presentation.screens.home.profile.ProfileViewModel
import com.pondpedia.android.pondpedia.presentation.screens.home.profile.components.ProfileTopBar
import com.pondpedia.android.pondpedia.presentation.screens.home.profile.components.ProfileContent
import com.pondpedia.android.pondpedia.presentation.screens.home.profile.components.SignOut
import kotlinx.coroutines.launch
import com.pondpedia.android.pondpedia.presentation.screens.home.profile.components.RevokeAccess

@Composable
fun ProfileScreen(
    viewModel: ProfileViewModel = hiltViewModel(),
    navigateToAuthScreen: () -> Unit
) {
    val snackbarHostState = remember { SnackbarHostState() }
    val coroutineScope = rememberCoroutineScope()

    Scaffold(
        snackbarHost = { SnackbarHost(snackbarHostState) },
        topBar = {
            ProfileTopBar(
                signOut = {
                    viewModel.signOut()
                },
                revokeAccess = {
                    viewModel.revokeAccess()
                }
            )
        },
        content = { padding ->
            ProfileContent(
                padding = padding,
                photoUrl = viewModel.photoUrl,
                displayName = viewModel.displayName
            )
        },
    )

    SignOut(
        navigateToAuthScreen = { signedOut ->
            if (signedOut) {
                navigateToAuthScreen()
            }
        }
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
        navigateToAuthScreen = { accessRevoked ->
            if (accessRevoked) {
                navigateToAuthScreen()
            }
        },
        showSnackBar = {
            showSnackBar()
        }
    )
}

@Preview
@Composable
fun ProfileScreenPreview(
) {
    ProfileScreen(
        navigateToAuthScreen = {}
    )
}