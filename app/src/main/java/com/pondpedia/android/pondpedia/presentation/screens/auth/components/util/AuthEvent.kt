package com.pondpedia.android.pondpedia.presentation.screens.auth.components.util

sealed interface AuthEvent {
    data object SignIn: AuthEvent
    data object SignUp: AuthEvent

    data class SetName(val value: String): AuthEvent
    data class SetPhoneNumber(val value: String): AuthEvent
    data class SetEmail(val value: String): AuthEvent
    data class SetPassword(val value: String): AuthEvent
    data class SetPasswordConfirmation(val value: String): AuthEvent
    data class SetOccupation(val value: String): AuthEvent
    data class SetInformationSource(val value: String): AuthEvent
    data class SetPhotoUrl(val value: String): AuthEvent
    data class SetFirebaseId(val value: String): AuthEvent
    data class SetPreferences(val value: String): AuthEvent

    data class SetSignInId(val value: String): AuthEvent
    data class SetSignInPassword(val value: String): AuthEvent

}