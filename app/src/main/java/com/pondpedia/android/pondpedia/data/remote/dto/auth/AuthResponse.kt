package com.pondpedia.android.pondpedia.data.remote.dto.auth

data class AuthResponse(
    val success: Boolean = false,
    val data: String = "",
    val message: String = ""
)
