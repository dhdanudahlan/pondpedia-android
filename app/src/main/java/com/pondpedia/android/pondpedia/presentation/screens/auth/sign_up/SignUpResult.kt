package com.pondpedia.android.pondpedia.presentation.screens.auth.sign_up

import com.pondpedia.android.pondpedia.presentation.screens.authentication.components.UserData


data class SignUpResult(
    val data: UserData?,
    val errorMessage: String?
)
