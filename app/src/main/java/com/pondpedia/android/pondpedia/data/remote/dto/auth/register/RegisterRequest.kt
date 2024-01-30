package com.pondpedia.android.pondpedia.data.remote.dto.auth.register

import com.google.gson.annotations.SerializedName

data class RegisterRequest(
    val name: String,
    val username: String,
    @field:SerializedName("phone_number")
    val phoneNumber: String,
    val email: String,
    val password: String,
    val occupation: String,
)
