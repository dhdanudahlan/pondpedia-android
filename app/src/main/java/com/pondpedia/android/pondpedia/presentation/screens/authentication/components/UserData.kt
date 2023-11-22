package com.pondpedia.android.pondpedia.presentation.screens.authentication.components

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class UserData(
    val userId: String?,
    val username: String?,
    val pictureUrl: String?,
    val userEmail: String?,
): Parcelable
