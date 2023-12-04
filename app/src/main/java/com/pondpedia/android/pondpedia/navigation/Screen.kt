package com.pondpedia.android.pondpedia.navigation

import com.pondpedia.android.pondpedia.core.util.Constants.AUTH_SCREEN
import com.pondpedia.android.pondpedia.core.util.Constants.PROFILE_SCREEN

sealed class Screen(val route: String) {
    data object AuthScreen: Screen(AUTH_SCREEN)
    data object ProfileScreen: Screen(PROFILE_SCREEN)
}