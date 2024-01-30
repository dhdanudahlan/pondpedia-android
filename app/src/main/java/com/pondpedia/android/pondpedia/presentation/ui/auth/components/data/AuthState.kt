package com.pondpedia.android.pondpedia.presentation.ui.auth.components.data

data class AuthState(
    val isSignInLoading: Boolean = false,
    val isSignInSuccessful: Boolean = false,
    val isSignInError: Boolean = false,

    val signInSuccess: String? = null,
    val signInError: String? = null,

    val isSignUpLoading: Boolean = false,
    val isSignUpSuccessful: Boolean = false,
    val isSignUpError: Boolean = false,

    val signUpSuccess: String? = null,
    val signUpError: String? = null,


    val name: String = "",
    val nameError: String? = null,

    val phoneNumber: String = "",
    val phoneNumberError: String? = null,

    val email: String = "",
    val emailError: String? = null,

    val password: String = "",
    val passwordError: String? = null,

    val repeatedPassword: String = "",
    val repeatedPasswordError: String? = null,

    val occupation: String = "",
    val occupationError: String? = null,

    val informationSource: String = "",
    val informationSourceError: String? = null,

    val acceptedTerms: Boolean = false,
    val acceptedTermsError: String? = null,

    val photoUrl: String = "",
    val firebaseId: String = "",
    val preferences: String = "",

    val signInId: String = "",
    val signInPassword: String = ""
)
