package com.pondpedia.android.pondpedia.presentation.ui.auth.components.util

sealed interface AuthEvent {
    data object SignIn: AuthEvent
    data object SignUp: AuthEvent
    data object DismissSignUpCommonDialog: AuthEvent
    data object DismissSignInCommonDialog: AuthEvent

    data class SetName(val value: String): AuthEvent
    data class SetPhoneNumber(val value: String): AuthEvent
    data class SetEmail(val value: String): AuthEvent
    data class SetPassword(val value: String): AuthEvent
    data class SetRepeatedPassword(val value: String): AuthEvent
    data class SetOccupation(val value: String): AuthEvent
    data class SetInformationSource(val value: String): AuthEvent
    data class SetAcceptTerms(val value: Boolean): AuthEvent
    data class SetPhotoUrl(val value: String): AuthEvent
    data class SetFirebaseId(val value: String): AuthEvent
    data class SetPreferences(val value: String): AuthEvent

    data class SetSignInId(val value: String): AuthEvent
    data class SetSignInPassword(val value: String): AuthEvent

    data object ResetSignUpState: AuthEvent
    data object ResetSignInState: AuthEvent

}