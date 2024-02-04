package com.pondpedia.android.pondpedia.data.remote.dto.auth

import com.squareup.moshi.Json


data class UserRegistrationRequest(
    val name: String,
    val username: String,
    @Json(name = "phone_number")
    val phoneNumber: String,
    val email: String,
    val occupation: String,
    @Json(name = "information_source")
    val informationSource: String,
    @Json(name = "preferences")
    val userPreferences: String
)
