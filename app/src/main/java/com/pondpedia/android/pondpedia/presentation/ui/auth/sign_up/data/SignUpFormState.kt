package com.pondpedia.android.pondpedia.presentation.ui.auth.sign_up.data

data class SignUpFormState(
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
    val acceptedTermsError: String? = null
)
