package com.pondpedia.compose.pondpedia.presentation.screens.authentication.sign_up

import com.pondpedia.compose.pondpedia.presentation_copy.screens.authentication.components.UserData

data class SignUpResult(
    val data: UserData?,
    val errorMessage: String?
)
