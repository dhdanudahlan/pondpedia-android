package com.pondpedia.android.pondpedia.core.util

object Constants {

    //App
    const val TAG = "AppTag"

    //Collection References
    const val USERS = "users"

    // User fields
    const val DISPLAY_NAME = "displayName"
    const val EMAIL = "email"
    const val PHOTO_URL = "photoUrl"
    const val CREATED_AT = "createdAt"

    //Names
    const val SIGN_IN_REQUEST = "signInRequest"
    const val SIGN_UP_REQUEST = "signUpRequest"

    //Buttons
    const val SIGN_IN_WITH_GOOGLE = "Sign in with Google"
    const val SIGN_OUT = "Sign-out"
    const val REVOKE_ACCESS = "Revoke Access"

    //Screens
    const val AUTH_SCREEN = "Authentication"
    const val SIGN_IN_SCREEN = "Sign in"
    const val FORGOT_PASSWORD_SCREEN = "Forgot password"
    const val SIGN_UP_SCREEN = "Sign up"
    const val VERIFY_EMAIL_SCREEN = "Verify email"
    const val PROFILE_SCREEN = "Profile"

    //Menu Items
    const val SIGN_OUT_ITEM = "Sign out"
    const val REVOKE_ACCESS_ITEM = "Revoke Access"

    //Texts
    const val FORGOT_PASSWORD = "Forgot password?"
    const val NO_ACCOUNT = "No account? Sign up."
    const val ALREADY_USER = "Already a user? Sign in."
    const val WELCOME_MESSAGE = "Welcome to our app."
    const val ALREADY_VERIFIED = "Already verified?"
    const val SPAM_EMAIL = "If not, please also check the spam folder."

    //Messages
    const val VERIFY_EMAIL_MESSAGE = "We've sent you an email with a link to verify the email."
    const val EMAIL_NOT_VERIFIED_MESSAGE = "Your email is not verified."
    const val RESET_PASSWORD_MESSAGE = "We've sent you an email with a link to reset the password."
    const val REVOKE_ACCESS_MESSAGE = "You need to re-authenticate before revoking the access."
    const val ACCESS_REVOKED_MESSAGE = "Your access has been revoked."

    //Error Messages
    const val SENSITIVE_OPERATION_MESSAGE = "This operation is sensitive and requires recent authentication. Log in again before retrying this request."


    const val PREFS_KEY_HOMEPAGE = "prefs_key_homepage"
    const val PREFS_KEY_THEME = "prefs_key_theme"
    const val DATASTORE_KEY = "datastore_key"
    const val USERDATA_KEY = "userdata_key"
    const val USER_SETTINGS = "user_settings_key"
    const val APP_ENTRY = "app_entry_key"

    const val MAXIMAL_SIZE = 1000000
}