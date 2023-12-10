package com.pondpedia.android.pondpedia.presentation.ui.auth.sign_up.util

import com.pondpedia.android.pondpedia.presentation.ui.auth.components.util.AuthEvent

sealed interface SignInFormEvent {
    data class NameChanged(val value: String): SignInFormEvent
    data class PhoneNumberChanged(val value: String): SignInFormEvent
    data class EmailChanged(val value: String): SignInFormEvent
    data class PasswordChanged(val value: String): SignInFormEvent
    data class RepeatedPasswordChanged(val value: String): SignInFormEvent
    data class OccupationChanged(val value: String): SignInFormEvent
    data class InformationSourceChanged(val value: String): SignInFormEvent

    data object SignUp: SignInFormEvent
}