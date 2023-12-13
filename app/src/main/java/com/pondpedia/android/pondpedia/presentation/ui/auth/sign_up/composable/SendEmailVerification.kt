package com.pondpedia.android.pondpedia.presentation.ui.auth.sign_up.composable

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.hilt.navigation.compose.hiltViewModel
import com.pondpedia.android.pondpedia.components.ProgressBar
import com.pondpedia.android.pondpedia.domain.model.auth.Response.Failure
import com.pondpedia.android.pondpedia.domain.model.auth.Response.Loading
import com.pondpedia.android.pondpedia.domain.model.auth.Response.Success
import com.pondpedia.android.pondpedia.presentation.ui.auth.sign_up.viewmodel.SignUpViewModel

@Composable
fun SendEmailVerification(
    viewModel: SignUpViewModel = hiltViewModel()
) {
    when(val sendEmailVerificationResponse = viewModel.sendEmailVerificationResponse) {
        is Loading -> ProgressBar()
        is Success -> Unit
        is Failure -> sendEmailVerificationResponse.apply {
            LaunchedEffect(e) {
                print(e)
            }
        }
    }
}