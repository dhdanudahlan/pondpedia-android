package com.pondpedia.android.pondpedia.data.remote.dto.auth

import com.google.gson.annotations.SerializedName

data class UserRegistrationRequest(
    val name: String,
    val username: String,
    @SerializedName("phone_number")
    val phoneNumber: String,
    val email: String,
    val occupation: String,
    @SerializedName("information_source")
    val informationSource: String,
    @SerializedName("preferences")
    val userPreferences: String
)
