package com.pondpedia.android.pondpedia.presentation.ui.home.profile.components

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.hilt.navigation.compose.hiltViewModel
import com.pondpedia.android.pondpedia.components.ProgressBar
import com.pondpedia.android.pondpedia.domain.model.Response.*
import com.pondpedia.android.pondpedia.presentation.ui.home.profile.ProfileViewModel

@Composable
fun RevokeAccess(
    viewModel: ProfileViewModel = hiltViewModel(),
    navigateToAuthScreen: (accessRevoked: Boolean) -> Unit,
    showSnackBar: () -> Unit
) {
    when(val revokeAccessResponse = viewModel.revokeAccessResponse) {
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
}