package com.pondpedia.android.pondpedia.presentation.ui.auth.components.composable

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.hilt.navigation.compose.hiltViewModel
import com.pondpedia.android.pondpedia.components.ProgressBar
import com.pondpedia.android.pondpedia.data.remote.dto.auth.UserRegistrationRequest
import com.pondpedia.android.pondpedia.domain.model.auth.Response.*
import com.pondpedia.android.pondpedia.presentation.ui.auth.components.viewmodel.AuthViewModel
import com.pondpedia.android.pondpedia.presentation.ui.auth.sign_in.viewmodel.SignInViewModel

@Composable
fun SignInWithGoogle(
    viewModel: SignInViewModel = hiltViewModel(),
    navigateToHomeScreen: (signedIn: Boolean) -> Unit
) {
    when(val signInWithGoogleResponse = viewModel.signInWithGoogleResponse) {
        is Loading -> ProgressBar()
        is Success -> signInWithGoogleResponse.data?.let { authResult ->
            LaunchedEffect(authResult) {
                navigateToHomeScreen(authResult.user != null)
                if (authResult.user != null) {
                    val isNewUser = authResult.additionalUserInfo?.isNewUser
                    if (isNewUser == true) {
                        authResult.user?.let { user ->
                            val AuthResponse = viewModel.registerGoogleAuth(
                                token = user.uid,
                                userRegistrationRequest =  UserRegistrationRequest(
                                    name = user.displayName!!,
                                    username = user.email!!,
                                    phoneNumber = user.phoneNumber!!,
                                    email = user.email!!,
                                    occupation = "",
                                    informationSource = "",
                                    userPreferences = ""
                                )
                            )

                        }
                    }
                }
            }
        }
        is Failure -> LaunchedEffect(Unit) {
            print(signInWithGoogleResponse.e)
        }
    }
}