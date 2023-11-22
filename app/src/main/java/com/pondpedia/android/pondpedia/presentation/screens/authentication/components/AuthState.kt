package com.pondpedia.android.pondpedia.presentation.screens.authentication.components

data class AuthState(
    val isSignInSuccessful: Boolean = false,
    val signInError: String? = null,
    val isSignUpSuccessful: Boolean = false,
    val signUpError: String? = null,
    val email: String = "",
    val password: String = "",
)
