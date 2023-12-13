package com.pondpedia.android.pondpedia.presentation.navigation

import com.pondpedia.android.pondpedia.core.util.Constants.AUTH_SCREEN
import com.pondpedia.android.pondpedia.core.util.Constants.FORGOT_PASSWORD_SCREEN
import com.pondpedia.android.pondpedia.core.util.Constants.PROFILE_SCREEN
import com.pondpedia.android.pondpedia.core.util.Constants.SIGN_IN_SCREEN
import com.pondpedia.android.pondpedia.core.util.Constants.SIGN_UP_SCREEN
import com.pondpedia.android.pondpedia.core.util.Constants.VERIFY_EMAIL_SCREEN

sealed class Screen(val route: String) {
    data object AuthScreen: Screen(AUTH_SCREEN)
    data object ProfileScreen: Screen(PROFILE_SCREEN)
    data object SignInScreen: Screen(SIGN_IN_SCREEN)
    data object ForgotPasswordScreen: Screen(FORGOT_PASSWORD_SCREEN)
    data object SignUpScreen: Screen(SIGN_UP_SCREEN)
    data object VerifyEmailScreen: Screen(VERIFY_EMAIL_SCREEN)
}