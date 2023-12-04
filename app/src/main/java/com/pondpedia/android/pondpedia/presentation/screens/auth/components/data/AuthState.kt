package com.pondpedia.android.pondpedia.presentation.screens.auth.components.data

data class AuthState(
    val isSignInSuccessful: Boolean = false,
    val signInError: String? = null,
    val isSignUpSuccessful: Boolean = false,
    val signUpError: String? = null,

    val name: String = "",
    val phoneNumber: String = "",
    val email: String = "",
    val password: String = "",
    val passwordConfirmation: String = "",
    val occupation: String = "",
    val informationSource: String = "",
    val photoUrl: String = "",
    val firebaseId: String = "",
    val preferences: String = "",

    val signInId: String = "",
    val signInPassword: String = ""
)
